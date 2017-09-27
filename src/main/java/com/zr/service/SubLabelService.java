package com.zr.service;

import net.sf.json.JSONObject;

public interface SubLabelService {
	/**
	 * 通过页码和显示数量
	 * @param pageSize 显示数量
	 * @param page 页码
	 * @return 题目标签
	 */
	public JSONObject getSubLabelsByPageAndPagesize(int page, int pageSize);

	/**
	 * 添加新的标签并且判断是否已存在
	 * @param newLabName
	 * @return 插入是否成功
	 */
	public JSONObject insertNewLabAndeExist(String newLabName);

	/**
	 * 通过s_lid删除标签
	 * @param s_lid 题标签id
	 * @return 成功与否
	 */
	public JSONObject deleteSubLabelByS_lid(int s_lid);

	/**
	 * 通过s_lid更新提标签
	 * @param updataS_LabName 新名字
	 * @param s_lid  题id
	 * @return 成功与否
	 */
	public JSONObject updataS_LabAndeExist(String updataS_LabName, int s_lid);


}
