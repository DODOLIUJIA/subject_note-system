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
		//jo.put("rows", sldao.getSubLabels((page-1)*pageSize, pageSize));
		return jo;
	}
	
	
	
}
