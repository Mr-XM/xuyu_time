package com.xuyu.mysql;

import java.sql.*;

/**
 * 数据库连接和断开
 */
public class MysqlConnect {
    public static final String url = "jdbc:MySQL://localhost:3306/xuyujiajiao?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=true";
    public static final String name = "com.mysql.cj.jdbc.Driver";

    /**
     * 连接数据库
     *
     * @return
     */
    public Connection getConnect() {
        Connection con = null;
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
     *
     * @return
     */
    public static void closeConnect(Connection conn,PreparedStatement pst,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("数据库连接关闭出错!");
            }
        }
        if(pst!=null){
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println("数据库连接关闭出错!");
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("数据库连接关闭出错!");
            }
        }
    }
}
