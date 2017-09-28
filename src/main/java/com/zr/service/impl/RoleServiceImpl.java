package com.zr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zr.dao.FunctionDao;
import com.zr.dao.RoleDao;
import com.zr.dao.impl.FunctionDaoImpl;
import com.zr.dao.impl.RoleDaoImpl;
import com.zr.model.Role;
import com.zr.service.RoleService;

import net.sf.json.JSONArray;

public class RoleServiceImpl implements RoleService{
	RoleDao rd = new RoleDaoImpl();
	FunctionDao fd = new FunctionDaoImpl();
	/**
	 * 得到所有角色的信息
	 * @return
	 */
	@Override
	public JSONArray selectAllRole() {
		JSONArray roles = new JSONArray();
		roles = rd.selectAllRole();
		return roles;
	}
	/**
	  * 通过角色名得到该角色的功能
	  * @param rname 角色名
	  * @return
	  */
	@Override
	public JSONArray selectFuncsByRoleName(String Rname) {
		JSONArray j = fd.selectFunctionsByRname(Rname, 0);
		return j;
	}
	 /**
	  * 通过角色名和功能id更新角色功能
	  * @param roleName 角色名
	  * @param funcsid 功能id字符串
	  * @return
	  */
	@Override
	public void UpdataRoleFunc(String roleName, String funcsid) {
    rd.UpdataRoleFunc(roleName, funcsid);
	}

}
