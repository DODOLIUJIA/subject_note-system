package com.zr.dao;

public interface UserDao {
	/**
	 * 检查数据库中是否有该用户名
	 * @param uname
	 * @return 若存在该用户名 则返回1 否则返回-1
	 */
	public int selectUnameByUName(String uname);
	
	/**
	 * 登陆时检查该用户是否存在
	 * @param uname password
	 * @return 若存在该用户 则返回1 否则返回-1
	 */
	public int selectUser(String uname,String password);
	
	/**
	 * 注册用户
	 * @param uname password
	 * @return 若注册成功 则返回1 否则返回-1
	 */
	public int insertUser(String uname, String password);
}
