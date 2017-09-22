package com.zr.service.impl;

import java.util.List;
import com.zr.model.Sub;
import com.zr.service.SubService;

import com.zr.dao.SubDao;
import com.zr.dao.impl.SubDaoImpl;
import com.zr.model.Subject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubServiceImpl implements SubService{
	SubDao subDao = new SubDaoImpl();
  
	@Override
	public List<Sub> selectSubsByMsg(String subType, int subCrateTime, String STcheck, String SCTcheck) {
		// TODO Auto-generated method stub
		return null;
	}
  
	@Override
	public JSONObject getSubsByPageAndPagesize(int page, int pageSize) {
		JSONObject  jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubs((page-1)*pageSize, pageSize));
		return jo;

	}
  
	@Override
	public JSONArray getAllYears() {
		JSONArray ja = new JSONArray();
		ja = subDao.getAllYears();
		return ja;
	}
  
	@Override
	public JSONArray getAllSubtype() {
		JSONArray ja = new JSONArray();
		List<Integer> subtypes = subDao.getAllSubtype();
		for (int i = 0; i < subtypes.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("id", i+1);
			jo.put("text", this.changeSubtypeIntToString(subtypes.get(i).intValue()));
			ja.add(jo);
		}
		return ja;
	}
  
	@Override
	public String changeSubtypeIntToString(int subtype) {
		String subtypeS = "";
		if(subtype == 1) {
			subtypeS = "简答题";
		}else if(subtype == 2) {
			subtypeS = "填空题";
		}else if(subtype == 3) {
			subtypeS = "单选题";
		}else if(subtype == 4){
			subtypeS = "多选题";
		}
		return subtypeS;
  }
	
	@Override
	public boolean insertNewSub(String subSummary, String subText, int subType, String subAnswer, int subTime) {
		return sdao.insertNewSubject(subSummary, subText, subType, subAnswer, subTime);
	}

	@Override
	public JSONObject getSubjectBySid(int sid) {
		Subject s=sdao.getSubjectBySId(sid);
		JSONObject json=new JSONObject();
		if(s!=null){
			json.put("subid", s.getSubId());
			json.put("subsummary", s.getSubSummary());
			json.put("subtext", s.getSubText());
			json.put("subtype", s.getSubType());
			json.put("subaccuracy", s.getSubAccuracy());
			json.put("subanswer", s.getSubAnswer());
			json.put("subtime", s.getSubTime());
			return json;
		}else{
			return null;
		}
	}
	
	public static void main(String[] args) {
		SubServiceImpl s=new SubServiceImpl();
		System.out.println(s.getSubjectBySid(1));
	}
}
