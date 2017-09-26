package com.zr.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.zr.dao.*;
import com.zr.util.JDBCUtil;

public class UserDaoImpl implements UserDao{
	Connection con = JDBCUtil.getConnection();
	
	@Override
	public int selectUnameByUName(String uname) {
		StringBuffer sql = new StringBuffer("");
		sql.append("select user.username from user where username = ?");
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, uname);
			ResultSet result = ps.executeQuery();
			
			if (result.next()){
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int selectUser(String uname, String password) {
		StringBuffer sql = new StringBuffer("");
		sql.append("select * ");
		sql.append("from user ");
		sql.append("where username = ? and upassword = ?");
		 
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, uname);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			
			if (result.next()){
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int insertUser(String uname, String password) {
		StringBuffer sql = new StringBuffer("");
		sql.append("insert into user ");
		sql.append("(username,upassword) ");
		sql.append("value(?,?) ");
		 
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, uname);
			ps.setString(2, password);
			int result = ps.executeUpdate();
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	
}
