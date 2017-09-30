package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.SubLabelDao;
import com.zr.model.SubjectLabel;
import com.zr.util.DBConnection;
import com.zr.util.JDBCUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 胡逸提标签类
 * 
 * @author GodIsEvil
 *
 */
public class SubLabelDaoImpl implements SubLabelDao {

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
			if (rs.next()) {
				subLabelCount = rs.getInt("counts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			while (rs.next()) {
				SubjectLabel sl = new SubjectLabel();
				sl.setS_lname(rs.getString(2));
				sl.setSubcount(rs.getInt(3));
				sllist.add(sl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return sllist;
	}

	@Override
	public JSONArray getSubLabels(int start, int pageSize) {

		JSONArray subLabs = new JSONArray();
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("SELECT s_label.s_lid, s_label.s_lname ");
		sql.append("FROM `s_label`  ORDER BY s_label.s_lid LIMIT ? , ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, start);
			pst.setInt(2, pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				JSONObject lab = new JSONObject();
				lab.put("s_lid", rs.getString("s_lid"));
				lab.put("s_lname", rs.getString("s_lname"));
				lab.put("subCount", this.getSubCountByS_lid(Integer.parseInt(rs.getString("s_lid"))));
				subLabs.add(lab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subLabs;
	}

	@Override
	public int getSubCountByS_lid(int s_lid) {
		int subLabelCount = 0;
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append(
				"SELECT COUNT(`subject_s_label`.s_lid) counts FROM subject_s_label WHERE  subject_s_label.s_lid = ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, s_lid);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				subLabelCount = rs.getInt("counts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return subLabelCount;
	}

	@Override
	public JSONObject insertNewLabAndeExist(String newLabName) {
		JSONObject jo = new JSONObject();
		if (this.subLabelExist(newLabName)) {
			jo.put("success", false);
		} else {
			Connection con = null;
			PreparedStatement pst = null;
			StringBuffer sql = new StringBuffer("");
			sql.append("INSERT INTO s_label(s_label.s_lname) VALUES ( ? ) ");
			try {
				con = JDBCUtil.getConnection();
				pst = con.prepareStatement(sql.toString());
				pst.setString(1, newLabName);
				pst.executeUpdate();
				jo.put("success", true);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					JDBCUtil.closeJDBC(pst, con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return jo;
	}

	@Override
	public boolean subLabelExist(String newLabName) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT s_label.s_lname FROM s_label WHERE s_label.s_lname = ? ");
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, newLabName);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public JSONObject deleteSubLabelByS_lid(int s_lid) {
		JSONObject jo = new JSONObject();
		boolean flag = false;
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		StringBuffer sql1 = new StringBuffer("DELETE FROM `s_label` WHERE `s_label`.s_lid = ? ");
		StringBuffer sql2 = new StringBuffer("DELETE FROM subject_s_label WHERE subject_s_label.s_lid = ? ");
		try {
			con = JDBCUtil.getConnection();
			con.setAutoCommit(false);
			pst1 = con.prepareStatement(sql1.toString());
			pst2 = con.prepareStatement(sql2.toString());
			pst1.setInt(1, s_lid);
			pst2.setInt(1, s_lid);
			pst1.executeUpdate();
			pst2.executeUpdate();
			con.commit();
			flag = true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.closeJDBC(pst1, con);
				JDBCUtil.closeJDBC(pst2, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		jo.put("success", flag);
		return jo;
	}

	@Override
	public JSONObject updataS_LabAndeExist(String updataS_LabName, int s_lid) {
		JSONObject jo = new JSONObject();
		boolean flag = false;
		if (!subLabelExist(updataS_LabName)) {
			Connection con = null;
			PreparedStatement pst1 = null;
			StringBuffer sql1 = new StringBuffer("UPDATE s_label SET s_label.s_lname = ? WHERE s_label.s_lid = ? ");
			try {
				con = JDBCUtil.getConnection();
				pst1 = con.prepareStatement(sql1.toString());
				pst1.setString(1, updataS_LabName);
				pst1.setInt(2, s_lid);
				pst1.executeUpdate();
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					JDBCUtil.closeJDBC(pst1, con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		jo.put("success", flag);
		return jo;
	}

	@Override
	public int[] getAllSubjectLabelID() {
		StringBuffer sql = new StringBuffer("");
		List<Integer> list = new ArrayList<Integer>();
		sql.append("SELECT s_label.s_lid from s_label");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				list.add(res.getInt("s_lid"));
			}
			int[] i = new int[list.size()];
			for (int j = 0; j < i.length; j++) {
				i[j] = list.get(j);
			}
			DBConnection.CloseConnection(conn, pre, res);
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateAllSubjectLabelCount() {
		StringBuffer sql = new StringBuffer("");
		sql.append("call updatecount();");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public JSONArray getAllLabelValueAndText() {
		StringBuffer sql =new StringBuffer("");
		JSONArray json =new JSONArray();
		sql.append("SELECT s_label.s_lid,s_label.s_lname ");
		sql.append("from s_label");
		Connection conn=DBConnection.getConnection();
		try {
			PreparedStatement pre=conn.prepareStatement(sql.toString());
			ResultSet res=pre.executeQuery();
			while(res.next()){
				JSONObject j=new JSONObject();
				j.put("value", res.getInt("s_lid"));
				j.put("text", res.getString("s_lname"));
				json.add(j);
			}
			return json;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		SubLabelDaoImpl s=new SubLabelDaoImpl();
	}

	@Override
	public boolean insertNewSSLabel(int subID, int[] lid) {
		StringBuffer sql=new StringBuffer("");
		sql.append("INSERT INTO subject_s_label(subject_s_label.s_lid,subject_s_label.subid) ");
		sql.append("VALUES (?,?)");
		Connection conn=DBConnection.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement pre=conn.prepareStatement(sql.toString());
			for (int i : lid) {
				pre.setInt(1, i);
				pre.setInt(2, subID);
				pre.executeUpdate();
			}
			conn.commit();
			DBConnection.CloseConnection(conn, pre);
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteFromSubSSLabelBySubID(int subID) {
		StringBuffer sql=new StringBuffer("");
		sql.append("delete from subject_s_label ");
		sql.append("where subject_s_label.subid=?");
		Connection conn=DBConnection.getConnection();
		try {
			PreparedStatement pre=conn.prepareStatement(sql.toString());
			pre.setInt(1, subID);
			pre.executeUpdate();
			DBConnection.CloseConnection(conn, pre);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
