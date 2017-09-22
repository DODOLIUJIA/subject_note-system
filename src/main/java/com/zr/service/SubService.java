package com.zr.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SubService {

	/**
	 * 通过页码和显示数量获得题目
	 * @param pageSize 显示数量
	 * @param page 页码
	 * @return 题目数组
	 */
	public JSONObject getSubsByPageAndPagesize(int page, int pageSize);
	
	/**
	 * 获取所有年份
	 * @return
	 */
	public JSONArray getAllYears();
	
	/**
	 * 获得所有题型
	 * @return
	 */
	public JSONArray getAllSubtype();
	
	/**
	 * 把题目类型数字变成翻译
	 * @param subtype
	 * @return 题型
	 */
	public String changeSubtypeIntToString(int subtype);

}
