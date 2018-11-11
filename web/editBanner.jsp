<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/xuyu.css"/>
    <title>文案编辑</title>
</head>
<body id="pageBody">
<h3 align="center">请将您要发送的内容在此页面编辑</h3>
<form action="CopywriteServlet" method="post">
    <br>
    <div align="left"><span>标 题:</span><input type="text" name="text"></div>
    <br>
    <div align="left"><span style="vertical-align: top">内 容:</span><textarea id="contentTextarea" rows="20"
                                                                             name="textarea"></textarea></div>
    <br>
    <div style="width:86%"><input type="submit" id="submitButton" value="添加"></div>
    <br>
    <br>
</form>
</body>
</html>