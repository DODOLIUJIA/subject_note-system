package com.zr.service;

import java.util.List;

import com.zr.model.N_label;
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
	/**
	 * 插入
	 * @param userid
	 * @param notetitle
	 * @param notetext
	 * @param notesummary
	 * @return
	 */
	public int insertnote(int userid,String notetitle ,String notetext,String notesummary);
	/**
	 * 得到noteid
	 * @param notetitle
	 * @return
	 */
	public int getnoteid(String notetitle);
	/**
	 * 根据标签名的到lid
	 * @param lname
	 * @return
	 */
	public int getlid(String lname);
	/**
	 * 
	 * @param noteid
	 * @param n_lid
	 * @return
	 */
	public int insertNote_lable(int noteid,int n_lid);
	/**
	 * 
	 * @param n_lname
	 * @return
	 */
	public int getN_lid(String n_lname);
	/**
	 * 根据笔记id得到笔记内容
	 * @param notetitle
	 * @return
	 */
	public String getNotetext(int noteid);
	/**
	 * 根据笔记id得到笔记标题
	 * @param noteid
	 * @return
	 */
	public String getNotetitle(int noteid);
	/**
	 * 得到所有标签
	 * @return
	 */
	public List<N_label> getNotetabel();
	
	/**
	 * 根据笔记id 修改标签
	 * @param noteid 笔记id
	 * @return
	 */
	public int updatelid(int n_lid,int noteid );
	/**
	 * 根据标题修改内容
	 * @param notetitle
	 * @return
	 */
	public int updatetext(String notetext,String notetitle, int noteid);
	/**
	 * 根据标签id得到标签名
	 * @param n_lid
	 * @return
	 */
	public String getN_lname(int n_lid);
	/**
	 * 根据笔记id删除笔记
	 * @param noteid
	 * @return
	 */
	public int deleteNoteBynoteid(int noteid);
	/**
	 * 根据笔记id删除关联表
	 * @param noteid
	 * @return
	 */
	public int delectN_n_label(int noteid);
	/**
	 * 插入标签名
	 * @param lname
	 * @return
	 */
	public int insertNoteTabel(String lname);
	
}
