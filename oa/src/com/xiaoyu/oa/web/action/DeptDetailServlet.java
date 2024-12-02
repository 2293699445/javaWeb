package com.xiaoyu.oa.web.action;

import com.xiaoyu.oa.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //连接数据库
        //查询数据
        //返回到页面
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //处理请求和响应
        int detail = Integer.parseInt(req.getParameter("detail"));
        String path = req.getContextPath();
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        out.print("                <!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.print("    <title>部门详情页面</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("    <h1 align='center'>部门详情</h1>");
        out.print("    <hr>");
        out.print("    <table border='10px' align='center' width='50%'>");
        out.print("        <tr>");
        out.print("            <th>序号</th>");
        out.print("            <th>部门编号</th>");
        out.print("            <th>部门名称</th>");
        out.print("            <th>位置</th>");
        out.print("        </tr>");
        out.print("<!--以上是静态数据-->");
        out.print("");
        out.print("");

        try{
            //注册驱动+获取连接对象
            conn = DBUtils.getConnection();
            //获取数据库操作对象
            String sql = "select * from dept where deptno = ?";
            pstmt = conn.prepareStatement(sql);
            //执行sql
            pstmt.setInt(1, detail);
            rs = pstmt.executeQuery();
            //处理查询结果集
            if(rs.next()){

                String dname = rs.getString("dname");
                String loc = rs.getString("loc");


                out.print("        <tr>");
                out.print("            <td>1</td>");
                out.print("            <td>"+detail+"</td>");
                out.print("            <td>"+dname+"</td>");
                out.print("            <td>"+loc+"</td>");
                out.print("        </tr>");

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                DBUtils.close(conn,pstmt,rs);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        out.print("    </table>");
        out.print("<input type='button' value='后退' onclick='window.history.back()'/>");
        out.print("</body>");
        out.print("</html>");
    }
}
