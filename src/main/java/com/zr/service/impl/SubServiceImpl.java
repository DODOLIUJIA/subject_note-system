package com.zr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zr.dao.UserSubDao;
import com.zr.dao.impl.UserSubDaoImpl;
import com.zr.model.Subject;
import com.zr.model.SubjectLabel;
import com.zr.service.SubService;
/**
 * 
 * @author 吴尚鑫
 *
 */
public class SubServiceImpl implements SubService{
    UserSubDao usd = new UserSubDaoImpl();
    
    /**
	 * 通过页面传来的信息选择题目
	 * @param sublabel 题目标签	
	 * @param subCrateTime 出题时间
	 * @param STcheck 题目标签是否被点击	
	 * @param SCTcheck 出题时间选项是否被点击
	 * @return
	 */
	@Override
	public List<Subject> selectSubsByMsg(String sublabel, int subCrateTime, int loadtimms) {
		List<Subject> subs = new ArrayList<Subject>();
		if("".equals(sublabel)&&subCrateTime != 0){
			subs = usd.selectSubsBySubTime(subCrateTime, loadtimms);
		}else if(!("".equals(sublabel))&&subCrateTime == 0){
			subs = usd.selectSubsBySubType(sublabel, loadtimms);
		}else{
			subs = usd.selectSubsBySubTypeAndSubTime(sublabel, subCrateTime, loadtimms);
		}
		return subs;
	}

	/**
	 * 得到所有的题目标签
	 * @return
	 */
	public List<SubjectLabel> selectSubLabel(){
		List<SubjectLabel> labels = new ArrayList<SubjectLabel>();
		labels = usd.selectSubLabel();
		return labels;
	}
	/**
	 * 得到所有题目的不同的出题时间
	 * @return
	 */
	public List<Subject> selectSubTime(){
		List<Subject> times = new ArrayList<Subject>();
		times = usd.selectSubTime();
		return times;
	}
}
