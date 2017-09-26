package com.zr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	 //1.数据库地址  (根据不同的数据标准是不一样)
	  //private  final  static String DBURL = "jdbc:mysql://172.18.23.77:3306/sub_note?useUnicode=true&characterEncoding=UTF8";
	  private  final  static String DBURL = "jdbc:mysql://182.61.32.204:3306/sub_note?useUnicode=true&characterEncoding=UTF8";
	  //2.设置用户和密码
	  private  final  static String  USERNAME = "zxy";
	  private  final  static String  PASSWORD = "zxyzxy";
	  //3.设置驱动名称 (根据不同的数据标准是不一样)
	  private  final  static String  DBDRIVER = "com.mysql.jdbc.Driver";
	  /**
	   * 获取数据库连接
	   * @return  返回数据库连接
	   */
	  public  static  Connection  getConnection(){
		  Connection  con = null;
		  try {
			Class.forName(DBDRIVER);
			con  =  DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return  con;
			
	  }
	  /**
	   * 关闭连接
	   * @param st
	   * @param con
	   * @throws SQLException
	   */
	  public static void  closeJDBC(Statement st,Connection  con) throws SQLException{
		    if(st!=null){
		    	st.close();
		    }
		    if(con!=null){
		    	con.close();
		    }
		   
	  }
}
