package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.SubLabelDao;
import com.zr.model.SubLabel;
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
			JDBCUtil.closeJDBC(pst, con);
		}

		return subLabelCount;
	}

	@Override
	public List<SubLabel> getSubLabels(int start, int pageSize) {
		List<SubLabel> labs = new ArrayList<SubLabel>();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("SELECT `s_label`.s_lid,`s_label`.s_lname,`s_label`.subcount ");
		sql.append("FROM `s_label` ORDER BY `s_label`.s_lid ");
		sql.append("LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, start);
			pst.setInt(2, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				SubLabel lab = new SubLabel();
				lab.setSubLabelId(rs.getInt("s_lid"));
				lab.setSubLabelName(rs.getString("s_lname"));
				lab.setSubCount(rs.getInt("subcount"));
				labs.add(lab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeJDBC(pst, con);
		}
		return labs;
	}

}
