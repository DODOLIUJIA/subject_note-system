package com.zr.dao;

import java.util.List;

import com.zr.model.SubjectLabel;


public interface SubLabelDao {


	/**
	 * 获取题目标签的数量
	 * @return
	 */
	public int getSubLabelsCount();
	
	/**
	 * 获取所有的标签
	 * @return
	 */
	public List<SubjectLabel> getsub_label();

}
