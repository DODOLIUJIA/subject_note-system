package com.zr.service.impl;

import com.zr.dao.RoleDao;
import com.zr.dao.impl.RoleDaoImpl;
import com.zr.service.RoleManageService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RoleManageServiceImpl implements RoleManageService{
	RoleDao rdao = new RoleDaoImpl();
	@Override
	public JSONObject getUsersByPageAndPagesize(int page, int pageSize) {
		JSONObject  jo = new JSONObject();
		jo.put("total", rdao.getUsersCount());
		jo.put("rows", rdao.getUsers((page-1)*pageSize, pageSize));
		return jo;
	}
	@Override
	public JSONArray getAllRole() {
		JSONArray roles = rdao.getAllRole();
		return roles;
	}
	@Override
	public JSONObject updataSuccessByUidAndRid(int userId, int roleId) {
		JSONObject  jo = new JSONObject();
		jo = rdao.updataSuccessByUidAndRid( userId, roleId);
		return jo;
	}

}
