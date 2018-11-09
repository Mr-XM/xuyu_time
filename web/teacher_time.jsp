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

        #aaaa + span {
            color: #FF7F50;
            font-weight: bold;
        }

        #aaa + span {
            color: #FF7F50;
            font-weight: bold;
        }

        .bor {
            border: 1px dashed #F00;
            width: 90%;
            height: 60px;
            margin-top: 10px
        }

    </style>
    <jsp:useBean id="sqlhelper" class="com.xuyu.tool.SqlHelper">

    </jsp:useBean>
    <jsp:useBean id="myurl" class="com.xuyu.tool.MyURL">

    </jsp:useBean>
    <jsp:useBean id="timeutils" class="com.xuyu.tool.TimeUtils">

    </jsp:useBean>
    <jsp:useBean id="GOauth2core" class="com.xuyu.message.GOauth2Core">

    </jsp:useBean>
    <jsp:useBean id="md5" class="com.xuyu.tool.MD5">

    </jsp:useBean>
    <jsp:useBean id="WxApi" class="com.xuyu.message.WxApi">

    </jsp:useBean>
    <%@page import="java.util.*" %>
    <%@page import="com.xuyu.youzan.YouzanApi" %>
    <%@page import="com.xuyu.message.WxContext" %>
    <%@page import="com.xuyu.message.Teacher" %>
    <%@page import="org.apache.commons.lang3.StringUtils" %>
    <%@page import="com.xuyu.message.Status" %>
    <%@page import="com.xuyu.tool.ErrHandler" %>
    <%
        /**
         *无效链接
         */
        int urlInvalid = 0;

        /**
         * 页面失效
         */
        int pageInvalid =1;

        /**
         * 链接与你身份不匹配
         */
        int urlMatch = 2;

        /**
         * 有赞商城编码错误
         */
        int youzanCodeError = 3;
        String encryptedUserId = null;
        String isOauthFlag = request.getParameter("f");
        String userId = null;
        String state = null;
        String token = null;
        if (isOauthFlag.equals(Status.FLAG_UN_OAUTH_CERTIFICATE)) {
            encryptedUserId = request.getParameter("u");
            token = request.getParameter("t");
            state = encryptedUserId + ":" + token;
            String redirectUrl = GOauth2core.getCode(state);
            response.sendRedirect(redirectUrl);//传的就是加密的user_id
            return;
        }
        if (isOauthFlag.equals(Status.FLAG_OAUTH_CERTIFICATE)) {
            String userIdAndToken[] = request.getAttribute("state").toString().split(":");
            encryptedUserId = userIdAndToken[0]; //获得的就是加密的user_id
            token = userIdAndToken[1];
            userId = request.getAttribute("userId").toString();//获得的就是未加密的user_id
        } else {
            out.print(ErrHandler.Error(urlInvalid));
            return;
        }
        if (myurl.isValid(myurl.stampToDate(token)) == false) {
            out.println(ErrHandler.Error(pageInvalid));
            return;
        }
        if (encryptedUserId.isEmpty()) {
            return;
        }
        if (md5.getMD5(userId).equals(encryptedUserId)) {
            String AccessToken = WxContext.getInstance().getAccessToken();
            Teacher teacher;
            teacher = WxApi.getTeacher(AccessToken, userId);
            sqlhelper.addTeacher(encryptedUserId, teacher.getUserId(), 0, teacher.getMobile(), teacher.getName());
        } else {
            out.println(ErrHandler.Error(urlMatch));
            return;
        }
        Teacher teacher = sqlhelper.getTeacher(encryptedUserId);
        int isSetTimeFlag = teacher.getFlag();
        userId = teacher.getUserId();
        String name = teacher.getName();
    %>
    <title>须臾私教-<%=name %>
    </title>
</head>
<body>
<%
    if (isSetTimeFlag == Status.FLAG_SETTED) {
        response.sendRedirect("showSuccess.jsp?u=" + encryptedUserId + "&n=" + name);
        return;

    } else {

        String itemId = YouzanApi.getItem_id(userId + ":" + name);
        if (itemId == null) {
            out.print(ErrHandler.Error(youzanCodeError));
            return;
        }
%>


<h3 align="center">可上课时间设置</h3>
<%
    String beginTime = timeutils.getNextweekday(new Date(), timeutils.difference(timeutils.getWeekOfDate(new Date())) - 6);
    String overTime = timeutils.getNextweekday(new Date(), timeutils.difference(timeutils.getWeekOfDate(new Date())) + 1);
    sqlhelper.setTime(encryptedUserId, beginTime, overTime);
%>
<div align="center"><%=beginTime %> 至 <%=overTime %>
</div>
<form action="setTimeSubmit.jsp?u=<%=encryptedUserId%>&n=<%=name %>" method="post">
    <table align="center" border="1" width="100%" rules=cols frame=void style="line-height:35px;">
        <%

            Set choosed = new HashSet();
            String[] CNT_CHOOSED = YouzanApi.getCNT_CHOOSED(Long.parseLong(itemId)).split(",");
            if (CNT_CHOOSED != null || (CNT_CHOOSED == null && CNT_CHOOSED.length != 0)) {
                for (int i = 0; i < CNT_CHOOSED.length; i++) {
                    if (!StringUtils.isBlank(CNT_CHOOSED[i])) {
                        int a = Integer.valueOf(CNT_CHOOSED[i]);
                        choosed.add(a);
                    }
                }
            }
            int k = 0;
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
            <td align="center">
                <%

                    for (int j = 0; j < 3; j++) {
                        if (choosed.contains(k)) {%>
                <input type="checkbox" id="aaaa" name="time1" onclick="return false;" value="<%=k++%>" checked>

                <%
                } else {%>
                <input type="checkbox" id="zxc" class="2" name="time1" value="<%=k++%>">
                <%
                    }
                %>
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
                <% }
                %>
            </td>
        </tr>

        <%
            }
        %>
    </table>
    <br>
    <table class=bor align="center">

        <tr>
            <td align="center"><input type="checkbox" id="aaa" value="上午" onclick="return false;"
                                      checked="checked" class="namm"><span>时间</span></td>
            <td align="left">表示有学生已经预定该时段课程</td>
        </tr>
        <tr>
            <td align="center"><input type="checkbox" value="上午" onclick="return false;"
                                      checked="checked"><span>上午</span></td>
            <td align="left">表示您可以选择在该时段上课</td>
        </tr>
        <tr>
            <td align="center"><input type="checkbox" value="上午" onclick="return false;"><span>下午</span>
            </td>
            <td align="left">留空表示不在该时段上课</td>
        </tr>
    </table>
    <br>
    <br>
    <div align="center"><input type="submit" value="提交"
                               style="width:50px; line-height:22px; background-color:#7FFFD4; border:none; display:block; color:#000000;">
    </div>
</form>


<%


    }


%>
</body>
</html>