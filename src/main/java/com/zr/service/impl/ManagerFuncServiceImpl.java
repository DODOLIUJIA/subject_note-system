package com.zr.service.impl;



import com.zr.dao.FunctionDao;
import com.zr.dao.impl.FunctionDaoImpl;
import com.zr.model.Function;
import com.zr.service.ManagerFuncService;

import net.sf.json.JSONArray;

public class ManagerFuncServiceImpl implements ManagerFuncService{
	FunctionDao funD = new FunctionDaoImpl();
	/**
	 * 通过用户名得到该管理员所拥有的功能
	 * @param uname
	 * @return
	 */
	@Override
public JSONArray selectFunctionsByUname(String uname,int funcpid){
		JSONArray funcs = new JSONArray();
		funcs = funD.selectFunctionsByUname(uname, funcpid);
		return funcs;
	}
}
