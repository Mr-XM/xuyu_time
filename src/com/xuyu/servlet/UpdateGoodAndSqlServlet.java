package com.xuyu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xuyu.message.Teacher;


import com.xuyu.tool.Change;
import com.xuyu.mysql.SqlHelper;
import com.xuyu.youzan.YouzanApi;


/**
 * 数据处理，从老师设置的时间界面获取对应的值，转换成有赞库存里面对应的值，并更新有赞库存。
 * 也将从老师设置的时间界面获取对应的值，找到其中标志为 IDLE 保存在对应的数据库表项里
 */

public class UpdateGoodAndSqlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodAndSqlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        String userId = request.getParameter("u");    //从setTimeSubmit.jsp获取的userId
        String[] data1 = request.getParameterValues("time1");  ////从setTimeSubmit.jsp获取的用户选择有空上课的数据


        Change chg = new Change();
        SqlHelper sqh = new SqlHelper();
        int[] HasClass = new int[21];  //存储老师设置有课的标志，有课对应时间号，时间号：除3取整为星期几，取余为上午/下午/晚上

        //对存储有课标志的数组进行初始化
        for (int i = 0; i < HasClass.length; i++) {
            HasClass[i] = 22;
        }

        //对存储有课标志的数组进行赋值
        for (int i = 0; i < data1.length; i++) {
            HasClass[Integer.parseInt(data1[i])] = Integer.parseInt(data1[i]);
        }

        //根据有课标志获取全部标志为IDLE的ItemNo
        String[] ItemNo = chg.getIDLEItemNo(HasClass);

        //数据库初始化商家编码
        sqh.initdayItem(userId);
        //数据库将商家编码添加到monday～sunday
        sqh.setdayItem(userId, ItemNo);


        StringBuffer s = new StringBuffer();
        String data;
        for (int i = 0; i < data1.length; i++) {
            if (i > 0 && i < data1.length) {
                s.append(",");
            }
            s.append(data1[i]);
            //System.out.println(data1[i]);
        }
        data = s.toString();

        //更新数据库
        sqh.updateData(data, userId);

        Teacher teacher =sqh.getTeacher(userId);
        String user = teacher.getUserId();
        String name = teacher.getName();
        //System.out.println(user+":"+name);


        //同步到有赞商城
        String item = YouzanApi.getItem_id(user + ":" + name);
        if (YouzanApi.updateGood(item, YouzanApi.creatSku_stocks(YouzanApi.getskus(Long.parseLong(item)), data1, YouzanApi.getCNT_CHOOSED(Long.parseLong(item))))) {
            response.sendRedirect("showSuccess.jsp?u=" + userId);
        } else {
            out.print("更新有赞库存失败！");
        }

    }
}


