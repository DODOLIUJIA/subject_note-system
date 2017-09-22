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

	
	/**
	 * 插入一道新的题目
	 * @param subSummary题目描述
	 * @param subText题目内容
	 * @param subType题目类型
	 * @param subAnswer题目答案
	 * @param subTime题目创建年份
	 * @return
	 */
	public boolean insertNewSubject(String subSummary,String subText,int subType,String subAnswer,int subTime);

	/**
	 * 通过题目id来获得一道题目的完整信息
	 * @param sid题目id
	 * @return 返回查找到的题目对象，否则返回空
	 */
	public Subject getSubjectBySId(int sid);
	
}
