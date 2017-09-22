package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.NoteDao;
import com.zr.model.Note;
import com.zr.util.JDBCUtil;

public class NoteDaoimpl implements NoteDao {

	@Override
	public List<Note> getAllnotes(String n_lname) {
		List<Note> notes = new ArrayList<Note>();
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("select noteid,notetitle,notesummary from note  where note.noteid   ");
		sql.append("in (select note_n_label.noteid from note_n_label  ");
		sql.append("where note_n_label.n_lid in  ");
		sql.append("(select n_label.n_lid from n_label ");
		sql.append("where n_label.n_lname=?))");
		
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, n_lname);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Note note = new Note();
				note.setNoteid(rs.getInt("noteid"));
				note.setNotetitle(rs.getString("notetitle"));
				note.setNotesummary(rs.getString("notesummary"));
				notes.add(note);
				//System.out.println(notes.size());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	@Override
	public List<Note> getnote(String n_lname,int start, int size) {
		List<Note> notes =new  ArrayList<Note>();
         StringBuffer sql = new StringBuffer();
         Connection con = JDBCUtil.getConnection();
         sql.append("select noteid,notetitle,notesummary from note  where note.noteid   ");
 		sql.append("in (select note_n_label.noteid from note_n_label  ");
 		sql.append("where note_n_label.n_lid in  ");
 		sql.append("(select n_label.n_lid from n_label ");
 		sql.append("where n_label.n_lname=?)) limit ?,?");
 		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, n_lname);
			pst.setInt(2, start);
			pst.setInt(3, size);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Note note = new Note();
				note.setNoteid(rs.getInt("noteid"));
				note.setNotetitle(rs.getString("notetitle"));
				note.setNotesummary(rs.getString("notesummary"));
				notes.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	@Override
	public int insertnoteByuidAndnote(int userid, String notetitle, String notetext, String notesummary) {
		int i =0;
		StringBuffer sql = new  StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("insert into note(userid,notetitle,notetext,notesummary) ");
		sql.append("VALUE (?,?,?,?)");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, userid);
			pst.setString(2, notetitle);
			pst.setString(3, notetext);
			pst.setString(4, notesummary);
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int selectNoteid(String notetitle) {
		int nid =0;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("select noteid from note  ");
		sql.append("where notetitle=?");
		
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, notetitle);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				nid = rs.getInt("noteid");
			//	System.out.println(nid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nid;
	}

	@Override
	public int insertNote_lable(int noteid, int n_lid) {
		int i =0;
		StringBuffer sql = new  StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("insert into note_n_label(n_lid,noteid) ");
		sql.append("value (?,?)");	
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, n_lid);
		   pst.setInt(2,noteid);
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int updateN_lable(int notecount) {
	    int i = 0;
		StringBuffer sql = new  StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("update n_lable set notecount = ");
		sql.append("(select count(n_lid) as count from note_n_label)");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
		
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int selectN_lid(String n_lname) {
		int lid =0;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("select n_lid from n_label  ");
		sql.append("where n_label.n_lname=?");
		
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, n_lname);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				lid = rs.getInt("n_lid");
				//System.out.println(lid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lid;
	}

}
