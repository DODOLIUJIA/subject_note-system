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
		sql.append("SELECT `subject`.subid, `subject`.subsummary, `subject`.subtext, `subject`.subtype, ");
		sql.append("`subject`.subaccuracy, `subject`.subanswer, `subject`.subtime, `s_label`.s_lname ");
		sql.append("FROM `subject`,`s_label`,`subject_s_label` ");
		sql.append("WHERE `subject`.subid = `subject_s_label`.subid AND `s_label`.s_lid = `subject_s_label`.s_lid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, start);
			pst.setInt(2, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Subject subject = new Subject();
				subject.setSubid(rs.getInt("subid"));
				subject.setSubsummary(rs.getString("subsummary"));
				subject.setSubtext(rs.getString("subtext"));
				subject.setSubtype(rs.getInt("subtype"));
				subject.setSubaccuracy(rs.getString("subaccuracy"));
				subject.setSubanswer(rs.getString("subanswer"));
				subject.setSubtime(rs.getInt("subtime"));
				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}

		return subjects;
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
	public boolean insertNewSubject(String subSummary, String subText, int subType, String subAnswer, int subTime) {
		StringBuffer sql=new StringBuffer("");
		sql.append("insert into `subject`(`subject`.subsummary,`subject`.subtext,");
		sql.append("`subject`.subtype,`subject`.subaccuracy,`subject`.subanswer,`subject`.subtime) ");
		sql.append("VALUES(?,?,?,?,?,?) ");
		Connection conn=DBConnection.getConnection();
		try {
			PreparedStatement pre=conn.prepareStatement(sql.toString());
			pre.setString(1, subSummary);
			pre.setString(2, subText);
			pre.setInt(3, subType);
			pre.setString(4, "0%");
			pre.setString(5, subAnswer);
			pre.setInt(6, subTime);
			int i=pre.executeUpdate();
			if(i==1){
				DBConnection.CloseConnection(conn, pre );
				return true;
			}else{
				DBConnection.CloseConnection(conn, pre );
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Subject getSubjectBySId(int sid) {
		StringBuffer sql=new StringBuffer("");
		sql.append("SELECT * from `subject` ");
		sql.append("where `subject`.subid=?");
		Connection conn=DBConnection.getConnection();
		try {
			PreparedStatement pre=conn.prepareStatement(sql.toString());
			pre.setInt(1, sid);
			ResultSet res=pre.executeQuery();
			if(res.next()){
				Subject s=new Subject(res.getInt("subid"), res.getString("subsummary"), res.getString("subtext"), 
						res.getInt("subtype"), res.getString("subaccuracy"), res.getString("subanswer"), res.getInt("subtime"));
				DBConnection.CloseConnection(conn, pre, res);
				return s;
			}else{
				DBConnection.CloseConnection(conn, pre, res);
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
