package com.zr.service;

import java.util.List;

import com.zr.model.Subject;
import net.sf.json.JSONObject;

public interface SubService {

	/**
	 * 插入一道新的题目
	 * @param subSummary题目描述
	 * @param subText题目内容
	 * @param subType题目类型
	 * @param subAnswer题目答案
	 * @param subTime题目创建年份
	 * @return
	 */
	public boolean insertNewSub(String subSummary,String subText,int subType,String subAnswer,int subTime);

	/**
	 * 通过题目id得到一个题目
	 * @param sid题目id
	 * @return返回一个存有题目信息的JSONObject对象
	 */
	public JSONObject getSubjectBySid(int sid);
	
	/**
	 * 通过题目id得到一个题目
	 * @param sid题目的id
	 * @return返回一个存有Subject对象
	 */
	public Subject getSubjectBySubid(int sid);
	
	/**
	 * 通过sid来更新题目
	 * @param sid题目ID
	 * @param subSummary题目描述
	 * @param subText题目内容
	 * @param subType题目类型
	 * @param subAnswer题目答案
	 * @param subTime题目创建年份
	 * @return 更新成功返回true，更新失败返回false
	 */
	public boolean updateSubject(int sid, String subSummary, String subText, int subType, String subAnswer,
			int subTime);
	
	/**
	 * 通过页面传来的信息选择题目
	 * 
	 * @param sublabel
	 *            题目标签
	 * @param subCrateTime
	 *            出题时间
	 * @param STcheck
	 *            题目标签被点击次数
	 * @param SCTcheck
	 *            出题时间选项被点击次数
	 * @return
	 */
	public List<Subject> selectSubsByMsg(String sublabel, int subCrateTime);

	/**
	 * 得到所有的题目标签
	 * 
	 * @return
	 */
	public List<Subject> selectSubLabel();

	/**
	 * 得到所有题目的不同的出题时间
	 * 
	 * @return
	 */
	public List<Subject> selectSubTime();
}
