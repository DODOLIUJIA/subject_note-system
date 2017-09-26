package com.zr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	  private  final  static String DBURL = "jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=UTF8";
	  private  final  static String  USERNAME = "root";
	  private  final  static String  PASSWORD = "1996116";
			
	  public  static  Connection    getConnection(){
		    Connection  con  = null;
		    try {
				Class.forName("com.mysql.jdbc.Driver");
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
	  
	  public   static  void   closeJDBC(PreparedStatement  pst,Connection  con){
		    if(pst!=null){
		    	try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    if(con!=null){
		    	try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	  }
}
