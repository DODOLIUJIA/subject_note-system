package com.zr.model;

public class Comment {
	
	private int comID;//评论ID
	private int userID;//评论的用户ID
	private int subID;//题目ID
	private String comText;//评论内容
	private int likeNums;//点赞次数
	private int unLikeNums;//踩次数
	private String create_DateTime;//评论创建的时间
	private String userName;//评论的用户的用户名
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getComID() {
		return comID;
	}
	public void setComID(int comID) {
		this.comID = comID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getSubID() {
		return subID;
	}
	public void setSubID(int subID) {
		this.subID = subID;
	}
	public String getComText() {
		return comText;
	}
	public void setComText(String comText) {
		this.comText = comText;
	}
	public int getLikeNums() {
		return likeNums;
	}
	public void setLikeNums(int likeNums) {
		this.likeNums = likeNums;
	}
	public int getUnLikeNums() {
		return unLikeNums;
	}
	public void setUnLikeNums(int unLikeNums) {
		this.unLikeNums = unLikeNums;
	}
	public String getCreate_DateTime() {
		return create_DateTime;
	}
	public void setCreate_DateTime(String create_DateTime) {
		this.create_DateTime = create_DateTime;
	}
	public Comment(int comID, int userID, int subID, String comText, int likeNums, int unLikeNums,
			String create_DateTime) {
		this.comID = comID;
		this.userID = userID;
		this.subID = subID;
		this.comText = comText;
		this.likeNums = likeNums;
		this.unLikeNums = unLikeNums;
		this.create_DateTime = create_DateTime;
	}
	public Comment() {
		super();
	}
	
	
	
}
