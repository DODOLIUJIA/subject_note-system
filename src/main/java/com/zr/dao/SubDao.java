package com.zr.dao;

import java.util.List;

import com.zr.model.Subject;

import net.sf.json.JSONArray;

public interface SubDao {
	
	/**
	 * 获得题目数量
	 * @return
	 */
	public int getSubsCount();

	/**
	 * 分页所需参数
	 * @param start 起始位置
	 * @param pageSize 显示数量
	 * @return 题目集合
	 */
	public List<Subject> getSubs(int start, int pageSize);
	/**
	 * 通过题目id查找标签，如有多个进行拼接
	 * @param subid
	 * @return
	 */
	public String getSublabelBySubid(int subid);
	
	/**
	 * 获取所有年份
	 * @return
	 */
	public JSONArray getAllYears();

	/**
	 * 获取所有题型
	 * @return
	 */
	public List<Integer> getAllSubtype();
	
	/**
	 * 获取所有题目标签
	 * @return
	 */
	public JSONArray getAllSubLabel();
	
	/**
	 * 分页所需参数
	 * @param start
	 * @param pageSize
	 * @param subLabel 题目标签
	 * @return 题目集合
	 */
	public Object getSubsBySublabel(int start, int pageSize, String subLabel);

}
