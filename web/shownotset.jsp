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
<%@ page import="com.xuyu.mysql.SqlHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xuyu.message.Teacher" %>
<h4 align="center">未设置上课时间人员名单</h4>
<%
    List<Teacher> list = SqlHelper.getNotSetTimeTeacher();
%>
<br>
<table align="center" width="100%" style="line-height:35px;">
    <tr>
        <td align="center">姓名</td>
        <td align="center">电话</td>
    </tr>
    <%
        for(int i=0;i<list.size();i++){
            %>
            <tr>
                <td align="center"><%=list.get(i).getName()%></td>
                <td align="center"><%=list.get(i).getMobile()%></td>
            </tr>
    <%
        }
    %>
</table>
</body>
</html>