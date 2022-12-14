package com.lp.first.learn.old.jdbc;

import java.math.BigInteger;
import java.sql.SQLException;

/**
 * Created by Lin on 2018/3/19.
 */
public class Demo {

    public static void main(String[] args) throws SQLException {
//        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","orac","orac");
//        PreparedStatement statement = conn.prepareStatement("select count(1) from WORK_USER");
//        ResultSet result = statement.executeQuery("select count(1) as s from WORK_USER");
//        ResultSet result = JdbcUtil.executeQuery("select count(1) as s from WORK_USER");
//        while(result.next()){
//            System.err.println(result.getInt("s"));
//        }
        int id = 100000;
        BigInteger b = BigInteger.valueOf(id);
        System.err.println(b);
        String userName = "小红";
//                int i = JdbcUtil.update("insert into WORK_USER(ID,USER_NAME) values(?,?)",id,userName);
        int i = JdbcUtil.update("update WORK_USER set user_Name = ? where id = ?", userName, id);
    }
}
