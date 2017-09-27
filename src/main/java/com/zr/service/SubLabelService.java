package com.zr.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SubLabelService {
	/**
	 * 通过页码和显示数量
	 * @param pageSize 显示数量
	 * @param page 页码
	 * @return 题目标签
	 */
	public JSONObject getSubLabelsByPageAndPagesize(int page, int pageSize);
	
	public JSONArray getSubLabelsCount();

}
