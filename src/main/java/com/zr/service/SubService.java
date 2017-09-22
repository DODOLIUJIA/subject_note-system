package com.zr.service;

import java.util.List;

import com.zr.model.Sub;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SubService {
	/**
	 * 通过页面传来的信息选择题目
	 * @param subType 
	 * @param subCrateTime
	 * @param STcheck
	 * @param SCTcheck
	 * @return
	 */
public List<Sub> selectSubsByMsg(String subType,int subCrateTime,String STcheck,String SCTcheck);

	/**
	 * 通过页码和显示数量获得题目
	 * @param pageSize 显示数量
	 * @param page 页码
	 * @return 题目数组
	 */
	public JSONObject getSubsByPageAndPagesize(int page, int pageSize);
	
	/**
	 * 获取所有年份
	 * @return
	 */
	public JSONArray getAllYears();
	
	/**
	 * 获得所有题型
	 * @return
	 */
	public JSONArray getAllSubtype();
	
	/**
	 * 把题目类型数字变成翻译
	 * @param subtype
	 * @return 题型
	 */
	public String changeSubtypeIntToString(int subtype);

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
}