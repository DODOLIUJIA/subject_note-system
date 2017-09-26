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
import com.zr.util.DBConnection;

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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		sql.append("ORDER BY `subject`.subid ");
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;
	}
	
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			JSONObject jo = new JSONObject();
			jo.put("id", i);
			jo.put("text", "所有");
			ja.add(jo);
			while(rs.next()) {
				i++;
				JSONObject jo1 = new JSONObject();
				jo1.put("id", i);
				jo1.put("text", rs.getInt("subtime"));
				ja.add(jo1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			subtypes.add(0);
			while(rs.next()) {
				Integer subtype = rs.getInt("subtype");
				subtypes.add(subtype);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subtypes;
  }

	@Override
	public boolean insertNewSubject(String subSummary, String subText, int subType, String subAnswer, int subTime) {
		StringBuffer sql = new StringBuffer("");
		sql.append("insert into `subject`(`subject`.subsummary,`subject`.subtext,");
		sql.append("`subject`.subtype,`subject`.subaccuracy,`subject`.subanswer,`subject`.subtime) ");
		sql.append("VALUES(?,?,?,?,?,?) ");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setString(1, subSummary);
			pre.setString(2, subText);
			pre.setInt(3, subType);
			pre.setString(4, "0%");
			pre.setString(5, subAnswer);
			pre.setInt(6, subTime);
			int i = pre.executeUpdate();
			if (i == 1) {
				DBConnection.CloseConnection(conn, pre);
				return true;
			} else {
				DBConnection.CloseConnection(conn, pre);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Subject getSubjectBySId(int sid) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT * from `subject` ");
		sql.append("where `subject`.subid=?");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, sid);
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				Subject s = new Subject(res.getInt("subid"), res.getString("subsummary"), res.getString("subtext"),
						res.getInt("subtype"), res.getString("subaccuracy"), res.getString("subanswer"),
						res.getInt("subtime"));
				DBConnection.CloseConnection(conn, pre, res);
				return s;
			} else {
				DBConnection.CloseConnection(conn, pre, res);
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateSubject(int sid, String subSummary, String subText, int subType, String subAnswer,
			int subTime) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE `subject` ");
		sql.append("SET `subject`.subsummary=?,`subject`.subtext=?, ");
		sql.append("`subject`.subtype=?,`subject`.subanswer=?,`subject`.subtime=? ");
		sql.append("where `subject`.subid=? ");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setString(1, subSummary);
			pre.setString(2, subText);
			pre.setInt(3, subType);
			pre.setString(4, subAnswer);
			pre.setInt(5, subTime);
			pre.setInt(6, sid);
			int i = pre.executeUpdate();
			if (i == 1) {
				DBConnection.CloseConnection(conn, pre);
				return true;
			} else {
				DBConnection.CloseConnection(conn, pre);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

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
			JSONObject jo = new JSONObject();
			jo.put("id", i);
			jo.put("text", "所有");
			ja.add(jo);
			while(rs.next()) {
				i++;
				JSONObject jo1 = new JSONObject();
				jo1.put("id", i);
				jo1.put("text", rs.getString("s_lname"));
				ja.add(jo1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ja;

	}

	@Override
	public List<Subject> getSubsBySublabel(int start, int pageSize, String subLabel) {
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
		sql.append("ORDER BY `subject`.subid ");
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;

	}
	
	@Override
	public List<Subject> getSubsBySubType(int start, int pageSize, int subTypeI) {
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
		sql.append("WHERE `subject`.subtype = ? ");
		sql.append("ORDER BY `subject`.subid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, subTypeI);
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;

	}
	
	@Override
	public List<Subject> getSubsBySubYear(int start, int pageSize, int yearInt) {
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
		sql.append("WHERE `subject`.subtime = ? ");
		sql.append("ORDER BY `subject`.subid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, yearInt);
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;
	}
	
	@Override
	public List<Subject> getSubsBySubTypeAndSubLabel(int start, int pageSize, int subTypeI, String subLabel) {
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
		sql.append("WHERE `subject`.subtype = ? AND `s_label`.s_lname = ? ");
		sql.append("ORDER BY `subject`.subid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, subTypeI);
			pst.setString(2, subLabel);
			pst.setInt(3, start);
			pst.setInt(4, pageSize);
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;

	}

	@Override
	public List<Subject> getSubsBySubYearAndSubLabel(int start, int pageSize, int yearInt, String subLabel) {
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
		sql.append("WHERE `subject`.subtime = ? AND `s_label`.s_lname = ? ");
		sql.append("ORDER BY `subject`.subid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, yearInt);
			pst.setString(2, subLabel);
			pst.setInt(3, start);
			pst.setInt(4, pageSize);
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;

	}

	@Override
	public List<Subject> getSubsBySubYearAndSubType(int start, int pageSize, int yearInt, int subTypeI) {
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
		sql.append("WHERE `subject`.subtime = ? AND `subject`.subtype = ? ");
		sql.append("ORDER BY `subject`.subid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, yearInt);
			pst.setInt(2, subTypeI);
			pst.setInt(3, start);
			pst.setInt(4, pageSize);
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;
	}

	@Override
	public List<Subject> getSubsBySubYearAndSubTypeAndSubLabel(int start, int pageSize, int yearInt, int subTypeI,	String subLabel) {
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
		sql.append("WHERE `subject`.subtime = ? AND `subject`.subtype = ? AND `s_label`.s_lname = ? ");
		sql.append("ORDER BY `subject`.subid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, yearInt);
			pst.setInt(2, subTypeI);
			pst.setString(3, subLabel);
			pst.setInt(4, start);
			pst.setInt(5, pageSize);
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
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subjects;
	}


	@Override
	public void deleteSubBySubId(int subId) {

		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		StringBuffer sql1 = new StringBuffer("");
		StringBuffer sql2 = new StringBuffer("");
		StringBuffer sql3 = new StringBuffer("");
		sql1.append("DELETE FROM `subject` WHERE `subject`.subid = ? ");
		sql2.append("DELETE FROM `subject_s_label` WHERE `subject_s_label`.subid = ? ");
		sql3.append("DELETE FROM `selectanswer` WHERE `selectanswer`.subid = ? ");
		
		try {
			con = JDBCUtil.getConnection();
			con.setAutoCommit(false);
			pst1 = con.prepareStatement(sql1.toString());
			pst2 = con.prepareStatement(sql2.toString());
			pst3 = con.prepareStatement(sql3.toString());
			pst1.setInt(1, subId);
			pst2.setInt(1, subId);
			pst3.setInt(1, subId);
			pst1.executeUpdate();
			pst2.executeUpdate();
			pst3.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				JDBCUtil.closeJDBC(pst1, con);
				JDBCUtil.closeJDBC(pst2, con);
				JDBCUtil.closeJDBC(pst3, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
