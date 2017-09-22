package com.zr.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SubService {
	
	/**
	 * 获取所有年份
	 * @return
	 */
	public JSONArray getAllYears();
	
	/**
	 * 获得所有题型
	 * @return
	 */
	public JSONArray getAllSubType();
	
	/**
	 * 获取所有题目标签
	 * @return
	 */
	public JSONArray getAllSubLabel();

	/**
	 * 把题目类型数字变成翻译
	 * @param subtype
	 * @return 题型
	 */
	public String changeSubtypeIntToString(int subType);

	/**
	 * 通过页码和显示数量获得题目
	 * @param pageSize 显示数量
	 * @param page 页码
	 * @return 题目数组
	 */
	public JSONObject getSubsByPageAndPagesize(int page, int pageSize);
	
	/**
	 * 通过页码和显示数量和题目标签或的题目
	 * @param page 页码
	 * @param pageSize 数量
	 * @param subLabel 题目标签
	 * @return 题目数组
	 */
	public JSONObject getSubsByPageAndPagesizeBySubLabel(int page, int pageSize, String subLabel);

}
