package com.zr.dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface SubLabelDao {


	/**
	 * 获取题标签的数量
	 * @return
	 */
	public int getSubLabelsCount();
	
	/**
	 * 获取题标签
	 * @param start 分页参数
	 * @param pageSize 分页参数
	 * @return 题标签json数组
	 */
	public JSONArray getSubLabels(int start, int pageSize);

	/**
	 * 通过提标签id查询每个标签下题有多少个
	 * @param s_lid 提标签id
	 * @return 每个标签下题有多少个
	 */
	public int getSubCountByS_lid(int s_lid);

	/**
	 * 添加新的标签并且判断是否已存在
	 * @param newLabName
	 * @return 插入是否成功
	 */
	public JSONObject insertNewLabAndeExist(String newLabName);
	
	/**
	 * 判断是否已存在此标签
	 * @param newLabName 新的标签名
	 * @return true 已存在，false 不存在。
	 */
	public boolean subLabelExist(String newLabName);

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
