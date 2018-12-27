package com.xuyu.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接和断开
 */
public class MysqlConnect {
	public static final String url = "jdbc:MySQL://localhost:3306/xuyujiajiao?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=true";
	public static final String name = "com.mysql.cj.jdbc.Driver";
	Connection con;

	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConnect() {
		try {
			Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, "root", "dai911029");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 断开数据库
	 * @return
	 */
	public Connection closeConnect() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/*public static void main(String[] args)
	{
		MysqlConnect c=new MysqlConnect();
		c.getConnect();
	    c.closeConnect();
	}*/
}
