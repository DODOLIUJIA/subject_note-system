package com.zr.dao;

import java.util.List;

import com.zr.model.N_label;
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
	 * 获取标签id
	 * @param n_lname
	 * @return
	 */
	public int selectN_lid(String n_lname);
	/**
	 * 根据标题选出笔记内容
	 * @param notetitle
	 * @return
	 */
	public String selectNotetext(int noteid);
	/**
	 * 根据笔记id选出笔记标题
	 * @param noteid
	 * @return
	 */
	public String selectNotetitle(int noteid);
	/**
	 * 获取全部的笔记标签
	 * @return
	 */
	public List<N_label> selectNotelabel();
	/**
	 * 根据笔记id修改n_lid
	 * @param noteid
	 * @return
	 */
	public int updatelid( int n_lid,int noteid);
	/**
	 * 根据笔记id修改笔记内容
	 * @param noteid
	 * @return
	 */
	public int updatetext(String notetext,String notetitle,int noteid);
	/**
	 * 获取标签名
	 * @param n_lid
	 * @return
	 */
   public String selectN_lname(int n_lid);
   /**
    * 根据笔记id删除笔记
    * @param noteid
    * @return
    */
   public  int deleteNote(int noteid);
   /**
    * 根据笔记id删除关联表
    * @param noteid
    * @return
    */
   public int deleteN_n_label(int noteid);
   /**
    * 插入标签
    * @param lname
    * @return
    */
   public int insertNoteTabel(String lname);
	
}
