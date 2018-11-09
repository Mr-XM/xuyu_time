<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
    <title>为设置人员名单</title>
</head>
<body>
<%@ page import="com.xuyu.tool.MysqlConnect" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<h4 align="center">未设置上课时间人员名单</h4>
<%
    try {
        MysqlConnect ps = new MysqlConnect();
        Connection con;
        Statement sql;
        ResultSet res;
        con = ps.getConnect();
        sql = con.createStatement();
        res = sql.executeQuery("select * from teacher_time where flag = 0");
%>
<br>
<table align="center" width="100%" style="line-height:35px;">
    <tr>
        <td align="left">姓名</td>
        <td align="left">电话</td>
    </tr>
    <%
        while (res.next()) {
    %>
    <tr>
        <td align="left"><%=res.getString("name") %>
        </td>
        <td align="left"><%=res.getString("tel") %>
        </td>
    </tr>
    <%
            }
            sql.close();
            con.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    %>
</table>
</body>
</html>