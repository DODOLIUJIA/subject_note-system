package com.zr.service.impl;
import com.zr.dao.UserDao;
import com.zr.dao.impl.UserDaoImpl;
import com.zr.service.LoginService;

import net.sf.json.JSONObject;

/**
 * 
 * @author zhang
 *
 */
public class LoginServiceImpl implements LoginService{

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public JSONObject selectUser(String uname, String password) {
		// TODO Auto-generated method stub
		return userDao.selectUser(uname, password);
	}

}
