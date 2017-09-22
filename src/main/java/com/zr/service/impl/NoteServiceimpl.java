package com.zr.service.impl;

import java.util.List;

import com.zr.dao.NoteDao;
import com.zr.dao.impl.NoteDaoimpl;
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

}
