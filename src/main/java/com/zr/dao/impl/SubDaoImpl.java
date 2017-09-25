package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zr.dao.SubDao;
import com.zr.model.Subject;
import com.zr.util.DBConnection;

public class SubDaoImpl implements SubDao {

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
}
