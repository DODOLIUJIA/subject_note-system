package com.zr.dao;

import java.util.List;

import com.zr.model.Function;

import net.sf.json.JSONArray;

/**
 * 
 * @author 吴尚鑫
 *
 */
public interface FunctionDao {
	/**
	 * 通过用户名得到该管理员所拥有的功能
	 * @param uname
	 * @return
	 */
public JSONArray selectFunctionsByUname(String uname,int funcpid);
}
