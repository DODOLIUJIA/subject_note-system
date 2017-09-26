package com.zr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zr.dao.SubDao;
import com.zr.dao.UserSubDao;
import com.zr.dao.impl.SubDaoImpl;
import com.zr.dao.impl.UserSubDaoImpl;
import com.zr.model.Subject;
import com.zr.service.SubService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubServiceImpl implements SubService {

	SubDao sdao = new SubDaoImpl();
	UserSubDao usd = new UserSubDaoImpl();

	@Override
	public boolean insertNewSub(String subSummary, String subText, int subType, String subAnswer, int subTime) {
		return sdao.insertNewSubject(subSummary, subText, subType, subAnswer, subTime);
	}

	@Override
	public JSONObject getSubjectBySid(int sid) {
		Subject s = sdao.getSubjectBySId(sid);
		JSONObject json = new JSONObject();
		if (s != null) {
			json.put("subid", s.getSubId());
			json.put("subsummary", s.getSubSummary());
			json.put("subtext", s.getSubText());
			json.put("subtype", s.getSubType());
			json.put("subaccuracy", s.getSubAccuracy());
			json.put("subanswer", s.getSubAnswer());
			json.put("subtime", s.getSubTime());
			return json;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		SubServiceImpl s = new SubServiceImpl();
		System.out.println(s.getSubjectBySid(1));
	}

	@Override
	public boolean updateSubject(int sid, String subSummary, String subText, int subType, String subAnswer,
			int subTime) {
		return sdao.updateSubject(sid, subSummary, subText, subType, subAnswer, subTime);
	}

	/**
	 * 通过页面传来的信息选择题目
	 * 
	 * @param sublabel
	 *            题目标签
	 * @param subCrateTime
	 *            出题时间
	 * @param STcheck
	 *            题目标签是否被点击
	 * @param SCTcheck
	 *            出题时间选项是否被点击
	 * @return
	 */
	@Override
	public List<Subject> selectSubsByMsg(String sublabel, int subCrateTime) {
		List<Subject> subs = new ArrayList<Subject>();
		if ("".equals(sublabel) && subCrateTime != 0) {
			subs = usd.selectSubsBySubTime(subCrateTime);
		} else if (!("".equals(sublabel)) && subCrateTime == 0) {
			subs = usd.selectSubsBySubType(sublabel);
		} else {
			subs = usd.selectSubsBySubTypeAndSubTime(sublabel, subCrateTime);
		}
		return subs;
	}

	/**
	 * 得到所有的题目标签
	 * 
	 * @return
	 */
	public List<Subject> selectSubLabel() {
		List<Subject> labels = new ArrayList<Subject>();
		labels = usd.selectSubLabel();
		return labels;
	}

	/**
	 * 得到所有题目的不同的出题时间
	 * 
	 * @return
	 */
	public List<Subject> selectSubTime() {
		List<Subject> times = new ArrayList<Subject>();
		times = usd.selectSubTime();
		return times;
	}

	@Override
	public Subject getSubjectBySubid(int sid) {
		return sdao.getSubjectBySId(sid);
	}
}
