package com.zr.service.impl;

import com.zr.dao.UserDao;
import com.zr.dao.impl.UserDaoImpl;
import com.zr.service.UserService;

import net.sf.json.JSONObject;

public class UserServiceImpl implements UserService{
	UserDao udao = new UserDaoImpl();
	@Override
	public JSONObject getUsersByPageAndPagesize(int page, int pageSize) {
		JSONObject  jo = new JSONObject();
		jo.put("total", udao.getUsersCount());
		jo.put("rows", udao.getUsers((page-1)*pageSize, pageSize));
		return jo;
	}

}
