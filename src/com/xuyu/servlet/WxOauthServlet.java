package com.xuyu.servlet;

import com.xuyu.message.GOauth2Core;
import com.xuyu.message.WxContext;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WxOauthServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (!"authdeny".equals(code)) {
            String access_token = WxContext.getInstance().getAccessToken();

            // agentid 跳转链接时所在的企业应用ID 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同
            String userId = GOauth2Core.getUserID(access_token, code);
            request.setAttribute("userId", userId);
            request.setAttribute("state", state);
           
        } else {
            out.print("授权获取失败");
        }
        //获得userId和state改变f并跳转回去teacher_time.jsp
        request.getRequestDispatcher("teacher_time.jsp?f=1111").forward(request, response);
    }
}
