package com.zr.service.impl;

import java.util.List;

import com.zr.dao.NoteDao;
import com.zr.dao.impl.NoteDaoimpl;
import com.zr.model.N_label;
import com.zr.model.Note;
import com.zr.service.NoteService;

import net.sf.json.JSONObject;
/**
 * 
 * @author liujia
 *
 */

public class NoteServiceimpl implements NoteService {
     NoteDao ndao = new NoteDaoimpl();
	@Override
	public List<Note> getallnotesByn_lname(String n_lname) {
		List<Note> notes = ndao.getAllnotes(n_lname);
		return notes;
	}

	@Override
	public JSONObject getnoteBypageAndpagesize(String n_lname,int page, int pagesize) {
		JSONObject json = new JSONObject();
		json.put("total", ndao.getAllnotes(n_lname));
		json.put("rows", ndao.getnote(n_lname, (page-1)*pagesize, pagesize));
		return json;
	}

	@Override
	public int insertnote(int userid, String notetitle, String notetext, String notesummary) {
		int i = ndao.insertnoteByuidAndnote(userid, notetitle, notetext, notesummary);
		return i;
	}

	@Override
	public int getnoteid(String notetitle) {
		int i = ndao.selectNoteid(notetitle);
		return i;
	}

	@Override
	public int insertNote_lable(int noteid, int n_lid) {
		int i = ndao.insertNote_lable(noteid, n_lid);
		return i;
	}

	@Override
	public int getN_lid(String n_lname) {
		int i = ndao.selectN_lid(n_lname);
		return i;
	}

	@Override
	public String getNotetext(int noteid) {
		String text = ndao.selectNotetext(noteid);
		return text;
	}

	@Override
	public String getNotetitle(int noteid) {
		String title = ndao.selectNotetitle(noteid);
		return title;
	}

	@Override
	public List<N_label> getNotetabel() {
		List<N_label> labels=ndao.selectNotelabel();
		return labels;
	}

	@Override
	public int updatelid(int n_lid,int noteid ) {
		int i = ndao.updatelid(n_lid, noteid);
		return i;
	}

	@Override
	public int updatetext(String notetext,int noteid) {
		int i = ndao.updatetext(notetext, noteid);
		return i;
	}

	@Override
	public int getlid(String lname) {
		int i = ndao.selectN_lid(lname);
		return i;
	}

	@Override
	public String getN_lname(int n_lid) {
		String i= ndao.selectN_lname(n_lid);
		return i;
	}

	@Override
	public int deleteNoteBynoteid(int noteid) {
	    int i = ndao.deleteNote(noteid);
		return i;
	}

	@Override
	public int delectN_n_label(int noteid) {
		int i =ndao.deleteN_n_label(noteid);
		return i;
	}

	@Override
	public int insertNoteTabel(String lname) {
		int i =ndao.insertNoteTabel(lname);
		return i;
	}

}
