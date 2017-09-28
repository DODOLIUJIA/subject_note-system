package com.zr.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface RoleManageService {
	/**
	 * 分页参数
	 * @param page
	 * @param pageSize
	 * @return 用户对象
	 */
	JSONObject getUsersByPageAndPagesize(int page, int pageSize);

	/**
	 * 获取所有角色 
	 * @return
	 */
	public JSONArray getAllRole();

	/**
	 * 修改角色
	 * @param userId 用户id
	 * @param roleId 角色名
	 * @return 成功与否
	 */
	JSONObject updataSuccessByUidAndRid(int userId, int roleId);
}
