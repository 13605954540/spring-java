package com.lp.first.learn.old.jdbc;

import java.sql.*;

/**
 * JDBC增删改查工具类
 * Created by Lin on 2018/3/20.
 */
public class JdbcUtil {

    private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";

    private static final String userName = "orac";

    private static final String passWord = "orac";

    private static Connection conn = null;

    private static PreparedStatement statement = null;

    private static ResultSet result = null;

    static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static{
        try {
            conn = DriverManager.getConnection(url,userName,passWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PreparedStatement getStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    /**
     * 查询
     * @param sql 数据库语句
     * @param object ?对应的参数
     * @return
     */
    public static ResultSet executeQuery(String sql,Object... object) {
        try {
            statement = getStatement(sql);
            if(object != null){
                for(int i = 0;i<object.length;i++){
                    statement.setObject(i + 1,object[i]);
                }
                result = statement.executeQuery(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新操作
     * @param sql 数据库语句
     * @param object ?对应的参数
     * @return
     * @throws SQLException
     */
    public static int update(String sql,Object... object) throws SQLException {
        int res = -1;
        try {
            statement = getStatement(sql);
            if(object != null){
                for(int i = 0;i<object.length;i++){
                    statement.setObject(i + 1,object[i]);
                }
                res = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return res;
    }

    /**
     * 关闭
     * @throws SQLException
     */
    public static void close() throws SQLException {
        if(result != null){
            result.close();
        }
        if(statement != null){
            statement.close();
        }
        if(conn != null){
            conn.close();
        }
    }
}
