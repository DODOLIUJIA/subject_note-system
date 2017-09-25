package com.zr.dao;

import com.zr.model.Subject;

import net.sf.json.JSONArray;

/**
 * 操作课程的接口
 * @author JACK
 *
 */
public interface SubDao {

	/**
	 * 插入一道新的题目
	 * @param subSummary题目描述
	 * @param subText题目内容
	 * @param subType题目类型
	 * @param subAnswer题目答案
	 * @param subTime题目创建年份
	 * @return
	 */
	public boolean insertNewSubject(String subSummary, String subText, int subType, String subAnswer, int subTime);

	/**
	 * 通过题目id来获得一道题目的完整信息
	 * @param sid题目id
	 * @return 返回查找到的题目对象，否则返回空
	 */
	public Subject getSubjectBySId(int sid);

	/**
	 * 通过sid来更新题目
	 * @param sid题目ID
	 * @param subSummary题目描述
	 * @param subText题目内容
	 * @param subType题目类型
	 * @param subAnswer题目答案
	 * @param subTime题目创建年份
	 * @return
	 */
	public boolean updateSubject(int sid, String subSummary, String subText, int subType, String subAnswer,
			int subTime);

}
