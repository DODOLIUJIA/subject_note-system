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
	 * 通过页码和显示数量和题目标签
	 * @param start
	 * @param pageSize
	 * @param subLabel 题目标签
	 * @return 题目集合
	 */
	public List<Subject> getSubsBySublabel(int start, int pageSize, String subLabel);

	/** 
	 * 通过页码和显示数量和题目类型
	 * @param start
	 * @param pageSize
	 * @param subType 题目标签
	 * @return 题目数组
	 */	
	public List<Subject> getSubsBySubType(int start, int pageSize, int subTypeI);
	
	/**
	 * 通过页码和显示数量和年份
	 * @param start 
	 * @param pageSize
	 * @param yearInt 年份
	 * @return 题目数组
	 */
	public List<Subject> getSubsBySubYear(int start, int pageSize, int yearInt);

	/**
	 * 通过页码和显示数量和题型、题标签
	 * @param start
	 * @param pageSize
	 * @param subTypeI 题型
	 * @param subLabel 题标签
	 * @return 题目数组
	 */
	public List<Subject> getSubsBySubTypeAndSubLabel(int start, int pageSize, int subTypeI, String subLabel);
	
	/**
	 * 通过页码和显示数量和年份、题标签
	 * @param start
	 * @param pageSize
	 * @param subTypeI 年份
	 * @param subLabel 题标签
	 * @return 题目数组
	 */
	public List<Subject> getSubsBySubYearAndSubLabel(int start, int pageSize, int yearInt, String subLabel);

	/**
	 * 过页码和显示数量和年份、题型
	 * @param start
	 * @param pageSize
	 * @param yearInt 年份
	 * @param subTypeI 题型
	 * @return 题目数组
	 */
	public List<Subject> getSubsBySubYearAndSubType(int start, int pageSize, int yearInt, int subTypeI);

	/**
	 * 过页码和显示数量和年份、题型
	 * @param start
	 * @param pageSize
	 * @param yearInt 年份
	 * @param subTypeI 题型
	 * @param subLabel 提标签
	 * @return
	 */
	public List<Subject> getSubsBySubYearAndSubTypeAndSubLabel(int start, int pageSize, int yearInt, int subTypeI, String subLabel);

	
	/**
	 * 通过题目id删除题目
	 * @param subId
	 */
	public void deleteSubBySubId(int subId);

	
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
