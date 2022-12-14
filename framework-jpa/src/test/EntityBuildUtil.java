package com.lp.first.framework.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lin on 2017/10/31.
 */
public class EntityBuildUtil {

    private String path = "code";

    private String authorName = "lping";

    private String tableName = "s_user";

    private String[] colNames;

    private String[] colTypes;

    private int[] colSizes;

    private boolean isUtil = false;

    private boolean isSql = false;

    private static final String URL = "jdbc:mysql://localhost:3306/springboot";

    private static final String NAME = "root";

    private static final String PASSWORD = "123";

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * 得到类名和set 和 get 要拼接的字符串
     *
     * @param str
     * @return
     */
    private String initClass(String str) {
        String reg = "[a-zA-Z]";
        str = str.toLowerCase();
        StringBuffer buffer = new StringBuffer();
        List<String> strs = new ArrayList<String>();
        if (str.substring(0, 1).matches(reg)) {
            String[] sz = str.split("_");
            strs = Arrays.asList(sz);
            for (String s : strs) {
                s = s.replace(s.substring(0, 1), s.substring(0, 1).toUpperCase());
                buffer.append(s);
            }
        }
        return buffer.toString();
    }

    /**
     * 获取java类型
     *
     * @param sqlType
     * @return
     */
    private String getJaveType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("number") || sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("varchar2") || sqlType.equalsIgnoreCase("nvarchar")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else {
            return "String";
        }
    }

    /**
     * 功能：成员变量
     *
     * @param str
     * @return
     */
    private String initField(String str) {
        String reg = "[a-zA-Z]";
        str = str.toLowerCase();
        StringBuffer buffer = new StringBuffer();
        List<String> strs = new ArrayList<String>();
        if (str.substring(0, 1).matches(reg)) {
            String[] sz = str.split("_");
            strs = Arrays.asList(sz);
            for (String s : strs) {
                s = s.replace(s.substring(0, 1), s.substring(0, 1).toUpperCase());
                buffer.append(s);
            }
        }
        String finalStr = buffer.toString();
        finalStr = finalStr.replace(finalStr.substring(0, 1), finalStr.substring(0, 1).toLowerCase());
        return finalStr;
    }

    /**
     * 生成成员变量
     *
     * @param sb
     * @return
     */
    private StringBuffer getProperty(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tprivate " + getJaveType(colTypes[i]) + " " + getPropertyName(colNames[i]) + ";\r\n\n");
        }
        return sb;
    }

    //生成文件里所有代码
    private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + "com.lp.first.framework." + this.path + ";\r\n\n");
        if (isUtil) {
            sb.append("import java.util.Date;\r\n");
        }
        if (isSql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("\r\n");
        //生成注释
        sb.append("/**\r\n");
        sb.append(" * " + tableName + " 实体类\r\n");
        sb.append(" */ \r\n");
        //生成实体主体代码
        sb.append("public class " + initClass(tableName) + "{\r\n\n");
        sb = getProperty(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    //改字段名为成员变量名称
    public String getPropertyName(String str) {
        str = str.toLowerCase();
        String res = "";
        String[] strs = str.split("_");
        for (int i = 0; i < strs.length; i++) {
            if (i != 0) {
                strs[i] = strs[i].replace(strs[i].substring(0, 1), strs[i].substring(0, 1).toUpperCase());
            }
            res += strs[i];
        }
        return res;
    }

    //执行输出
    public EntityBuildUtil() throws ClassNotFoundException, SQLException, IOException {
        Class.forName(DRIVER);
        //创建数据库连接
        Connection con = DriverManager.getConnection(URL, NAME, PASSWORD);
        //查询要什么实体类的表
        String sql = "select * from " + tableName;
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSetMetaData res = pre.getMetaData();
        int size = res.getColumnCount();
        colNames = new String[size];
        colTypes = new String[size];
        colSizes = new int[size];
        for (int i = 0; i < size; i++) {
            colNames[i] = res.getColumnName(i + 1);
            colTypes[i] = res.getColumnTypeName(i + 1);
            colSizes[i] = res.getColumnDisplaySize(i + 1);
        }
        String content = parse(colNames, colTypes, colSizes);
        File dir = new File("");
        String outPath = dir.getAbsolutePath() + "/framework/src/main/java/com/lp/first/framework/" + this.path.replace(".", "/") + "/" + initClass
            (tableName) +
            ".java";
        FileWriter fw = new FileWriter(outPath);
        PrintWriter pw = new PrintWriter(fw);
        pw.write(content);
        pw.flush();
        pw.close();
        con.close();
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        EntityBuildUtil entityBuildUtil = new EntityBuildUtil();
    }
}
