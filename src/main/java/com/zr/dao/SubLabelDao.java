package com.zr.dao;

import java.util.List;

import com.zr.model.SubjectLabel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SubLabelDao {

	/**
	 * 获取题目标签的数量
	 * 
	 * @return
	 */
	public int getSubLabelsCount();

	/**
	 * 获取所有的标签
	 * 
	 * @return
	 */
	public List<SubjectLabel> getsub_label();

	/**
	 * 获取题标签
	 * 
	 * @param start
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return 题标签json数组
	 */
	public JSONArray getSubLabels(int start, int pageSize);

	/**
	 * 通过提标签id查询每个标签下题有多少个
	 * 
	 * @param s_lid
	 *            提标签id
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
	 * @param newLabName新的标签名
	 * @return true 已存在，false 不存在。
	 */
	public boolean subLabelExist(String newLabName);

	/**
	 * 通过s_lid删除标签
	 * @param s_lid题标签id
	 * @return
	 */
	public JSONObject deleteSubLabelByS_lid(int s_lid);

	/**
	 * 通过s_lid更新提标签
	 * @param updataS_LabName新名字
	 * @param s_lid题id
	 * @return
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
