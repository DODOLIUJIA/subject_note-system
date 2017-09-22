package com.zr.service;

import java.util.List;

import com.zr.model.Sub;

/**
 * 
 * @author 吴尚鑫
 *
 */
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
}
