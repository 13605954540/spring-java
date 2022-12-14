package com.lp.first.framework.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import freemarker.template.Template;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * @author 苹神
 * @date 2022/11/30
 */
public class GeneratorUtil {

    //类作者时间信息
    private final String AUTHOR = "苹神";
    private final String CURRENT_DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    //数据库配置
    private final String URL = "jdbc:mysql://localhost:3306/educational";
    private final String USER = "root";
    private final String PASSWORD = "";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //要生成java文件的表名数组
    private final List<String> TABLE_NAMES = Lists.newArrayList(
            "test",
            "test2",
            "test3",
            "test4"
    );

    //下级目录名(如家庭端相关的family为根目录)
    private final String NEXT_PACKAGE_NAME = "family";
    //项目根目录
    private final String PROJ_ROOT = "com.example.educationalsystem";
    //项目根路径
    private final String PROJ_DISK_BASE_PATH = "D:\\educational-system\\src\\main\\java\\com\\example\\educationalsystem";

    //不需要生成实体代码的列
    private final List<String> NO_DEAL_COL_NAMES = Lists.newArrayList("id", "create_by", "update_by", "create_time", "update_time", "create_id", "update_id", "is_del");

    //生成的文件类型(实现类统一为接口+.impl 如service.impl)
    private List<String> fileTypes = Lists.newArrayList(
            "entity",
            "condition",
            "mapper",
            "service",
            "service.impl",
            "controller"
    );


    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     * @throws Exception
     */
    public Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        Properties props =new Properties();
        props.put("remarksReporting","true");
        props.put("user", USER);
        props.put("password", PASSWORD);
        Connection connection= DriverManager.getConnection(URL, props);
        return connection;
    }

    /**
     * 获取表注释
     *
     * @return 表注释
     * @throws Exception
     */
    public String getTableAnnotation() throws Exception {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SHOW CREATE TABLE " + this.tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        String comment = "";
        while(resultSet.next()) {
            String create = resultSet.getString(2);
            int index = create.indexOf("COMMENT='");
            if (index < 0) {
                return "";
            }
            comment = create.substring(index + 9);
            comment = comment.substring(0, comment.length() - 1);
            try {
                comment = new String(comment.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return comment;
    }

    /**
     * 生成所有文件
     *
     * @throws Exception
     */
    public void generate() throws Exception{
        try {
            for(String item: TABLE_NAMES) {
                this.tableName = item;
                Connection connection = getConnection();
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                ResultSet resultSet = databaseMetaData.getColumns(null, "%", this.tableName, "%");
                generateFiles(resultSet);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成表对应的java文件
     *
     * @param resultSet 数据库结果集
     * @throws Exception
     */
    private void generateFiles(ResultSet resultSet) throws Exception{
        for(String item: fileTypes) {
            String filePath = getFilePath(item);
            String fileName = item.substring(0, 1).toUpperCase() + (item.contains(".") ? item.substring(1, item.indexOf(".")) : item.substring(1)) +".java";
            if(fileName.contains("Model")) {
                fileName = fileName.replace("Model", "");
            }
            if(fileName.contains("Entity")) {
                fileName = fileName.replace("Entity", "");
            }
            final String path = filePath + getModelName(this.tableName) + fileName;
            final String templateName = item.substring(0, 1) + (item.contains(".")
                    ? item.substring(1, item.indexOf("."))
                    : item.substring(1))
                    + ".ftl";
            File mapperFile = new File(path);
            List<Map<String, Object>> columnClassList = new ArrayList<>();
            Map columnMap = Maps.newHashMap();
            while (resultSet.next()) {
                if (this.NO_DEAL_COL_NAMES.contains(resultSet.getString("COLUMN_NAME"))) {
                    continue;
                }
                columnMap = Maps.newHashMap();
                columnMap.put("columnName", resultSet.getString("COLUMN_NAME"));
                columnMap.put("columnType", resultSet.getString("TYPE_NAME").toLowerCase());
                columnMap.put("changeColumnName", replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
                columnMap.put("columnComment", resultSet.getString("REMARKS"));
                columnClassList.add(columnMap);
            }
            Map<String, Object> dataMap = Maps.newHashMap();
            dataMap.put("model_column", columnClassList);
            generateFileByTemplate(templateName, mapperFile, dataMap);
        }
    }

    /**
     * freemarker根据模板生成文件操作
     *
     * @param templateName 模板名称
     * @param file 生成的文件完整路径
     * @param dataMap 传递到模板的参数
     * @throws Exception
     */
    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
        Template template = FreeMarkerTemplateUtil.getTemplate(templateName);
        String modelName = getModelName(this.tableName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name_small",this.tableName);
        dataMap.put("model_name",modelName);
        dataMap.put("author",AUTHOR);
        dataMap.put("date",CURRENT_DATE);
        dataMap.put("table_annotation",getTableAnnotation());
        dataMap.put("model_variable",modelName.substring(0,1).toLowerCase() + modelName.substring(1));
        dataMap.put("condition_name", modelName + "Condition");
        dataMap.put("proj_root", PROJ_ROOT);
        dataMap.put("next_package_name", NEXT_PACKAGE_NAME);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

    /**
     * 获取模型字段名称
     *
     * @param str 数据库字段名
     * @return 模型字段名
     */
    public String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return result;
    }

    /**
     * 获取模型名称
     *
     * @param tableName 表名
     * @return
     */
    public String getModelName(String tableName) {
        String result = replaceUnderLineAndUpperCase(tableName);
        return result.substring(0,1).toUpperCase() + result.substring(1);
    }

    /**
     * 获取生成文件所在包完整路径
     *
     * @param fileType 生成的文件所在包名
     * @return 文件所在包完整路径
     */
    public String getFilePath(String fileType) {
        String copyFileType = fileType;
        if(copyFileType.contains(".")) {
            fileType = fileType.substring(0, fileType.indexOf("."));
        }
        String res = PROJ_DISK_BASE_PATH + "\\" + fileType + "\\" + NEXT_PACKAGE_NAME;
        if(copyFileType.contains(".")) {
            res = res + "\\" + copyFileType.substring(copyFileType.indexOf(".") + 1);
        }
        File modelFile = new File(res);
        if(!modelFile.exists()) {
            modelFile.mkdir();
        }
        return res + "\\";
    }


    public static void main(String[] args) throws Exception{
        GeneratorUtil codeGenerateUtils = new GeneratorUtil();
        File baseFile = new File(codeGenerateUtils.PROJ_DISK_BASE_PATH);
        if(!baseFile.exists()) {
            baseFile.mkdir();
        }
        codeGenerateUtils.generate();
    }

    //表名(无用，TABLE_NAMES循环保存当前值供多个方法直接调用)
    private String tableName = "";
}
