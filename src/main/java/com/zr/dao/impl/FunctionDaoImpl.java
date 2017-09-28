package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.FunctionDao;
import com.zr.model.Function;
import com.zr.util.JDBCUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FunctionDaoImpl implements FunctionDao{
	/**
	 * 通过用户名得到该管理员所拥有的功能
	 * @param uname
	 * @return
	 */
	@Override
	public JSONArray selectFunctionsByUname(String uname,int funcpid) {
		JSONArray funcs = new JSONArray();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `function`.funccontent,`function`.funcid,`function`.funcname,`function`.funcpid,`function`.funcstate,`function`.funcurl ");
		sql.append("FROM `function` ");
		sql.append("INNER JOIN role_function ");
		sql.append("ON `function`.funcid = role_function.funcid ");
		sql.append("INNER JOIN role ");
		sql.append("ON role.roleid = role_function.roleid ");
		sql.append("INNER JOIN `user` ");
		sql.append("ON `user`.roleid = role.roleid ");
		sql.append("WHERE `user`.username = ? AND funcpid = ? ");
		Connection con = JDBCUtil.getConnection();
	    try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, uname);
			pst.setInt(2, funcpid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Function func = new Function();
				func.setFuncid(rs.getInt("funcid"));
				func.setFuncname(rs.getString("funcname"));
				func.setFuncpid(rs.getInt("funcpid"));
				func.setFuncstate(rs.getString("funcstate"));
				func.setFunccontnet(rs.getString("funccontent"));
				funcs.add(func);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //递归
		for(int i = 0;i<funcs.size();i++){
			String state = funcs.getJSONObject(i).getString("funcstate");
			if("close".equals(state)){
				continue;
			}else{
				int currentId =	Integer.parseInt(funcs.getJSONObject(i).getString("funcid"));
				JSONArray   children  =  this.selectFunctionsByUname(uname, currentId);
				funcs.getJSONObject(i).put("menus", children);
			}		
		}
		return funcs;
	}
	
	/**
	 * 通过角色名得到该管理员的所有功能
	 * @param uname 角色名
	 * @param funcpid 父节点id 
	 * @return
	 */
	@Override
	public JSONArray selectFunctionsByRname(String Rname, int funcpid) {
		JSONArray funcs = new JSONArray();
		StringBuffer sql = new StringBuffer("");
		List<String> funcName = new ArrayList<String>();
		sql.append("SELECT `function`.funccontent,`function`.funcid,`function`.funcname,`function`.funcpid,`function`.funcstate,`function`.funcurl ");
		sql.append("FROM `function` ");
		sql.append("WHERE funcpid = ? ");
		StringBuffer sql1 = new StringBuffer("");
		sql1.append("SELECT `function`.funcname ");
		sql1.append("FROM `function` ");
		sql1.append("INNER JOIN role_function ");
		sql1.append("ON `function`.funcid = role_function.funcid ");
		sql1.append("INNER JOIN  role ");
		sql1.append("ON role_function.roleid = role.roleid ");
		sql1.append("WHERE role.rolename=? AND funcpid=?");
		Connection con = JDBCUtil.getConnection();
	    try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, funcpid);
			ResultSet rs = pst.executeQuery();
			PreparedStatement pst1 = con.prepareStatement(sql1.toString());
			pst1.setString(1, Rname);
			pst1.setInt(2, funcpid);
			ResultSet rs1 = pst1.executeQuery();
			while(rs1.next()){
				funcName.add(rs1.getString("funcname")); 
			}

			while(rs.next()){
				JSONObject func = new JSONObject();
				func.put("id",rs.getInt("funcid"));
				func.put("text",rs.getString("funcname"));
				func.put("state",rs.getString("funcstate"));
				for(int i=0;i<funcName.size();i++){
					if(rs.getString("funcname").equals(funcName.get(i))){
						func.put("checked", true);
					}
				}
				
				funcs.add(func);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //递归
		for(int i = 0;i<funcs.size();i++){
			String state = funcs.getJSONObject(i).getString("state");
			if("close".equals(state)){
				continue;
			}else{
				int currentId =	Integer.parseInt(funcs.getJSONObject(i).getString("id"));
				//System.out.println("currentId:"+currentId);
				JSONArray   children  =  this.selectFunctionsByRname(Rname, currentId);
				funcs.getJSONObject(i).put("children", children);
			}		
		}
		return funcs;
	}

}
