package com.zr.service;

import net.sf.json.JSONObject;

/**
 * 
 * @author zhang
 *
 */
public interface LoginService {

	public JSONObject selectUser(String uname,String password);
}
