package com.zr.dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface RoleDao {

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
	 * 获取所有角色 
	 * @return
	 */
	public JSONArray getAllRole();
	
	/**
	 * 修改角色
	 * @param userId 用户id
	 * @param rolrName 角色名
	 * @return 成功与否
	 */
	public JSONObject updataSuccessByUidAndRid(int userId, int rolrId);

}
