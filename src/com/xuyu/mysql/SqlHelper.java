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
	    	PreparedStatement preparedStatement;
	    	con=ps.getConnect();
	    	preparedStatement=con.prepareStatement("update teacher_time set BeginTime = ?,OverTime = ? where user_id ='"+user_id+"'");
	 	    preparedStatement.setString(1,begintime);
	 	    preparedStatement.setString(2,overtime);
    		preparedStatement.executeUpdate();


	    	ps.closeConnect(con,preparedStatement,null);
	    	
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
	    	PreparedStatement statement;
			ResultSet res;
	    	con=ps.getConnect();
	    	String sql="select * from teacher_time where user_id='"+user_id+"'";
	    	statement=con.prepareStatement(sql);
	       	res=statement.executeQuery();
	        while(res.next())
	       	{
	        	value[0]=res.getString("BeginTime");
	        	value[1]=res.getString("OverTime");
	       	}

			ps.closeConnect(con,statement,res);
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
	    	PreparedStatement preparedStatement;
	    	con=ps.getConnect();
	    	preparedStatement=con.prepareStatement("update teacher_time set Monday = ?,Tuesday = ?,Wednesday = ?,Thursday = ?,Friday = ?,Saturday = ?,Sunday = ? where user_id ='"+user_id+"'");
	 	    preparedStatement.setString(1,data[0]);
	 	    preparedStatement.setString(2,data[1]);
	 	    preparedStatement.setString(3,data[2]);
	 	    preparedStatement.setString(4,data[3]);
	 	    preparedStatement.setString(5,data[4]);
	 	    preparedStatement.setString(6,data[5]);
	 	    preparedStatement.setString(7,data[6]);

	    	ps.closeConnect(con,preparedStatement,null);
	    	
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
		    PreparedStatement preparedStatement;
	    	con=ps.getConnect();
	    	preparedStatement=con.prepareStatement("update teacher_time set Monday = ?,Tuesday = ?,Wednesday = ?,Thursday = ?,Friday = ?,Saturday = ?,Sunday = ? where user_id ='"+user_id+"'");
	    	
	    	preparedStatement.setString(1, null);
	    	preparedStatement.setString(2, null);
	    	preparedStatement.setString(3, null);
	    	preparedStatement.setString(4, null);
	    	preparedStatement.setString(5, null);
	    	preparedStatement.setString(6, null);
	    	preparedStatement.setString(7, null);

	    	ps.closeConnect(con,preparedStatement,null);
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
			PreparedStatement preparedStatement;
			con=ps.getConnect();
			ResultSet res;
			String sql="select * from teacher_time where user_id='"+user_id+"'";
			preparedStatement=con.prepareStatement(sql);
			res=preparedStatement.executeQuery();
			while(res.next())
			{
				teacher= new Teacher(res.getString("user"),res.getString("name"),res.getDate("flag"));
			}

			ps.closeConnect(con, preparedStatement,res);
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
	    	PreparedStatement preparedStatement;
	    	con=ps.getConnect();
	 	    preparedStatement=con.prepareStatement("update teacher_time set data=?,flag=? where user_id = '"+user_id+"'");
	 	    preparedStatement.setString(1,data);
	 	    preparedStatement.setDate(2,date);
    		preparedStatement.executeUpdate();


	    	ps.closeConnect(con,preparedStatement,null);
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
		    PreparedStatement preparedStatement;
		    ResultSet res;
	    	con=ps.getConnect();
			preparedStatement=con.prepareStatement("select * from teacher_time where user_id ='"+userId+"'");
	        res=preparedStatement.executeQuery();
	        if(!res.next()) {
	        	try{
				    Connection con1;
			    	PreparedStatement preparedStatement1;
			    	con1=ps.getConnect();
			 	    preparedStatement1=con1.prepareStatement("insert into teacher_time(user_id,name,user,tel)"+"values(?,?,?,?)");
			    	preparedStatement1.setString(1,userId);
		    		preparedStatement1.setString(2,name);
		    		preparedStatement1.setString(3,user);
		    		preparedStatement1.setString(4,tel);
		    		
		    		preparedStatement1.executeUpdate();

			    	ps.closeConnect(con,preparedStatement1,null);
				}catch(Exception e){
					e.printStackTrace();
				}
	        }
	    	ps.closeConnect(con,preparedStatement,res);
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}

	public static List<Teacher> getNotSetTimeTeacher(){
		List<Teacher> list=new ArrayList<>();
		try {
			Set<String> set = new HashSet<>();
			MysqlConnect ps=new MysqlConnect();
			Connection con;
			PreparedStatement preparedStatement;
			con=ps.getConnect();
			ResultSet res;
			String sql="select * from teacher_time";
			preparedStatement=con.prepareStatement(sql);
			res=preparedStatement.executeQuery();
			while(res.next())
			{
				if(TimeUtils.getTimeDifference(new java.util.Date())<7){
					Teacher teacher= new Teacher(res.getString("user"),res.getString("name"),res.getDate("flag"));
					set.add(teacher.getUserId());
				}
			}
			ps.closeConnect(con,preparedStatement,res);
			List<Teacher> listAllTeacher=WxApi.getUerId(WxContext.getInstance().getAccessToken());
			for(int i=0;i<listAllTeacher.size();i++){
				if(!set.contains(listAllTeacher.get(i).getUserId())){
					list.add(listAllTeacher.get(i));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
