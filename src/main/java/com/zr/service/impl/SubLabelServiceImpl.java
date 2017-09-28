package com.zr.service.impl;

import java.util.ArrayList;

import com.zr.dao.SubLabelDao;
import com.zr.dao.impl.SubLabelDaoImpl;
import com.zr.model.SubjectLabel;
import com.zr.service.SubLabelService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubLabelServiceImpl implements SubLabelService {
	SubLabelDao sldao = new SubLabelDaoImpl();

	@Override
	public JSONObject getSubLabelsByPageAndPagesize(int page, int pageSize) {
		JSONObject jo = new JSONObject();
		jo.put("total", sldao.getSubLabelsCount());

		jo.put("rows", sldao.getSubLabels((page-1)*pageSize, pageSize));

		return jo;
	}

	@Override
	public JSONArray getSubLabelsCount() {
		ArrayList<SubjectLabel> sub_labelList = (ArrayList<SubjectLabel>) sldao.getsub_label();

		ArrayList<String> lNames = new ArrayList<String>();
		ArrayList<Integer> lCounts = new ArrayList<Integer>();

		for (SubjectLabel sl : sub_labelList) {
			lNames.add(sl.getS_lname());
			lCounts.add(sl.getSubcount());
		}

		JSONArray labelNamesJsonArr = JSONArray.fromObject(lNames);
		JSONArray labelCountsJsonArr = JSONArray.fromObject(lCounts);
		
		ArrayList<JSONArray> jas = new ArrayList<JSONArray>();
		jas.add(labelCountsJsonArr);
		jas.add(labelNamesJsonArr);
		
		JSONArray result = JSONArray.fromObject(jas);

		return result;
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

	@Override
	public int[] getAllSubjectLabelID() {
		return sldao.getAllSubjectLabelID();
	}

	@Override
	public boolean updateAllSubjectLabelCount() {
		return sldao.updateAllSubjectLabelCount();
	}

	@Override
	public JSONArray getAllLabelValueAndText() {
		return sldao.getAllLabelValueAndText();
	}

	@Override
	public boolean insertNewSSLabel(int subID, int[] lid) {
		return sldao.insertNewSSLabel(subID, lid);
	}

	@Override
	public boolean deleteFromSubSSLabelBySubID(int subID) {
		return sldao.deleteFromSubSSLabelBySubID(subID);
	}
}
