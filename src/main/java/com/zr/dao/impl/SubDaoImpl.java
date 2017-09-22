package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.SubDao;
import com.zr.model.Subject;
import com.zr.util.JDBCUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubDaoImpl implements SubDao {

	@Override
	public int getSubsCount() {
		int subCount = 0;
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(subject.subid) subcount FROM subject ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				subCount=rs.getInt("subcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}

		return subCount;
	}

	@Override
	public List<Subject> getSubs(int start, int pageSize) {
		List<Subject> subjects = new ArrayList<Subject>();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT `subject`.subid, `subject`.subsummary, `subject`.subtext, `subject`.subtype, ");
		sql.append("`subject`.subaccuracy, `subject`.subanswer, `subject`.subtime ");
		sql.append("FROM `subject` LEFT JOIN `subject_s_label` ");
		sql.append("ON `subject`.subid = `subject_s_label`.subid ");
		sql.append("LEFT JOIN `s_label` ");
		sql.append("ON `subject_s_label`.s_lid = `s_label`.s_lid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, start);
			pst.setInt(2, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Subject subject = new Subject();
				subject.setSubId(rs.getInt("subid"));
				subject.setS_label(getSublabelBySubid(rs.getInt("subid")));
				subject.setSubSummary(rs.getString("subsummary"));
				subject.setSubText(rs.getString("subtext"));
				subject.setSubType(rs.getInt("subtype"));
				subject.setSubAccuracy(rs.getString("subaccuracy"));
				subject.setSubAnswer(rs.getString("subanswer"));
				subject.setSubTime(rs.getInt("subtime"));
				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}

		return subjects;
	}
	
	//通过题目id查找标签，如有多个进行拼接
	public String getSublabelBySubid(int subid) {
		StringBuffer label = new StringBuffer("");
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `s_label`.s_lname ");
		sql.append("FROM `subject`,`s_label`,`subject_s_label` ");
		sql.append("WHERE `subject`.subid = `subject_s_label`.subid ");
		sql.append("AND `s_label`.s_lid = `subject_s_label`.s_lid ");
		sql.append("AND `subject`.subid = ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, subid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				label.append(rs.getString("s_lname"));
				label.append(" ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}
		return label.toString();
	}
	
	
	@Override
	public JSONArray getAllYears() {
		JSONArray ja = new JSONArray();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT `subject`.subtime ");
		sql.append("FROM `subject` ORDER BY `subject`.subtime ASC ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			int i = 0;
			while(rs.next()) {
				i++;
				JSONObject jo = new JSONObject();
				jo.put("id", i);
				jo.put("text", rs.getInt("subtime"));
				ja.add(jo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}
		return ja;
	}

	@Override
	public List<Integer> getAllSubtype() {
		List<Integer> subtypes = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT `subject`.subtype FROM `subject` ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Integer subtype = rs.getInt("subtype");
				subtypes.add(subtype);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}
		return subtypes;
	}

	@Override
	public JSONArray getAllSubLabel() {
		JSONArray ja = new JSONArray();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `s_label`.s_lname FROM `s_label` ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			int i = 0;
			while(rs.next()) {
				i++;
				JSONObject jo = new JSONObject();
				jo.put("id", i);
				jo.put("text", rs.getString("s_lname"));
				ja.add(jo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}
		return ja;

	}

	@Override
	public Object getSubsBySublabel(int start, int pageSize, String subLabel) {
		List<Subject> subjects = new ArrayList<Subject>();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT `subject`.subid, `subject`.subsummary, `subject`.subtext, `subject`.subtype, ");
		sql.append("`subject`.subaccuracy, `subject`.subanswer, `subject`.subtime ");
		sql.append("FROM `subject` LEFT JOIN `subject_s_label` ");
		sql.append("ON `subject`.subid = `subject_s_label`.subid ");
		sql.append("LEFT JOIN `s_label` ");
		sql.append("ON `subject_s_label`.s_lid = `s_label`.s_lid ");
		sql.append("WHERE `s_label`.s_lname = ? ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, subLabel);
			pst.setInt(2, start);
			pst.setInt(3, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Subject subject = new Subject();
				subject.setSubId(rs.getInt("subid"));
				subject.setS_label(getSublabelBySubid(rs.getInt("subid")));
				subject.setSubSummary(rs.getString("subsummary"));
				subject.setSubText(rs.getString("subtext"));
				subject.setSubType(rs.getInt("subtype"));
				subject.setSubAccuracy(rs.getString("subaccuracy"));
				subject.setSubAnswer(rs.getString("subanswer"));
				subject.setSubTime(rs.getInt("subtime"));
				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}

		return subjects;

	}

}
