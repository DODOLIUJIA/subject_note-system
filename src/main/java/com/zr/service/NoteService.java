package com.zr.service;

import java.util.List;

import com.zr.model.Note;

import net.sf.json.JSONObject;

/**
 * 
 * @author liujia
 *
 */
public interface NoteService {

	/**
	 * 通过笔记标签名得到所有笔记
	 * @param n_lname   笔记标签名
	 * @return
	 */
	public List<Note> getallnotesByn_lname(String n_lname);
	/**
	 * 通过页码和页面大小得到笔记
	 * @param page    页码
	 * @param pagesize   页面大小
	 * @return
	 */
	public JSONObject getnoteBypageAndpagesize(String n_lname,int page ,int pagesize);
}
