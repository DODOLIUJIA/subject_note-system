package com.zr.service.impl;

import com.zr.dao.UserDao;
import com.zr.dao.impl.UserDaoImpl;
import com.zr.service.RegisterService;

public class RegisterServiceImpl implements RegisterService{

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public int checkUname(String uname) {
		// TODO Auto-generated method stub
		return userDao.selectUnameByUName(uname);
	}

	@Override
	public int registerUser(String uname, String password) {
		// TODO Auto-generated method stub
		return userDao.insertUser(uname, password);
	}

}
