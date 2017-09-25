package com.zr.service.impl;

import com.zr.dao.SubDao;
import com.zr.dao.impl.SubDaoImpl;
import com.zr.model.Subject;
import com.zr.service.SubService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubServiceImpl implements SubService {

	SubDao sdao = new SubDaoImpl();

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
}
