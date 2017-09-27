package com.zr.service.impl;

import com.zr.dao.SubLabelDao;
import com.zr.dao.impl.SubLabelDaoImpl;
import com.zr.service.SubLabelService;

import net.sf.json.JSONObject;

public class SubLabelServiceImpl implements SubLabelService{
	SubLabelDao sldao = new SubLabelDaoImpl();
	@Override
	public JSONObject getSubLabelsByPageAndPagesize(int page, int pageSize) {
		JSONObject  jo = new JSONObject();
		jo.put("total", sldao.getSubLabelsCount());
		jo.put("rows", sldao.getSubLabels((page-1)*pageSize, pageSize));
		return jo;
	}
	@Override
	public JSONObject insertNewLabAndeExist(String newLabName) {
		JSONObject jo = new JSONObject();
		jo = sldao.insertNewLabAndeExist(newLabName);
		return jo;
	}
	@Override
	public JSONObject deleteSubLabelByS_lid(int s_lid) {
		JSONObject jo = new JSONObject();
		jo = sldao.deleteSubLabelByS_lid(s_lid);
		return jo;
	}
	@Override
	public JSONObject updataS_LabAndeExist(String updataS_LabName, int s_lid) {
		JSONObject jo = new JSONObject();
		jo = sldao.updataS_LabAndeExist( updataS_LabName, s_lid);
		return jo;
	}
}
