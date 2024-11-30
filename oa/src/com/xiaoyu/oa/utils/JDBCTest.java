package com.xiaoyu.oa.utils;

import java.sql.SQLException;
import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动&2.获取连接对象
        Connection conn = DBUtils.getConnection();
        //3.获取数据库预编译操作对象
        String sql = "select * from dept";
        PreparedStatement pstmt =  conn.prepareStatement(sql);
        //4.执行sql语句
        ResultSet rs = pstmt.executeQuery();
        //处理查询结果集
        while(rs.next()){

            System.out.println(rs.getInt(1)+"---"+rs.getString(2)+"---"+rs.getString(3));
        }
        //关闭资源
        DBUtils.close(conn,pstmt,rs);
    }
}
