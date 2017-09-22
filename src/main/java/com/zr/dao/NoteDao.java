package com.zr.dao;

import java.util.List;

import com.zr.model.Note;
/**
 * 
 * @author liujia
 *
 */
public interface NoteDao {

	/**
	 * 
	 * @param n_lname
	 * @return
	 */
	public List<Note> getAllnotes(String n_lname);
	
	public List<Note> getnote(String n_lname,int start,int size);
}
