package com.xuyu.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xuyu.message.Teacher;
import com.xuyu.tool.TimeUtils;
import com.youzan.open.sdk.util.misc.TimeUtil;
import com.xuyu.message.WxApi;
import com.xuyu.message.WxContext;

/**
 * 数据库操作
 */
public class SqlHelper {
    /**
     * 设置上课的开始日期、结束日期
     * @param user_id
     * @param begintime
     * @param overtime
     */
    public void setTime(String user_id,String begintime,String overtime) {
    	try{
			MysqlConnect ps=new MysqlConnect();
		    Connection con;
	    	PreparedStatement sql; 
	    	con=ps.getConnect();
	    	sql=con.prepareStatement("update teacher_time set BeginTime = ?,OverTime = ? where user_id ='"+user_id+"'");
	 	    sql.setString(1,begintime);
	 	    sql.setString(2,overtime);
    		sql.executeUpdate();
    		sql.close();
	    	ps.CloseConnect();
	    	
		}catch(Exception e){
			e.printStackTrace();
			
		}
    }
    /**
     * 获取上课的开始日期、结束日期
     * @param user_id
     * @return
     */
    public String[] getTime(String user_id) {
        String[] value=new String [2];
		
		try {
			MysqlConnect ps=new MysqlConnect();
		    Connection con;
	    	Statement sql;
			ResultSet res;
	    	con=ps.getConnect();
	    	sql=con.createStatement();
	       	res=sql.executeQuery("select * from teacher_time where user_id='"+user_id+"'");
	        while(res.next())
	       	{
	        	value[0]=res.getString("BeginTime");
	        	value[1]=res.getString("OverTime");
	       	}
	       	sql.close();
	       	con.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		return value;
    }
    
    /**
     * 设置monday～sunday的itemNo
     * @param user_id
     * @param data
     */
    public void setdayItem(String user_id,String[] data) {
    	try{
			MysqlConnect ps=new MysqlConnect();
		    Connection con;
	    	PreparedStatement sql; 
	    	con=ps.getConnect();
	    	sql=con.prepareStatement("update teacher_time set Monday = ?,Tuesday = ?,Wednesday = ?,Thursday = ?,Friday = ?,Saturday = ?,Sunday = ? where user_id ='"+user_id+"'");
	 	    sql.setString(1,data[0]);
	 	    sql.setString(2,data[1]);
	 	    sql.setString(3,data[2]);
	 	    sql.setString(4,data[3]);
	 	    sql.setString(5,data[4]);
	 	    sql.setString(6,data[5]);
	 	    sql.setString(7,data[6]);
    		sql.executeUpdate();
    		sql.close();
	    	ps.CloseConnect();
	    	
		}catch(Exception e){
			e.printStackTrace();
			
		}
    }
    /**
     * 初始化monday～sunday的itemNo
     * @param user_id
     */
	public void initdayItem(String user_id) {
		try{
			MysqlConnect ps=new MysqlConnect();
		    Connection con;
		    PreparedStatement sql; 
	    	con=ps.getConnect();
	    	sql=con.prepareStatement("update teacher_time set Monday = ?,Tuesday = ?,Wednesday = ?,Thursday = ?,Friday = ?,Saturday = ?,Sunday = ? where user_id ='"+user_id+"'");
	    	
	    	sql.setString(1, null);
	    	sql.setString(2, null);
	    	sql.setString(3, null);
	    	sql.setString(4, null);
	    	sql.setString(5, null);
	    	sql.setString(6, null);
	    	sql.setString(7, null);
	    	
			sql.executeUpdate();
    		sql.close();
	    	ps.CloseConnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * 获得存有老师信息的teacher对象
	 * @param user_id
	 * @return
	 */
	public Teacher getTeacher(String user_id){
		Teacher teacher=null;
		try {
			MysqlConnect ps=new MysqlConnect();
			Connection con;
			Statement sql;
			con=ps.getConnect();
			ResultSet res;
			sql=con.createStatement();
			res=sql.executeQuery("select * from teacher_time where user_id='"+user_id+"'");
			while(res.next())
			{
				teacher= new Teacher(res.getString("user"),res.getString("name"),res.getDate("flag"));
			}
			sql.close();
			con.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		return teacher;
	}

	/**
	 * 更新数据表的data值
	 * @param data
	 * @param user_id
	 * @return
	 */
	public boolean updateData(String data,String user_id) {
		try{
			java.sql.Date date =new java.sql.Date(System.currentTimeMillis());
			MysqlConnect ps=new MysqlConnect();
		    Connection con;
	    	PreparedStatement sql; 
	    	con=ps.getConnect();
	 	    sql=con.prepareStatement("update teacher_time set data=?,flag=? where user_id = '"+user_id+"'");
	 	    sql.setString(1,data);
	 	    sql.setDate(2,date);
    		sql.executeUpdate();
    		sql.close();
	    	ps.CloseConnect();
	    	return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 添加新成员(先判断是否存在数据库，不存在才添加)
	 * @param userId
	 * @param user
	 * @param tel
	 * @param name
	 */
	public void addTeacher(String userId, String user, String tel, String name) {
		try {
			MysqlConnect ps=new MysqlConnect();
		    Connection con;
		    PreparedStatement sql;
		    ResultSet res;
	    	con=ps.getConnect();
			sql=con.prepareStatement("select * from teacher_time where user_id ='"+userId+"'");
	        res=sql.executeQuery();
	        if(!res.next()) {
	        	try{
				    Connection con1;
			    	PreparedStatement sql1; 
			    	con1=ps.getConnect();
			 	    sql1=con1.prepareStatement("insert into teacher_time(user_id,name,user,tel)"+"values(?,?,?,?)");
			    	sql1.setString(1,userId);
		    		sql1.setString(2,name);
		    		sql1.setString(3,user);
		    		sql1.setString(4,tel);
		    		
		    		sql1.executeUpdate();
		    		sql1.close();
			    	ps.CloseConnect();
				}catch(Exception e){
					e.printStackTrace();
				}
	        }
    		sql.close();
	    	ps.CloseConnect();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}

	public static List<Teacher> getNotSetTimeTeacher(){
		List<Teacher> list=new ArrayList<>();
		try {
			Set<Teacher> set = new HashSet<>();
			MysqlConnect ps=new MysqlConnect();
			Connection con;
			Statement sql;
			con=ps.getConnect();
			ResultSet res;
			sql=con.createStatement();
			res=sql.executeQuery("select * from teacher_time");
			while(res.next())
			{
				if(TimeUtils.getTimeDifference(new java.util.Date())<7){
					Teacher teacher= new Teacher(res.getString("user"),res.getString("name"),res.getDate("flag"));
					set.add(teacher);
				}
			}
			sql.close();
			con.close();
			List<Teacher> listAllTeacher=WxApi.getUerId(WxContext.getInstance().getAccessToken());
			for(int i=0;i<listAllTeacher.size();i++){
				if(!set.contains(listAllTeacher.get(i))){
					list.add(listAllTeacher.get(i));
					System.out.println(listAllTeacher.get(i).getUserId());
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
