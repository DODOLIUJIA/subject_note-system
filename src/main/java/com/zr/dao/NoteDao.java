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
	/**
	 * 插入笔记
	 * @param userid  用户id
	 * @param notetitle  笔记名
	 * @param notetext   笔记内容
	 * @param notesummary   笔记摘要
	 * @return
	 */
	public int insertnoteByuidAndnote(int userid,String notetitle,String notetext,String notesummary);
	/**
	 * 根据notetitle得到noteid
	 * @param notetitle
	 * @return
	 */
	public int selectNoteid(String notetitle);
	/**
	 * 插入到note_n_lable表
	 * @param noteid
	 * @param n_lid
	 * @return
	 */
	public int insertNote_lable(int noteid,int n_lid);
	/**
	 * 修改总数
	 * @param notecount
	 * @return
	 */
	public int updateN_lable(int notecount);
	/**
	 * 
	 * @param n_lname
	 * @return
	 */
	public int selectN_lid(String n_lname);
}
