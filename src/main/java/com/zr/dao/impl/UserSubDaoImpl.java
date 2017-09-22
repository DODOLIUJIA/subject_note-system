package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.UserSubDao;
import com.zr.model.Sub;
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
	public List<Sub> selectSubsBySubType(String sublabel) {
		List<Sub> subs = new ArrayList<Sub>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `subject`.subaccuracy,`subject`.subanswer,`subject`.subid,`subject`.subsummary,`subject`.subtext,`subject`.subtime,`subject`.subtype ");
		sql.append("FROM `subject` ");
		sql.append("INNER JOIN subject_s_label ");
		sql.append("ON `subject`.subid = subject_s_label.subid ");
		sql.append("INNER JOIN s_label ");
		sql.append("ON s_label.s_lid = subject_s_label.s_lid ");
		sql.append("WHERE s_label.s_lname=? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, sublabel);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Sub s = new Sub();
				s.setSubaccuracy(rs.getString("subaccuracy"));
				s.setSubanswer(rs.getString("subanswer"));
				s.setSubid(rs.getInt("subid"));
				s.setSubsummary(rs.getString("subsummary"));
				s.setSubtext(rs.getString("subtext"));
				s.setSubtime(rs.getInt("subtime"));
				s.setSubtype(rs.getInt("subtype"));
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
	public List<Sub> selectSubsBySubTime(int subtime) {
		List<Sub> subs = new ArrayList<Sub>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `subject`.subaccuracy,`subject`.subanswer,`subject`.subid,`subject`.subsummary,`subject`.subtext,`subject`.subtime,`subject`.subtype ");
		sql.append("FROM `subject` ");
		sql.append("WHERE `subject`.subtime=? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, subtime);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Sub s = new Sub();
				s.setSubaccuracy(rs.getString("subaccuracy"));
				s.setSubanswer(rs.getString("subanswer"));
				s.setSubid(rs.getInt("subid"));
				s.setSubsummary(rs.getString("subsummary"));
				s.setSubtext(rs.getString("subtext"));
				s.setSubtime(rs.getInt("subtime"));
				s.setSubtype(rs.getInt("subtype"));
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
	public List<Sub> selectSubsBySubTypeAndSubTime(String sublabel,int subtime) {
		List<Sub> subs = new ArrayList<Sub>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `subject`.subaccuracy,`subject`.subanswer,`subject`.subid,`subject`.subsummary,`subject`.subtext,`subject`.subtime,`subject`.subtype ");
		sql.append("FROM `subject` ");
		sql.append("INNER JOIN subject_s_label ");
		sql.append("ON `subject`.subid = subject_s_label.subid ");
		sql.append("INNER JOIN s_label ");
		sql.append("ON s_label.s_lid = subject_s_label.s_lid ");
		sql.append("WHERE `subject`.subtime=? AND s_label.s_lname=?");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, sublabel);
			pst.setInt(2, subtime);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Sub s = new Sub();
				s.setSubaccuracy(rs.getString("subaccuracy"));
				s.setSubanswer(rs.getString("subanswer"));
				s.setSubid(rs.getInt("subid"));
				s.setSubsummary(rs.getString("subsummary"));
				s.setSubtext(rs.getString("subtext"));
				s.setSubtime(rs.getInt("subtime"));
				s.setSubtype(rs.getInt("subtype"));
				subs.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subs;
	}

}
