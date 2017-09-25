package com.zr.service.impl;

import java.util.List;

import com.zr.dao.SubDao;
import com.zr.dao.impl.SubDaoImpl;
import com.zr.service.SubService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubServiceImpl implements SubService {
	SubDao subDao = new SubDaoImpl();
	@Override
	public JSONArray getAllYears() {
		JSONArray ja = new JSONArray();
		ja = subDao.getAllYears();
		return ja;
	}
	@Override
	public JSONArray getAllSubType() {
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
	public String changeSubtypeIntToString(int subType) {
		String subtypeS = "";
		if(subType == 1) {
			subtypeS = "简答题";
		}else if(subType == 2) {
			subtypeS = "填空题";
		}else if(subType == 3) {
			subtypeS = "单选题";
		}else if(subType == 4){
			subtypeS = "多选题";
		}else {
			subtypeS = "所有";
		}
		return subtypeS;
	}
	
	@Override
	public int changeSubtypeStringToInt(String subType) {
		int subtypeI = 0;
		if(subType.equals("简答题")) {
			subtypeI = 1;
		}else if(subType.equals("填空题")) {
			subtypeI = 2;
		}else if(subType.equals("单选题")) {
			subtypeI = 3;
		}else if(subType.equals("多选题")){
			subtypeI = 4;
		}
		return subtypeI;
	}
	
	@Override
	public JSONArray getAllSubLabel() {
		JSONArray ja = new JSONArray();
		ja = subDao.getAllSubLabel();
		return ja;
	}
	
	@Override
	public JSONObject getSubsByPageAndPagesize(int page, int pageSize) {
		JSONObject  jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubs((page-1)*pageSize, pageSize));
		return jo;

	}
	@Override
	public JSONObject getSubsByPageAndPagesizeBySubLabel(int page, int pageSize, String subLabel) {
		JSONObject  jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySublabel((page-1)*pageSize, pageSize, subLabel));
		return jo;

	}
	@Override
	public JSONObject getSubsByPageAndPagesizeBySubType(int page, int pageSize, String subType) {
		JSONObject  jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubType((page-1)*pageSize, pageSize, subTypeI));
		return jo;
	}
	@Override
	public JSONObject getSubsByPageAndPagesizeBySubYear(int page, int pageSize, int yearInt) {
		JSONObject  jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYear((page-1)*pageSize, pageSize, yearInt));
		return jo;
	}
	@Override
	public JSONObject getSubsByPageAndPagesizeBySubTypeAndSubLabel(int page, int pageSize, String subType, String subLabel) {
		JSONObject  jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubTypeAndSubLabel((page-1)*pageSize, pageSize, subTypeI, subLabel));
		return jo;
	}
	@Override
	public JSONObject getSubsByPageAndPagesizeByYearAndSubLabel(int page, int pageSize, int yearInt, String subLabel) {
		JSONObject  jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYearAndSubLabel((page-1)*pageSize, pageSize, yearInt, subLabel));
		return jo;
	}
	@Override
	public JSONObject getSubsByPageAndPagesizeByYearAndSubType(int page, int pageSize, int yearInt, String subType) {
		JSONObject  jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYearAndSubType((page-1)*pageSize, pageSize, yearInt, subTypeI));
		return jo;
	}
	@Override
	public JSONObject getSubsByPageAndPagesizeByYearAndSubTypeAndSubLabel(int page, int pageSize, int yearInt, String subType, String subLabel) {
		JSONObject  jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYearAndSubTypeAndSubLabel((page-1)*pageSize, pageSize, yearInt, subTypeI, subLabel));
		return jo;
	}
	@Override
	public void deleteSubBySubId(int subId) {
		
		subDao.deleteSubBySubId(subId);
	}
	
	
	
}
