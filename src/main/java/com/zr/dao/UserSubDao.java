package com.zr.dao;

import java.util.List;

import com.zr.model.Subject;
import com.zr.model.SubjectLabel;

/**
 * 
 * @author 吴尚鑫
 *
 */
public interface UserSubDao {
	/**
	 * 通过题目标签筛选题目
	 * 
	 * @param sublabel
	 *            题目标签
	 * @return
	 */
	public List<Subject> selectSubsBySubType(String sublabel, int loadtimms);
	
	/**
	 * 通过出题时间筛选题目
	 * 
	 * @param subtime
	 *            题目标签
	 * @return
	 */
	public List<Subject> selectSubsBySubTime(int subtime, int loadtimms);
	
	/**
	 * 通过题目标签和出题时间筛选题目
	 * 
	 * @param sublabel
	 *            题目标签
	 * @param subtime
	 *            出题时间
	 * @return
	 */
	public List<Subject> selectSubsBySubTypeAndSubTime(String sublabel,int subtime, int loadtimms);
	
	/**
	 * 得到所有的题目标签
	 * @return
	 */
	public List<SubjectLabel> selectSubLabel();
	/**
	 * 得到所有题目的不同的出题时间
	 * @return
	 */
	public List<Subject> selectSubTime();
}
