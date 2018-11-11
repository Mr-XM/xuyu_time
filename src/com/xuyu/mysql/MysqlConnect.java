package com.xuyu.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接和断开
 */
public class MysqlConnect {
	public static final String url = "jdbc:MySQL://localhost:3306/xuyujiajiao?characterEncoding=utf8&useSSL=true";
	public static final String name = "com.mysql.cj.jdbc.Driver";
	Connection con;

	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConnect() {
		try {
			Class.forName(name);
			//System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, "root", "4230980MENG");
			//System.out.println("数据库连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 断开数据库
	 * @return
	 */
	public Connection CloseConnect() {
		try {
			con.close();
			//System.out.println("数据库断开连接");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/*public static void main(String[] args)
	{
		MysqlConnect c=new MysqlConnect();
		c.getConnect();
	    c.CloseConnect();
	}*/
}
