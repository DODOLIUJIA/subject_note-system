package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.NoteDao;
import com.zr.model.N_label;
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
		sql.append("update n_label set notecount = ");
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

	@Override
	public String selectNotetext(int  noteid) {
		String text = null;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("select notetext from note where noteid = ?");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, noteid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				text = rs.getString("notetext");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}

	@Override
	public String selectNotetitle(int noteid) {
		String title = null;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("select notetitle from note where noteid = ?");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, noteid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				title = rs.getString("notetitle");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}

	@Override
	public List<N_label> selectNotelabel() {
		List<N_label> labels = new ArrayList<N_label>();
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("select n_lid,n_lname from n_label");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				N_label label = new N_label();
				label.setN_lid(rs.getInt("n_lid"));
				label.setN_lname(rs.getString("n_lname"));
				labels.add(label);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return labels;
	}

	@Override
	public int updatelid( int n_lid,int noteid) {
		int i = 0;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("update note_n_label set n_lid = ? where noteid = ? ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, n_lid);
			pst.setInt(2, noteid);
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int updatetext(String notetext,String notetitle,int noteid) {
		int i = 0;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("update note set notetext = ?,notetitle=?  where noteid = ? ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, notetext);
			pst.setString(2, notetitle);
			pst.setInt(3, noteid);
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public String selectN_lname(int n_lid) {
		String lname = null;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("select n_lname from n_label  ");
		sql.append("where n_label.n_lid=?");
		
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, n_lid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				lname =rs.getString("n_lname");
				//System.out.println(lid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lname;
	}

	@Override
	public int deleteNote(int noteid) {
		int i = 0;
		StringBuffer sql = new StringBuffer ();
		Connection con = JDBCUtil.getConnection();
		sql.append("delete from note where note.noteid=?");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, noteid);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int deleteN_n_label(int noteid) {
		int i = 0;
		StringBuffer sql = new StringBuffer ();
		Connection con = JDBCUtil.getConnection();
		sql.append("delete from note_n_label where noteid=?");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, noteid);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		}

	@Override
	public int insertNoteTabel(String lname) {
		int i =0;
		StringBuffer sql = new  StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("insert into n_label(n_lname) ");
		sql.append("value (?)");	
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
		   pst.setString(1,lname);
			i=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

}
