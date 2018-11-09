package com.xuyu.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.TimerTask;


/**
 * 该类的作用防止不使用定时发送消息功能导致相应数据不能重置而导致老师不能设置课程
 */
public class MysqlTableInit extends TimerTask {
    /**
     * 将设置时间标志变成初始状态
     */
    public void changeflag() {
        try {
            MysqlConnect ps = new MysqlConnect();
            Connection con;
            PreparedStatement sql;
            con = ps.getConnect();
            sql = con.prepareStatement("update teacher_time set flag = ? where flag = 1");
            sql.setInt(1, 0);
            sql.executeUpdate();
            sql.close();
            ps.CloseConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除未设置时间的人员
     */
    public void dele() {
        try {
            MysqlConnect ps = new MysqlConnect();
            Connection con;
            PreparedStatement sql;
            con = ps.getConnect();
            sql = con.prepareStatement("select * from teacher_time");
            sql.executeUpdate("delete from teacher_time where flag = 0");
            con.close();
            sql.close();
            ps.CloseConnect();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static boolean isRunning = false;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (!isRunning) {
            isRunning = true;
            dele();
            changeflag();
            //System.out.println("初始化执行成功");
            isRunning = false;
        }
    }

}
