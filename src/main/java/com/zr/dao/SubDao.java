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
	 * 获取所有年份
	 * @return
	 */
	public JSONArray getAllYears();

	/**
	 * 获取所有题型
	 * @return
	 */
	public List<Integer> getAllSubtype();

}
