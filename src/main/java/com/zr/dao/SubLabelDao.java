package com.zr.dao;

import java.util.List;

import com.zr.model.SubLabel;

public interface SubLabelDao {


	/**
	 * 获取题目标签的数量
	 * @return
	 */
	public int getSubLabelsCount();

	/**
	 * 过起始位置和显示数量 分页参数
	 * @param start 起始位置
	 * @param pageSize 显示数量
	 * @return 题目标签
	 */
	public List<SubLabel> getSubLabels(int start, int pageSize);
}
