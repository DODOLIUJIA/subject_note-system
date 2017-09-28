package com.zr.dao;

import java.util.List;

import com.zr.model.Comment;

/**
 * @author JACK
 */
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
	public boolean insertComment(int sid, int uid, String comment);

	/**
	 * 通过评论ID来给评论点赞
	 * 
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
	 * 
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
	
	/**
	 * 通过评论ID来删除某条评论
	 * @param cid评论ID
	 * @return
	 */
	public boolean deleteCommentByCID(int cid);
	
	/**
	 * 获得所有的评论
	 * @return返回一个装有所有评论的的List
	 */
	public List<Comment> getAllComments();
	
	/**
	 * 获得所有评论的数量
	 * @return
	 */
	public int getCommentsSum();
	
	/**
	 * 通过limit来获得指定位置的评论
	 * @param start开始位置
	 * @param size一次取出评论的数量
	 * @return
	 */
	public List<Comment> getCommentsByLimit(int start,int size);
	
	/**
	 * 通过传入的评论ID数组来删除数组中的评论
	 * @param cids传入的评论ID数组
	 * @return 返回删除的结果，全部删除成功返回true，有一个删除失败则返回false
	 */
	public boolean deleteCommentByCIDArray(int[] cids);
	
}
