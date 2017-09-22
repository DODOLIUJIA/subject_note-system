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

}
