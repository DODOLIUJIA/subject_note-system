package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zr.dao.UserDao;
import com.zr.util.JDBCUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserDaoImpl implements UserDao {
	Connection con = JDBCUtil.getConnection();

	@Override
	public int getUsersCount() {
		int count = 0;
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(`user`.userid) counts FROM `user` ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public JSONArray getUsers(int start, int pageSize) {
		JSONArray users = new JSONArray();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer(
				"SELECT `user`.userid,`user`.roleid,`user`.username,`user`.upassword,`user`.usersecurity, `user`.avator ");
		sql.append("FROM `user` LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, start);
			pst.setInt(2, pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				JSONObject user = new JSONObject();
				user.put("userId", rs.getInt("userid"));
				user.put("roleId", rs.getInt("roleid"));
				user.put("userName", rs.getString("username"));
				user.put("uPassword", rs.getString("upassword"));
				user.put("userSecurity", rs.getString("usersecurity"));
				user.put("avator", rs.getString("avator"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public int selectUnameByUName(String uname) {
		StringBuffer sql = new StringBuffer("");
		sql.append("select user.username from user where username = ?");
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, uname);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
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

			if (result.next()) {
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
