package com.zr.service;

import java.util.List;

import com.zr.model.Subject;

/**
 * 
 * @author 吴尚鑫
 *
 */
public interface SubService {
	/**
	 * 通过页面传来的信息选择题目
	 * @param sublabel 题目标签	
	 * @param subCrateTime 出题时间
	 * @param STcheck 题目标签被点击次数	
	 * @param SCTcheck 出题时间选项被点击次数
	 * @return
	 */
public List<Subject> selectSubsByMsg(String sublabel,int subCrateTime,int STcheck,int SCTcheck);
}
