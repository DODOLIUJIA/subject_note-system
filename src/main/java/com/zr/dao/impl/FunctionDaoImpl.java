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

}
