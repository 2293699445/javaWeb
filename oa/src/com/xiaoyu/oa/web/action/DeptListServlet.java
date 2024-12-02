package com.xiaoyu.oa.web.action;

import com.xiaoyu.oa.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.sql.*;

public class DeptListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //连接数据库
        //查询数据里面所有的数据
        //返回到页面去
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //设置响应内容类型和字符集,防止乱码
        resp.setContentType("text/html;charset=utf-8");
        //响应
        PrintWriter out= resp.getWriter();
        //获取项目根路径
        String path = req.getContextPath();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>部门列表页面</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1 align='center'>部门列表</h1>");
        out.println("    <hr>");
        out.println("    <table border='10px' align='center' width='50%'>");
        out.println("        <tr>");
        out.println("            <th>序号</th>");
        out.println("            <th>部门编号</th>");
        out.println("            <th>部门名称</th>");
        out.println("            <th>操作</th>");
        out.println("        </tr>");
        out.println("<!--以上是静态数据-->");
        out.println("");
        out.println("");

        try{
            int i=0;
            //注册驱动+获取连接对象
            conn = DBUtils.getConnection();
            //获取预编译数据库操作对象
            String sql = "select * from dept";
            pstmt = conn.prepareStatement(sql);
            //执行sql
            rs = pstmt.executeQuery();
            //处理查询结果集
            while(rs.next()){
                //返回数据到页面
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                out.println("        <tr>");
                out.println("            <td>"+(++i)+"</td>");
                out.println("            <td>"+deptno+"</td>");
                out.println("            <td>"+dname+"</td>");
                out.println("            <td>");
                out.println("                <a href='"+path+"/dept/delete?delete="+deptno+"'>删除</a>");
                out.println("                <a href='"+path+"/dept/edit'>修改</a>");
                out.println("                <a href='"+path+"/dept/detail?detail="+deptno+"'>详情</a>");
                out.println("            </td>");
                out.println("        </tr>");
                out.println("");
                out.println("");

            }
        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            try{
                DBUtils.close(conn,pstmt,rs);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        out.println("    </table>");
        out.println("<!--以下是静态数据-->");
        out.println("    <hr>");
        out.println("    <a href='/oa/dept/add'>新增部门</a>");
        out.println("</body>");
        out.println("</html>");

    }
}
