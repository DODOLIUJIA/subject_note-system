package com.zr.service;

public interface RegisterService {
	
	public int checkUname(String uname);
	
	public int registerUser(String uname, String password);

}
