package com.zr.dao;

import java.util.List;

import com.zr.model.Comment;

public interface CommentDao {

	/**
	 * 通过题目ID来获得该题目的所有评论
	 * @param sid题目ID
	 * @return返回一个装有该题目所有评论的LIST对象
	 */
	public List<Comment> getCommentBySubID(int sid);
	
	/**
	 * 插入一条新的评论
	 * @param sid题目ID
	 * @param uid用户ID
	 * @param comment评论内容
	 * @return
	 */
	public boolean insertComment(int sid,int uid,String comment);
	
	/**
	 * 通过评论ID来给评论点赞
	 * @param cid评论ID
	 * @return 点赞成功返回true，点赞失败返回false
	 */
	public boolean addLikeNums(int cid);
	
	/**
	 * 通过评论ID来踩某条评论
	 * @param cid评论ID
	 * @return 踩成功返回true，踩失败返回false
	 */
	public boolean addUnLikeNums(int cid);
	
	/**
	 * 通过评论ID来获得当前评论赞数
	 * @param cid评论ID
	 * @return 返回赞数
	 */
	public int getLikeNums(int cid);
	
	/**
	 * 通过评论ID来获得当前评论踩数
	 * @param cid评论ID
	 * @return 返回踩数
	 */
	public int getUnLikeNums(int cid);
}
