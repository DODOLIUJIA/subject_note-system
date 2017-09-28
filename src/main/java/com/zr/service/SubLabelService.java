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

	/**
	 * 获得所有题目标签标签的ID
	 * @return返回一个装有所有题目标签ID的INT类型的数组
	 */
	public int[] getAllSubjectLabelID();
	
	/**
	 * 调用该方法更新s_label表中所有的subcount字段
	 * @return更新成功返回true，更新失败返回false
	 */
	public boolean updateAllSubjectLabelCount();
	
	/**
	 * 获得标签的ID以及标签的名字（ID=VALUE，名字=Text）
	 * @return返回一个装有ID以及名字的JSONARRAY
	 */
	public JSONArray getAllLabelValueAndText();
	
	/**
	 * 向数据库中的题目标签中添加新的题目
	 * @param subID题目ID
	 * @param lid一个装有题目标签ID的数组
	 * @return
	 */
	public boolean insertNewSSLabel(int subID,int[] lid);
	
	/**
	 * 通过题目的ID删除数据库中Sub_S_Label表的数据
	 * @param subID题目ID
	 * @return 删除成功返回true
	 */
	public boolean deleteFromSubSSLabelBySubID(int subID);
}
