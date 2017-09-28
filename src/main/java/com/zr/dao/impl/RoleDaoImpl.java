package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.RoleDao;
import com.zr.model.Role;
import com.zr.util.JDBCUtil;

import net.sf.json.JSONArray;

public class RoleDaoImpl implements RoleDao{
	
	/**
	 * 得到所有角色的信息
	 * @return
	 */
	@Override
	public JSONArray selectAllRole() {
		JSONArray roles = new JSONArray();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT role.roleid,role.rolename ");
		sql.append("FROM role ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Role r = new Role();
				r.setRoleid(rs.getInt("roleid"));
				r.setRolename(rs.getString("rolename"));
				roles.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}
	 /**
	  * 通过角色名和功能id更新角色功能
	  * @param roleName 角色名
	  * @param funcsid 功能id字符串
	  * @return
	  */
	@Override
	 public void UpdataRoleFunc(String roleName,String funcsid) {
		//确认是否更新成功
		int flage = 0;
		//删除原有的功能
		deleteRoleFunc(roleName);
		//得到角色ID
		int rid = selectRoleIdByRoleName(roleName);
		String[] fids = funcsid.split(",");
		for(int i=0;i<fids.length;i++){
			insertRoleFunc(rid, fids[i]);
		}
	}
/**
 * 通过用户名删除用户现有的功能
 * @param roleName
 */
	public void deleteRoleFunc(String roleName){
		StringBuffer sql = new StringBuffer("");
		sql.append("DELETE FROM role_function ");
		sql.append("WHERE role_function.roleid IN( ");
		sql.append("SELECT role.roleid ");
		sql.append("FROM role ");
		sql.append("WHERE role.rolename =? ");
		sql.append(") ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, roleName);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 通过角色ID和功能ID重新给角色赋予功能
	 * @param roleid
	 * @param funcid
	 */
	public void insertRoleFunc(int roleid,String funcid){
		StringBuffer sql = new StringBuffer("");
		sql.append("INSERT INTO role_function VALUES( ");
		sql.append("?,? ");
		sql.append(") ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, roleid);
			pst.setString(2, funcid);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int selectRoleIdByRoleName(String rolename){
		int rid = 0;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT role.roleid ");
		sql.append("FROM role ");
		sql.append("WHERE rolename =? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
		     pst.setString(1, rolename);
		     ResultSet rs = pst.executeQuery();
		     while(rs.next()){
		    	 rid = rs.getInt("roleid");
		     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rid;		
	}

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
