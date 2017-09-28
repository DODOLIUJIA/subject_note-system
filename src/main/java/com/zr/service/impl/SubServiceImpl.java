package com.zr.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.zr.dao.UserSubDao;
import com.zr.dao.impl.UserSubDaoImpl;
import com.zr.model.Subject;
import com.zr.model.SubjectLabel;
import com.zr.service.SubService;
import com.zr.dao.SubDao;
import com.zr.dao.impl.SubDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubServiceImpl implements SubService {
	SubDao sdao = new SubDaoImpl();

	SubDao subDao = new SubDaoImpl();

	UserSubDao usd = new UserSubDaoImpl();

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
			jo.put("id", i + 1);
			jo.put("text", this.changeSubtypeIntToString(subtypes.get(i).intValue()));
			ja.add(jo);
		}
		return ja;
	}

	@Override
	public String changeSubtypeIntToString(int subType) {
		String subtypeS = "";
		/*if (subType == 1) {
			subtypeS = "绠�瓟棰�;
		} else if (subType == 2) {
			subtypeS = "濉┖棰�;
		} else if (subType == 3) {
			subtypeS = "鍗曢�棰�;
		} else if (subType == 4) {
			subtypeS = "澶氶�棰�;
		} else {
			subtypeS = "鎵�湁";
		}*/
		return subtypeS;
	}

	@Override
	public boolean insertNewSub(String subSummary, String subText, int subType, String subAnswer, int subTime) {
		return subDao.insertNewSubject(subSummary, subText, subType, subAnswer, subTime);
	}

	@Override
	public int changeSubtypeStringToInt(String subType) {
		int subtypeI = 0;
		/*if (subType.equals("绠�瓟棰�)) {
			subtypeI = 1;
		} else if (subType.equals("濉┖棰�)) {
			subtypeI = 2;
		} else if (subType.equals("鍗曢�棰�)) {
			subtypeI = 3;
		} else if (subType.equals("澶氶�棰�)) {
			subtypeI = 4;
		}*/
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
		JSONObject jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubs((page - 1) * pageSize, pageSize));
		return jo;

	}

	@Override
	public JSONObject getSubsByPageAndPagesizeBySubLabel(int page, int pageSize, String subLabel) {
		JSONObject jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySublabel((page - 1) * pageSize, pageSize, subLabel));
		return jo;
	}

	@Override
	public JSONObject getSubsByPageAndPagesizeBySubType(int page, int pageSize, String subType) {
		JSONObject jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubType((page - 1) * pageSize, pageSize, subTypeI));
		return jo;
	}

	@Override
	public JSONObject getSubsByPageAndPagesizeBySubYear(int page, int pageSize, int yearInt) {
		JSONObject jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYear((page - 1) * pageSize, pageSize, yearInt));
		return jo;
	}

	@Override
	public JSONObject getSubsByPageAndPagesizeBySubTypeAndSubLabel(int page, int pageSize, String subType,
			String subLabel) {
		JSONObject jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubTypeAndSubLabel((page - 1) * pageSize, pageSize, subTypeI, subLabel));
		return jo;
	}

	@Override
	public JSONObject getSubsByPageAndPagesizeByYearAndSubLabel(int page, int pageSize, int yearInt, String subLabel) {
		JSONObject jo = new JSONObject();
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYearAndSubLabel((page - 1) * pageSize, pageSize, yearInt, subLabel));
		return jo;
	}

	@Override
	public JSONObject getSubsByPageAndPagesizeByYearAndSubType(int page, int pageSize, int yearInt, String subType) {
		JSONObject jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYearAndSubType((page - 1) * pageSize, pageSize, yearInt, subTypeI));
		return jo;
	}

	@Override
	public JSONObject getSubsByPageAndPagesizeByYearAndSubTypeAndSubLabel(int page, int pageSize, int yearInt,
			String subType, String subLabel) {
		JSONObject jo = new JSONObject();
		int subTypeI = this.changeSubtypeStringToInt(subType);
		jo.put("total", subDao.getSubsCount());
		jo.put("rows", subDao.getSubsBySubYearAndSubTypeAndSubLabel((page - 1) * pageSize, pageSize, yearInt, subTypeI,
				subLabel));
		return jo;
	}

	@Override
	public void deleteSubBySubId(int subId) {
		subDao.deleteSubBySubId(subId);
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

	/**
	 * 閫氳繃椤甸潰浼犳潵鐨勪俊鎭�鎷╅鐩�
	 * 
	 * @param sublabel
	 *            棰樼洰鏍囩
	 * @param subCrateTime
	 *            鍑洪鏃堕棿
	 * @param loadtimms
	 *            涓嬫媺鍒锋柊娆℃暟
	 * @return
	 */
	@Override
	public List<Subject> selectSubsByMsg(String sublabel, int subCrateTime, int loadtimms) {
		List<Subject> subs = new ArrayList<Subject>();
		if ("".equals(sublabel) && subCrateTime != 0) {
			subs = usd.selectSubsBySubTime(subCrateTime, loadtimms);
		} else if (!("".equals(sublabel)) && subCrateTime == 0) {
			subs = usd.selectSubsBySubType(sublabel, loadtimms);
		} else {
			subs = usd.selectSubsBySubTypeAndSubTime(sublabel, subCrateTime, loadtimms);
		}
		return subs;
	}

	/**
	 * 寰楀埌鎵�湁鐨勯鐩爣绛�
	 * 
	 * @return
	 */
	@Override
	public List<SubjectLabel> selectSubLabel() {
		List<SubjectLabel> labels = new ArrayList<SubjectLabel>();
		labels = usd.selectSubLabel();
		return labels;
	}

	/**
	 * 寰楀埌鎵�湁棰樼洰鐨勪笉鍚岀殑鍑洪鏃堕棿
	 * 
	 * @return
	 */
	public List<Subject> selectSubTime() {
		List<Subject> times = new ArrayList<Subject>();
		times = usd.selectSubTime();
		return times;
	}

	/**
	 * 寰楀埌鎵�湁棰樼洰
	 * @param loadtimms 涓嬫媺娆℃暟
	 * @return
	 */
	@Override
	public List<Subject> selectAllSub(int loadtimms) {
		List<Subject> Subs = subDao.getSubs(loadtimms*4, 4);
		return Subs;
	}

	public boolean updateSubject(int sid, String subSummary, String subText, int subType, String subAnswer,
			int subTime) {
		return sdao.updateSubject(sid, subSummary, subText, subType, subAnswer, subTime);

	}

	@Override
	public Subject getSubjectBySubid(int sid) {
		return sdao.getSubjectBySId(sid);

	}

	@Override
	public int getSubIDBySummaryAndTextAndType(String summary, String text, int type) {
		return sdao.getSubIDBySummaryAndTextAndType(summary, text, type);
	}
}
