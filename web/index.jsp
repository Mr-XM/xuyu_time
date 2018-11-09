<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>

    <style>
        .namm1 + span {
            color: #ADFF2F;
            font-weight: bold;
        }

        .namm + span {
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
    <title>Insert title here</title>
</head>
<body>
<div align="center">可上课时间设置</div>
<div align="center">2018年01月01日 至 2018年01月08日</div>
<form action="Dealdata" method="post">
    <table align="center" border="1" width="100%" rules=cols frame=void style="line-height:35px;">
        <tr>
            <td align="center" class="星期一">星期一</td>
            <td align="center">
                <input type="checkbox" name="subject1" value="上午"><span>上午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject2" value="下午"><span>下午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject3" value="晚上"><span>晚上</span>
            </td>
        </tr>
        <tr>
            <td align="center">星期二</td>
            <td align="center">
                <input type="checkbox" name="subject1" value="上午"><span>上午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject2" value="下午"><span>下午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject3" value="晚上"><span>晚上</span>
            </td>
        <tr>
            <td align="center">星期三</td>
            <td align="center">
                <input type="checkbox" name="subject1" value="上午"><span>上午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject2" value="下午"><span>下午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject3" value="晚上"><span>晚上</span>
            </td>
        </tr>
        <tr>
            <td align="center">星期四</td>
            <td align="center">
                <input type="checkbox" name="subject1" value="上午"><span>上午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject2" value="下午"><span>下午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject3" value="晚上"><span>晚上</span>
            </td>
        </tr>
        <tr>
            <td align="center">星期五</td>
            <td align="center">
                <input type="checkbox" name="subject1" value="上午"><span>上午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject2" value="下午"><span>下午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject3" value="晚上"><span>晚上</span>
            </td>
        </tr>
        <tr>
            <td align="center">星期六</td>
            <td align="center">
                <input type="checkbox" name="subject1" value="上午"><span>上午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject2" value="下午"><span>下午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject3" value="晚上"><span>晚上</span>
            </td>
        </tr>
        <tr>
            <td align="center">星期日</td>
            <td align="center">
                <input type="checkbox" name="subject1" value="上午"><span>上午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject2" value="下午"><span>下午</span>
                &nbsp;
                &nbsp;
                <input type="checkbox" name="subject3" value="晚上"><span>晚上</span>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <table class=bor align="center">

        <tr>
            <td align="center"><input type="checkbox" name="subject1" value="上午" onclick="return false;"
                                      checked="checked" class="namm"><span>时间</span></td>
            <td align="left">表示有学生已经预定该时段课程</td>
        </tr>
        <tr>
            <td align="center"><input type="checkbox" name="subject1" value="上午" onclick="return false;"
                                      checked="checked" class="namm1"><span>上午</span></td>
            <td align="left">表示您可以在该时段上课</td>
        </tr>
        <tr>
            <td align="center"><input type="checkbox" name="subject1" value="上午" onclick="return false;"><span>下午</span>
            </td>
            <td align="left">留空表示不能在该时段上课</td>
        </tr>
    </table>
</form>
</body>
</html>