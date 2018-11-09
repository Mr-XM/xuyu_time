<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
    <style>
        input:checked + span {
            color: #ADFF2F;
            font-weight: bold;
        }

        #asd + span {
            color: #FF7F50;
            font-weight: bold;
        }

        #asd1 + span {
            color: #ADFF2F;
            font-weight: bold;
        }

        #replace {
            font-size: 14px;
            font-family: 宋体;

            width: 70px;
            height: 28px;
            line-height: 28px;

            color: white;
            background-color: #7FFFD4;
            border-radius: 6px;
            border: 0;

            float: left;
        }

        #submit1 {
            font-family: 宋体;
            font-size: 14px;
            width: 70px;
            height: 28px;
            line-height: 28px;
            color: white;
            background-color: #7FFFD4;
            border-radius: 6px;
            border: 0;

            float: right;

        }
    </style>
    <%
        String name = new String(request.getParameter("n").getBytes("ISO-8859-1"), "UTF-8");
    %>
    <%@page import="com.xuyu.message.Teacher" %>
    <jsp:useBean id="sqlhelper" class="com.xuyu.tool.SqlHelper"></jsp:useBean>
    <%
        String EncryptedUserId = request.getParameter("u");
        Teacher teacher = sqlhelper.getTeacher(EncryptedUserId);
        int flag = teacher.getFlag();
        String userId = teacher.getUserId();
    %>
    <title>须臾私教-<%=name%>
    </title>
</head>
<body>
<%@page import="java.util.*" %>
<%@page import="com.xuyu.youzan.YouzanApi" %>
<%

    if (flag == 0) {
%>
<h3 align="center">请确定您的可上课时间</h3>
<form action="DataDeal?u=<%=EncryptedUserId %>" method="post">
    <table align="center" border="1" width="100%" rules=cols frame=void style="line-height:35px;">
        <%
            String itemId = YouzanApi.getItem_id(userId + ":" + name);
            String[] CNT_CHOOSED = YouzanApi.getCNT_CHOOSED(Long.parseLong(itemId)).split(",");
            Set choosed = new HashSet();
            Set cnt_choose = new HashSet();
            int k = 0;
            request.setCharacterEncoding("UTF-8");
            String[] value = request.getParameterValues("time1");

            for (int j = 0; j < value.length; j++) {
                int a = Integer.valueOf(value[j]);
                choosed.add(a);
            }
            if (CNT_CHOOSED != null || (CNT_CHOOSED == null && CNT_CHOOSED.length != 0)) {
                for (int i = 0; i < CNT_CHOOSED.length; i++) {
                    if (!CNT_CHOOSED[i].equals("")) {
                        int b = Integer.valueOf(CNT_CHOOSED[i]);
                        cnt_choose.add(b);
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

                <input type="checkbox" id="asd" name="time1" onclick="return false;" value="<%=k++%>" checked>
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
                <input type="checkbox" id="asd1" name="time1" onclick="return false;" value="<%=k++%>" checked>
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
                <input type="checkbox" id="zxc" class="2" onclick="return false;" name="time1" value="<%=k++%>"
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
        <input type="button" style="margin-left:20%;" id="replace" value="返回" onclick="window.history.back(-1)"/>
        <input type="submit" id="submit1" value="确定提交"/>

    </div>
</form>


<%


    } else {
        response.sendRedirect("showSuccess.jsp?u=" + EncryptedUserId + "&n=" + name);
    }


%>
</body>
</html>