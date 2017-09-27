package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.UserSubDao;
import com.zr.model.Subject;
import com.zr.model.SubjectLabel;
import com.zr.util.JDBCUtil;

/**
 * 
 * @author 吴尚鑫
 *
 */
public class UserSubDaoImpl implements UserSubDao {
	/**
	 * 通过题目标签筛选题目
	 * 
	 * @param sublabel
	 *            题目标签
	 * @return
	 */
	public List<Subject> selectSubsBySubType(String sublabel ,int loadtimms) {
		List<Subject> subs = new ArrayList<Subject>();
		StringBuffer sql = new StringBuffer("");
		int begin = loadtimms*3;
		int end = 3;
		sql.append("SELECT `subject`.subaccuracy,`subject`.subanswer,`subject`.subid,`subject`.subsummary,`subject`.subtext,`subject`.subtime,`subject`.subtype ");
		sql.append("FROM `subject` ");
		sql.append("INNER JOIN subject_s_label ");
		sql.append("ON `subject`.subid = subject_s_label.subid ");
		sql.append("INNER JOIN s_label ");
		sql.append("ON s_label.s_lid = subject_s_label.s_lid ");
		sql.append("WHERE s_label.s_lname=? ");
		sql.append("ORDER BY subtime ");
		sql.append("LIMIT ?,? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, sublabel);
			pst.setInt(2, begin);
			pst.setInt(3, end);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Subject s = new Subject();
				s.setSubAccuracy(rs.getString("subaccuracy"));
				s.setSubAnswer(rs.getString("subanswer"));
				s.setSubId(rs.getInt("subid"));
				s.setSubSummary(rs.getString("subsummary"));
				s.setSubText(rs.getString("subtext"));
				s.setSubTime(rs.getInt("subtime"));
				s.setSubType(rs.getInt("subtype"));
				subs.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subs;
	}

	/**
	 * 通过出题时间筛选题目
	 * 
	 * @param subtime
	 *            题目标签
	 * @return
	 */
	public List<Subject> selectSubsBySubTime(int subtime, int loadtimms) {
		List<Subject> subs = new ArrayList<Subject>();
		StringBuffer sql = new StringBuffer("");
		int begin = loadtimms*3;
		int end = 3;
		sql.append("SELECT `subject`.subaccuracy,`subject`.subanswer,`subject`.subid,`subject`.subsummary,`subject`.subtext,`subject`.subtime,`subject`.subtype ");
		sql.append("FROM `subject` ");
		sql.append("WHERE `subject`.subtime=? ");
		sql.append("ORDER BY subtime ");
		sql.append("LIMIT ?,? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, subtime);
			pst.setInt(2, begin);
			pst.setInt(3, end);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Subject s = new Subject();
				s.setSubAccuracy(rs.getString("subaccuracy"));
				s.setSubAnswer(rs.getString("subanswer"));
				s.setSubId(rs.getInt("subid"));
				s.setSubSummary(rs.getString("subsummary"));
				s.setSubText(rs.getString("subtext"));
				s.setSubTime(rs.getInt("subtime"));
				s.setSubType(rs.getInt("subtype"));
				subs.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subs;
	}

	/**
	 * 通过题目标签和出题时间筛选题目
	 * 
	 * @param sublabel
	 *            题目标签
	 * @param subtime
	 *            出题时间
	 * @return
	 */
	public List<Subject> selectSubsBySubTypeAndSubTime(String sublabel,int subtime,int loadtimms) {
		List<Subject> subs = new ArrayList<Subject>();
		int begin = loadtimms*3;
		int end = 3;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `subject`.subaccuracy,`subject`.subanswer,`subject`.subid,`subject`.subsummary,`subject`.subtext,`subject`.subtime,`subject`.subtype ");
		sql.append("FROM `subject` ");
		sql.append("INNER JOIN subject_s_label ");
		sql.append("ON `subject`.subid = subject_s_label.subid ");
		sql.append("INNER JOIN s_label ");
		sql.append("ON s_label.s_lid = subject_s_label.s_lid ");
		sql.append("WHERE `subject`.subtime=? AND s_label.s_lname=? ");
		sql.append("ORDER BY subtime ");
		sql.append("LIMIT ?,? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, subtime);
			pst.setString(2, sublabel);
			pst.setInt(3, begin);
			pst.setInt(4, end);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Subject s = new Subject();
				s.setSubAccuracy(rs.getString("subaccuracy"));
				s.setSubAnswer(rs.getString("subanswer"));
				s.setSubId(rs.getInt("subid"));
				s.setSubSummary(rs.getString("subsummary"));
				s.setSubText(rs.getString("subtext"));
				s.setSubTime(rs.getInt("subtime"));
				s.setSubType(rs.getInt("subtype"));
				subs.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subs;
	}

	/**
	 * 得到所有的题目标签
	 * @return
	 */
	@Override
	public List<SubjectLabel> selectSubLabel() {
		List<SubjectLabel> labels = new ArrayList<SubjectLabel>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT s_label.s_lname ");
		sql.append("FROM s_label ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				SubjectLabel s_label = new SubjectLabel();
				s_label.setS_lname(rs.getString("s_label.s_lname"));
				labels.add(s_label);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return labels;
	}

	/**
	 * 得到所有题目的不同的出题时间
	 * @return
	 */
	public List<Subject> selectSubTime(){
		List<Subject> times = new ArrayList<Subject>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT `subject`.subtime ");
		sql.append("from `subject` ");
		sql.append("ORDER BY `subject`.subtime ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Subject s_time = new Subject();
				s_time.setSubTime(rs.getInt("subtime"));
				times.add(s_time);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return times;
	}
}
