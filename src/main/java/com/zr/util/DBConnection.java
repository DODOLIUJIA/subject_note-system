package com.zr.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * @author JACK
 * @date 2017/9/4
 */
public class DBConnection {
	/**
	 * @DBURL 数据库地址
	 * @USERNAME 数据库登录用户名
	 * @PASSWORD 数据库登录密码
	 * @DBDRIVER 数据库驱动路径
	 */
	//private final static String DBURL = "jdbc:mysql://182.61.32.204/sub_note?useUnicode=true&characterEncoding=utf8";
	private final static String DBURL = "jdbc:mysql://172.18.23.77:3306/sub_note?useUnicode=true&characterEncoding=utf8";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * 通过反射获得一个数据库链接
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Class.forName(DBDRIVER);
			Connection conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void CloseConnection(Connection conn,PreparedStatement pre) throws SQLException{
		pre.close();
		conn.close();
	}
	
	public static void CloseConnection(Connection conn,PreparedStatement pre,ResultSet res) throws SQLException{
		res.close();
		pre.close();
		conn.close();
	}
	
	public static void main(String[] args) {
		System.out.println(DBConnection.getConnection());
	}
}
