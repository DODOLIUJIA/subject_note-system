package com.zr.service.impl;

import java.util.List;

import com.zr.dao.CommentDao;
import com.zr.dao.impl.CommentDaoImpl;
import com.zr.model.Comment;
import com.zr.service.CommentService;

public class CommentServiceImpl implements CommentService {

	CommentDao cdao = new CommentDaoImpl();

	@Override
	public List<Comment> getCommentBySubID(int sid) {
		return cdao.getCommentBySubID(sid);
	}

	@Override
	public boolean insertComment(int sid, int uid, String comment) {
		return cdao.insertComment(sid, uid, comment);
	}

	@Override
	public boolean addLikeNums(int cid) {
		return cdao.addLikeNums(cid);
	}

	@Override
	public boolean addUnLikeNums(int cid) {
		return cdao.addUnLikeNums(cid);
	}

	@Override
	public int getLikeNums(int cid) {
		return cdao.getLikeNums(cid);
	}

	@Override
	public int getUnLikeNums(int cid) {
		return cdao.getUnLikeNums(cid);
	}

	@Override
	public boolean deleteCommentByCID(int cid) {
		return cdao.deleteCommentByCID(cid);
	}

	@Override
	public List<Comment> getAllComments() {
		return cdao.getAllComments();
	}

	@Override
	public int getCommentsSum() {
		return cdao.getCommentsSum();
	}

	@Override
	public List<Comment> getCommentsByLimit(int start, int size) {
		return cdao.getCommentsByLimit(start, size);
	}

	@Override
	public boolean deleteCommentByCIDArray(int[] cids) {
		return cdao.deleteCommentByCIDArray(cids);
	}

}
