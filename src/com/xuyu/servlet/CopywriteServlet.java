package com.xuyu.servlet;

import com.xuyu.mysql.MysqlConnect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 * 文案处理类，从前端界面读取文案信息，更新到数据库
 */
public class CopywriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CopywriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        String text = request.getParameter("text");
        String textarea = request.getParameter("textarea");
        try {
            MysqlConnect ps = new MysqlConnect();
            Connection con;
            PreparedStatement sql;
            con = ps.getConnect();
            sql = con.prepareStatement("insert into copywriting_table(banner,text)" + "values(?,?)");
            sql.setString(1, text);
            sql.setString(2, textarea);
            sql.executeUpdate();
            sql.close();
            ps.closeConnect();
            out.print("文案提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("文案提交失败");
            out.print("<br><a href = 'editBanner.jsp'>重新提交</a>");
        }


    }
}
