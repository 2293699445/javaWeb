package com.xiaoyu.oa.utils;

import java.sql.*;
import java.util.*;

public class DBUtils {

    private static ResourceBundle rb = ResourceBundle.getBundle("resources.jdbc");
    private static String driver = rb.getString("driver");
    private static String url = rb.getString("url");
    private static String user = rb.getString("user");
    private static String password = rb.getString("password");

    static {   //注册驱动
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return conn 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        Connection conn = null;

        conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    /**
     * 释放资源
     * @param conn 连接对象
     * @param pstmt 数据库操作对象
     * @param rs 查询结果集
     * @throws SQLException
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
        if(rs != null){
            rs.close();
        }
        if(pstmt != null){
            pstmt.close();
        }
        if(conn != null){
            conn.close();
        }
    }
}
