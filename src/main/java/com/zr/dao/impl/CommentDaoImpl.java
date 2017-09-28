package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.ConnectionEvent;

import com.zr.dao.CommentDao;
import com.zr.model.Comment;
import com.zr.util.DBConnection;

public class CommentDaoImpl implements CommentDao {

	@Override
	public List<Comment> getCommentBySubID(int sid) {
		List<Comment> list = new ArrayList<Comment>();
		StringBuffer sql = new StringBuffer("");
		sql.append("select `comment`.comid,`comment`.userid,`comment`.subid, ");
		sql.append("`comment`.comtext,`comment`.likenums,`comment`.unlikenums, ");
		sql.append("`comment`.create_datetime,`user`.username ");
		sql.append("from `comment`,`user` ");
		sql.append("where `comment`.userid=`user`.userid and `comment`.subid=? ");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, sid);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				Comment c = new Comment();
				c.setComID(res.getInt("comid"));
				c.setUserID(res.getInt("userid"));
				c.setSubID(res.getInt("subid"));
				c.setComText(res.getString("comtext"));
				c.setLikeNums(res.getInt("likenums"));
				c.setUnLikeNums(res.getShort("unlikenums"));
				c.setCreate_DateTime(res.getString("create_datetime"));
				c.setUserName(res.getString("username"));
				list.add(c);
			}
			DBConnection.CloseConnection(conn, pre, res);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insertComment(int sid, int uid, String comment) {
		StringBuffer sql = new StringBuffer("");
		sql.append("INSERT INTO `comment`(`comment`.subid,`comment`.userid,");
		sql.append("`comment`.comtext,`comment`.likenums,`comment`.unlikenums) ");
		sql.append("VALUES (?,?,?,0,0)");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, sid);
			pre.setInt(2, uid);
			pre.setString(3, comment);
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
	public boolean addLikeNums(int cid) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE `comment` ");
		sql.append("SET `comment`.likenums=`comment`.likenums+1 ");
		sql.append("WHERE `comment`.comid=?");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, cid);
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
	public boolean addUnLikeNums(int cid) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE `comment` ");
		sql.append("SET `comment`.unlikenums=`comment`.unlikenums+1 ");
		sql.append("WHERE `comment`.comid=?");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, cid);
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
	public int getLikeNums(int cid) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `comment`.likenums FROM `comment` WHERE `comment`.comid=?");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, cid);
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				int num = res.getInt("likenums");
				DBConnection.CloseConnection(conn, pre, res);
				return num;
			} else {
				DBConnection.CloseConnection(conn, pre, res);
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public int getUnLikeNums(int cid) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT `comment`.unlikenums FROM `comment` WHERE `comment`.comid=?");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, cid);
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				int num = res.getInt("unlikenums");
				DBConnection.CloseConnection(conn, pre, res);
				return num;
			} else {
				DBConnection.CloseConnection(conn, pre, res);
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean deleteCommentByCID(int cid) {
		StringBuffer sql = new StringBuffer("");
		sql.append("delete from `comment` ");
		sql.append("where `comment`.comid=?");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, cid);
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
	public List<Comment> getAllComments() {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT * FROM `comment`");
		List<Comment> list = new ArrayList<Comment>();
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				Comment c = new Comment();
				c.setComID(res.getInt("comid"));
				c.setUserID(res.getInt("userid"));
				c.setSubID(res.getInt("subid"));
				c.setComText(res.getString("comtext"));
				c.setLikeNums(res.getInt("likenums"));
				c.setUnLikeNums(res.getInt("unlikenums"));
				c.setCreate_DateTime(res.getString("create_datetime"));
				list.add(c);
			}
			if (list.size() == 0) {
				DBConnection.CloseConnection(conn, pre, res);
				return null;
			} else {
				DBConnection.CloseConnection(conn, pre, res);
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCommentsSum() {
		StringBuffer sql = new StringBuffer("");
		sql.append("select COUNT(1) from `comment`");
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				int r = res.getInt("COUNT(1)");
				DBConnection.CloseConnection(conn, pre, res);
				return r;
			} else {
				DBConnection.CloseConnection(conn, pre, res);
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Comment> getCommentsByLimit(int start, int size) {
		StringBuffer sql = new StringBuffer("");
		sql.append("select * from `comment` LIMIT ?,?");
		List<Comment> list = new ArrayList<Comment>();
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setInt(1, start);
			pre.setInt(2, size);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				Comment c = new Comment();
				c.setComID(res.getInt("comid"));
				c.setUserID(res.getInt("userid"));
				c.setSubID(res.getInt("subid"));
				c.setComText(res.getString("comtext"));
				c.setLikeNums(res.getInt("likenums"));
				c.setUnLikeNums(res.getInt("unlikenums"));
				c.setCreate_DateTime(res.getString("create_datetime"));
				list.add(c);
			}
			if (list.size() == 0) {
				DBConnection.CloseConnection(conn, pre, res);
				return null;
			} else {
				DBConnection.CloseConnection(conn, pre, res);
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteCommentByCIDArray(int[] cids) {
		StringBuffer sql = new StringBuffer("");
		sql.append("delete from `comment` ");
		sql.append("where `comment`.comid=?");
		Connection conn = DBConnection.getConnection();
		try {
			conn.setAutoCommit(false);     //关闭自动提交
			for (int i : cids) {
				PreparedStatement pre=conn.prepareStatement(sql.toString());
				pre.setInt(1, i);
				pre.executeUpdate();
				pre.close();
			}
			conn.commit();
			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
