package com.zr.service;

import net.sf.json.JSONObject;

public interface UserService {

	/**
	 * 分页参数
	 * @param page
	 * @param pageSize
	 * @return 用户对象
	 */
	JSONObject getUsersByPageAndPagesize(int page, int pageSize);

}
