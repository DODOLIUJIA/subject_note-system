package com.zr.service;

import java.util.List;

import com.zr.model.Subject;
import com.zr.model.SubjectLabel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface SubService {

	/**
	 * 通过页面传来的信息选择题目
	 * @param sublabel 题目标签	
	 * @param subCrateTime 出题时间
	 * @param loadtimms 下拉刷新次数
	 * @return
	 */
	public List<Subject> selectSubsByMsg(String sublabel, int subCrateTime,int loadtimms);


	/**
	 * 获取所有年份
	 * 
	 * @return
	 */
	public JSONArray getAllYears();

	/**
	 * 获得所有题型
	 * 
	 * @return
	 */
	public JSONArray getAllSubType();
	
	/**
	 * 获取所有题目标签
	 * @return
	 */
	public JSONArray getAllSubLabel();

	/**
	 * 把题目类型数字变成翻译
	 * 
	 * @param subtype
	 * @return 题型
	 */
	public String changeSubtypeIntToString(int subType);
	
	/**
	 * 把题目类型汉字变成数字
	 * @param subtype
	 * @return 题型
	 */
	public int changeSubtypeStringToInt(String subType);

	/**
	 * 通过页码和显示数量获得题目
	 * @param pageSize 显示数量
	 * @param page 页码
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesize(int page, int pageSize);
	
	/**
	 * 通过页码和显示数量和题目标签
	 * @param page 页码
	 * @param pageSize 数量
	 * @param subLabel 题目标签
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesizeBySubLabel(int page, int pageSize, String subLabel);
	
	/**
	 * 通过页码和显示数量和题目类型
	 * @param page 页码
	 * @param pageSize 数量
	 * @param subType 题目标签
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesizeBySubType(int page, int pageSize, String subType);
	
	/**
	 * 通过页码和显示数量和年份
	 * @param page 页码
	 * @param pageSize 数量
	 * @param yearInt 年份
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesizeBySubYear(int page, int pageSize, int yearInt);
	
	/**
	 * 通过页码和显示数量、题型、题标签
	 * @param page
	 * @param pageSize
	 * @param subType 题型
	 * @param subLabel 题标签
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesizeBySubTypeAndSubLabel(int page, int pageSize, String subType, String subLabel);
	
	/**
	 * 通过页码和显示数量、年份、题标签
	 * @param page
	 * @param pageSize
	 * @param yearInt
	 * @param subLabel
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesizeByYearAndSubLabel(int page, int pageSize, int yearInt, String subLabel);

	/**
	 * 通过页码和显示数量、年份、题型
	 * @param page
	 * @param pageSize
	 * @param yearInt 年份
	 * @param subType 题型
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesizeByYearAndSubType(int page, int pageSize, int yearInt, String subType);

	/**
	 * 通过页码和显示数量、年份、题型、题标签
	 * @param page 
	 * @param pageSize
	 * @param yearInt 年份
	 * @param subType 题型
	 * @param subLabel 题标签
	 * @return 题目分页json对象
	 */
	public JSONObject getSubsByPageAndPagesizeByYearAndSubTypeAndSubLabel(int page, int pageSize, int yearInt, String subType, String subLabel);

	/**
	 * 通过题目id删除题目
	 * @param subId
	 */
	public void deleteSubBySubId(int subId);

	/*
	 * 插入一道新的题目
	 * 
	 * @param subSummary题目描述
	 * 
	 * @param subText题目内容
	 * 
	 * @param subType题目类型
	 * 
	 * @param subAnswer题目答案
	 * 
	 * @param subTime题目创建年份
	 * 
	 * @return
	 */
	public boolean insertNewSub(String subSummary, String subText, int subType, String subAnswer, int subTime);

	/**
	 * 通过题目id得到一个题目
	 * 
	 * @param sid题目id
	 * @return返回一个存有题目信息的JSONObject对象
	 */
	public JSONObject getSubjectBySid(int sid);

	/**
	 * 得到所有的题目标签
	 * @return
	 */
	public List<SubjectLabel> selectSubLabel();
  
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
	public List<SubjectLabel> selectSubLabel();


	/**
	 * 得到所有题目的不同的出题时间
	 * 
	 * @return
	 */
	public List<Subject> selectSubTime();
}
