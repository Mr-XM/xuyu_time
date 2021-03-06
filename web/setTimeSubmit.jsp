<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/xuyu.css"/>
    <%@page import="com.xuyu.message.Teacher" %>
    <jsp:useBean id="sqlhelper" class="com.xuyu.mysql.SqlHelper"></jsp:useBean>
    <%
        String EncryptedUserId = request.getParameter("u");
        Teacher teacher = sqlhelper.getTeacher(EncryptedUserId);
        Date flag = teacher.getFlag();
        String userId = teacher.getUserId();
        String name = teacher.getName();
    %>
    <title>须臾私教-<%=name%>
    </title>
</head>
<body>
<%@page import="java.util.*" %>
<%@page import="com.xuyu.youzan.YouzanApi" %>
<%@ page import="com.xuyu.tool.TimeUtils" %>
<%

    if (TimeUtils.getTimeDifference(flag) > 7 || flag == null) {
%>
<h3 align="center">请确定您的可上课时间</h3>
<form action="UpdateGoodAndSqlServlet?u=<%=EncryptedUserId%>" method="post">
    <table align="center" border="1" width="100%" rules=cols frame=void style="line-height:35px;">
        <%
            String itemId = YouzanApi.getItem_id(userId + ":" + name);
            Set choosed = new HashSet();
            Set cnt_choose = new HashSet();
            int k = 0;
            request.setCharacterEncoding("UTF-8");
            if (request.getParameterValues("time1") != null) {
                String[] value = request.getParameterValues("time1");
                if (!(value == null && (value != null && value.length == 0))) {
                    for (int j = 0; j < value.length; j++) {
                        int a = Integer.valueOf(value[j]);
                        choosed.add(a);
                    }
                }
            }
            String cnt = YouzanApi.getCNT_CHOOSED(Long.parseLong(itemId));
            if (cnt != null) {
                String[] CNT_CHOOSED = cnt.split(",");
                if (!(CNT_CHOOSED == null && (CNT_CHOOSED != null && CNT_CHOOSED.length == 0))) {
                    for (int i = 0; i < CNT_CHOOSED.length; i++) {
                        if (!CNT_CHOOSED[i].equals("")) {
                            int b = Integer.valueOf(CNT_CHOOSED[i]);
                            cnt_choose.add(b);
                        }
                    }
                }
            }

            for (int i = 0; i < 7; i++) {%>
        <tr>
            <td align="center">
                <%
                    switch (i % 7) {
                        case 0:
                            out.print("星期一");
                            break;
                        case 1:
                            out.print("星期二");
                            break;
                        case 2:
                            out.print("星期三");
                            break;
                        case 3:
                            out.print("星期四");
                            break;
                        case 4:
                            out.print("星期五");
                            break;
                        case 5:
                            out.print("星期六");
                            break;
                        case 6:
                            out.print("星期日");
                            break;
                    }
                %>
            </td>
            <td align="center" style="border-bottom:1px dashed #000000;">
                <%
                    for (int j = 0; j < 3; j++) {
                        if (choosed.contains(k)) {
                            if (cnt_choose.contains(k)) {
                %>

                <input type="checkbox" id="defaultCheckedCheckbox" name="time1" onclick="return false;" value="<%=k++%>"
                       checked>
                <span>
			            			<%
                                        switch (j % 3) {
                                            case 0:
                                                out.print("上午");
                                                break;
                                            case 1:
                                                out.print("下午");
                                                break;
                                            case 2:
                                                out.print("晚上");
                                                break;
                                        }%>
			            		</span>
                &nbsp;
                &nbsp;
                <% } else {%>
                <input type="checkbox" id="ownCheckedCheckbox" name="time1" onclick="return false;" value="<%=k++%>"
                       checked>
                <span>
		            		<%
                                switch (j % 3) {
                                    case 0:
                                        out.print("上午");
                                        break;
                                    case 1:
                                        out.print("下午");
                                        break;
                                    case 2:
                                        out.print("晚上");
                                        break;
                                }%>
		            			</span>
                &nbsp;
                &nbsp;
                <%
                    }
                } else {
                %>
                <input type="checkbox" onclick="return false;" name="time1" value="<%=k++%>"
                       style="visibility:hidden">
                <span style="visibility:hidden">
		            		<%
                                switch (j % 3) {
                                    case 0:
                                        out.print("上午");
                                        break;
                                    case 1:
                                        out.print("下午");
                                        break;
                                    case 2:
                                        out.print("晚上");
                                        break;
                                }%>
		            		</span>
                &nbsp;
                &nbsp;
                <%
                    }
                %>

                <% }
                %>

            </td>
        </tr>

        <%
            }
        %>
    </table>
    <br>
    <br>
    <div style="width:80%">
        <input type="button" style="margin-left:20%;" id="replaceButton" value="返回" onclick="window.history.back(-1)"/>
        <input type="submit" id="submitButton" value="确定提交"/>

    </div>
</form>


<%


    } else {
        
        response.sendRedirect("showSuccess.jsp?u=" + EncryptedUserId + "&n=" + name);
    }


%>
</body>
</html>