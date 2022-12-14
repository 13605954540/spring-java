package com.lp.last.framework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author LP
 * @problem 1: 数字太长时,没有自适应 2: 高度太低
 * @date 2018/7/29
 */
public class ExcelUtil {

    public HSSFWorkbook hssfWorkbook;

    public List<Map<String, Object>> dataList;

    public String title;

    public String[] rows;

    public String[] fields;

    public HttpServletResponse response;

    public ExcelUtil(String title, String[] rowName, String[] fields, List<Map<String, Object>> dataList, HttpServletResponse response) {
        this.dataList = dataList;
        this.rows = rowName;
        this.title = title;
        this.response = response;
        this.fields = fields;
    }

    public HSSFWorkbook createHssfWorkbook() {
        return new HSSFWorkbook();
    }

    public HSSFSheet createHssfSheet(HSSFWorkbook hssfWorkbook) {
        return hssfWorkbook.createSheet();
    }

    public HSSFRow createHssfRow(HSSFSheet hssfSheet, int i) {
        return hssfSheet.createRow((short) i);
    }

    public void export() throws Exception {
        hssfWorkbook = createHssfWorkbook();
        HSSFCellStyle cellStyle = getColumnTopStyle(hssfWorkbook);
        HSSFSheet hssfSheet = createHssfSheet(hssfWorkbook);
        hssfSheet.createFreezePane(1, 3);
        hssfSheet.addMergedRegion(new CellRangeAddress(0, 1, 0, rows.length));
        HSSFRow titleRow = createHssfRow(hssfSheet, 0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(cellStyle);
        HSSFRow hssfRow = createHssfRow(hssfSheet, 2);
        Integer len = rows.length;
        for (int i = 0; i < len + 1; i++) {
            Cell cell = hssfRow.createCell(i);
            cell.setCellValue(i == 0 ? "序号" : rows[i - 1]);
            cell.setCellStyle(cellStyle);
        }
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> t = dataList.get(i);
            Class cla = t.getClass();
            HSSFRow row = createHssfRow(hssfSheet, i + 3);
            for (int z = 0; z < fields.length + 1; z++) {
                Cell cell = row.createCell(z);
                if (z == 0) {
                    cell.setCellValue(i + 1);
                    cell.setCellStyle(getStyle(hssfWorkbook));
                    continue;
                }
                String field = fields[z - 1];
                Object result = t.get(field);
                String value = "";
                if (result != null) {
                    value = getValue(result);
                }
                cell.setCellValue(value);
                HSSFCellStyle style = getStyle(hssfWorkbook);
                cell.setCellStyle(style);
            }
        }
        //让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < rows.length + 1; colNum++) {
            int columnWidth = hssfSheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (hssfSheet.getRow(rowNum) == null) {
                    currentRow = hssfSheet.createRow(rowNum);
                } else {
                    currentRow = hssfSheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    Cell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if (colNum == 0) {
                hssfSheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
            } else {
                hssfSheet.setColumnWidth(colNum, (columnWidth + 6) * 256);
            }
        }
        write();
    }

    public String getValue(Object obj) {
        if (obj instanceof Integer || obj instanceof String || obj instanceof Long || obj instanceof Double) {
            return String.valueOf(obj);
        } else if (obj instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd").format(obj);
        } else {
            return String.valueOf(obj);
        }
    }

    public void write() {
        try {
            String fileName = URLEncoder.encode(title + "_", "utf-8") + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
            String headStr = "attachment; filename=\"" + fileName + "\"";
            response.setContentType("application/x-msexcel");
            response.setHeader("Content-Disposition", headStr);
            OutputStream out = response.getOutputStream();
            hssfWorkbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBold(true);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        //设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        //设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 将excel数据保存在list中
     *
     * @param path   文件的具体地址
     * @param fields 要保存的map key
     * @param iStart 第几行开始
     * @param yStart 第几列开始
     * @param yEnd   第几列结束
     * @return
     * @throws ParseException
     */
    public static List<Map<String, Object>> getList(String path, String[] fields, int iStart, int yStart, int yEnd) throws ParseException {
        List<Map<String, Object>> list = new ArrayList();
        try {
            Workbook wb = new HSSFWorkbook(new FileInputStream(path));
            Sheet sheet = wb.getSheetAt(0);
            int totalRowNum = sheet.getLastRowNum();
            if (totalRowNum <= iStart + 1) {
                list = null;
            } else {
                int mapIndex = yStart;
                for (int i = iStart; i < totalRowNum; i++) {
                    Map map = new HashMap();
                    Row row = sheet.getRow(i);
                    for (int y = yStart; y < yEnd; y++) {
                        Object obj = null;
                        if (row.getCell(y) != null) {
                            row.getCell(y).setCellType(CellType.STRING);
                            if (row.getCell(y).getCellTypeEnum() == CellType.STRING) {
                                obj = row.getCell(y).getStringCellValue();
                            } else {
                                row.getCell(y).getNumericCellValue();
                            }
                        }
                        map.put(fields[y - mapIndex], obj);
                    }
                    list.add(map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 转换方法
     *
     * @parma numberString 要转换的浮点数
     * @parma format 要获得的格式 例如"hh:mm:ss"
     **/
    public static String toDate(double numberString) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        int wholeDays = (int) Math.floor(numberString);
        int HOURS_PER_DAY = 24;
        int SECONDS_PER_MINUTE = 60;
        int MINUTES_PER_HOUR = 60;
        int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
        long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;
        int millisecondsInday = (int) ((numberString - wholeDays) * DAY_MILLISECONDS + 0.5);
        Calendar calendar = new GregorianCalendar();
        setCalendar(calendar, wholeDays, millisecondsInday, false);
        return sdFormat.format(calendar.getTime());
    }

    private static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay, boolean use1904windowing) {
        int startYear = 1900;
        int dayAdjust = -1;
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1;
        } else if (wholeDays < 61) {
            dayAdjust = 0;
        }
        calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(GregorianCalendar.MILLISECOND, millisecondsInDay);
    }
}
