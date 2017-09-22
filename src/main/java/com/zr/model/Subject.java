package com.zr.model;

public class Subject {

	private String s_label;
  private int subId;//题目ID
	private String subSummary;//题目描述
	private String subText;//题目内容
	private int subType;//题目类型
	private String subAccuracy;//题目准确率
	private String subAnswer;//题目答案
	private int subTime;//题目创建的年份
  
  public String getS_label() {
		return s_label;
	}
  
	public void setS_label(String s_label) {
		this.s_label = s_label;
	}
  
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getSubSummary() {
		return subSummary;
	}
	public void setSubSummary(String subSummary) {
		this.subSummary = subSummary;
	}
	public String getSubText() {
		return subText;
	}
	public void setSubText(String subText) {
		this.subText = subText;
	}
	public int getSubType() {
		return subType;
	}
	public void setSubType(int subType) {
		this.subType = subType;
	}
	public String getSubAccuracy() {
		return subAccuracy;
	}
	public void setSubAccuracy(String subAccuracy) {
		this.subAccuracy = subAccuracy;
	}
	public String getSubAnswer() {
		return subAnswer;
	}
	public void setSubAnswer(String subAnswer) {
		this.subAnswer = subAnswer;
	}
	public int getSubTime() {
		return subTime;
	}
	public void setSubTime(int subTime) {
		this.subTime = subTime;
	}
	/**
	 * 题目的无参构造方法
	 */
	public Subject() {
		super();
	}
	
	/**
	 * 题目的有参构造方法
	 * @param subId
	 * @param subSummary
	 * @param subText
	 * @param subType
	 * @param subAccuracy
	 * @param subAnswer
	 * @param subTime
	 */
	public Subject(int subId, String subSummary, String subText, int subType, String subAccuracy, String subAnswer,
			int subTime) {
		this.subId = subId;
		this.subSummary = subSummary;
		this.subText = subText;
		this.subType = subType;
		this.subAccuracy = subAccuracy;
		this.subAnswer = subAnswer;
		this.subTime = subTime;
	}
}
