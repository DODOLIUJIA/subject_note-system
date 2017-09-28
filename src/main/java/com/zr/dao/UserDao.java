package com.zr.dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * @author zhang
 *
 */
public interface UserDao {

	/**
	 * 计算用户数量
	 * @return 用户数量
	 */
	public int getUsersCount();

	/**
	 * 获取用户 通过分页
	 * @param start
	 * @param pageSize
	 * @return 用户
	 */
	public JSONArray getUsers(int start, int pageSize);
	
	/**
	 * author:zxl
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
	public JSONObject selectUser(String uname,String password);
	
	/**
	 * 注册用户
	 * @param uname password
	 * @return 若注册成功 则返回1 否则返回-1
	 */
	public int insertUser(String uname, String password);

}
