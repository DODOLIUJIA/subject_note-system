package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.SubLabelDao;
import com.zr.model.SubjectLabel;
import com.zr.util.JDBCUtil;

public class SubLabelDaoImpl implements SubLabelDao{

	@Override
	public int getSubLabelsCount() {
		int subLabelCount = 0;
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(`s_label`.s_lid) counts FROM `s_label` ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				subLabelCount=rs.getInt("counts");
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

		return subLabelCount;
	}

	@Override
	public List<SubjectLabel> getsub_label() {
		List<SubjectLabel> sllist = new ArrayList<SubjectLabel>();
		
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT * FROM `s_label` ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				SubjectLabel sl = new SubjectLabel();
				sl.setS_lname(rs.getString(2));
				sl.setSubcount(rs.getInt(3));
				sllist.add(sl);
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

		return sllist;
	}

}
