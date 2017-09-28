package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zr.dao.RoleDao;
import com.zr.util.JDBCUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RoleDaoImpl implements RoleDao {

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
		StringBuffer sql = new StringBuffer("SELECT `user`.userid,`user`.username,`role`.rolename ");
		sql.append("FROM `user` LEFT JOIN `role` ");
		sql.append("ON `user`.roleid = `role`.roleid ");
		sql.append("WHERE `user`.userid >1 ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, start);
			pst.setInt(2, pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				JSONObject user = new JSONObject();
				user.put("userId", rs.getInt("userid"));
				user.put("userName", rs.getString("username"));
				user.put("roleName", rs.getString("rolename"));
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
	public JSONArray getAllRole() {
		JSONArray roles = new JSONArray();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("SELECT role.roleid, role.rolename ");
		sql.append("FROM role ");
		sql.append("WHERE role.roleid>1 ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				JSONObject role = new JSONObject();
				role.put("id", rs.getInt("roleid"));
				role.put("text", rs.getString("rolename"));
				roles.add(role);
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
		return roles;
	}

	@Override
	public JSONObject updataSuccessByUidAndRid(int userId, int rolrId) {
		JSONObject jo = new JSONObject();
		boolean flag = false;
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("UPDATE `user` SET `user`.roleid = ? ");
		sql.append("WHERE `user`.userid = ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, rolrId);
			pst.setInt(2, userId);
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		jo.put("success", flag);
		return jo;
	}
	
}
