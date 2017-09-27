package com.test;

import java.util.ArrayList;

import com.zr.dao.impl.SubLabelDaoImpl;
import com.zr.model.SubjectLabel;

import net.sf.json.JSONArray;

public class jsonTest {
	public static void main(String[] args) {
		SubLabelDaoImpl sldi = new SubLabelDaoImpl();
		
		ArrayList<SubjectLabel> slist = (ArrayList<SubjectLabel>) sldi.getsub_label();
		ArrayList<String> names = new ArrayList<String>();
		
		for (SubjectLabel sl : slist) {
			names.add(sl.getS_lname());
		}
		
		JSONArray ja = JSONArray.fromObject(names);
		System.out.println(ja.toString());
		
	}
}
