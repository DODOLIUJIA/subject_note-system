package com.zr.model;

public class Subject {
	private int subid;
	private String subsummary;
	private String subtext;
	private String s_label;
	public String getS_label() {
		return s_label;
	}
	public void setS_label(String s_label) {
		this.s_label = s_label;
	}
	//1.简答题，2.填空题，3.单选题，4.多选题
	private int subtype;
	private String subaccuracy;
	private String subanswer;
	private int subtime;
	public int getSubid() {
		return subid;
	}
	public void setSubid(int subid) {
		this.subid = subid;
	}
	public String getSubsummary() {
		return subsummary;
	}
	public void setSubsummary(String subsummary) {
		this.subsummary = subsummary;
	}
	public String getSubtext() {
		return subtext;
	}
	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}
	public int getSubtype() {
		return subtype;
	}
	public void setSubtype(int subtype) {
		this.subtype = subtype;
	}
	public String getSubaccuracy() {
		return subaccuracy;
	}
	public void setSubaccuracy(String subaccuracy) {
		this.subaccuracy = subaccuracy;
	}
	public String getSubanswer() {
		return subanswer;
	}
	public void setSubanswer(String subanswer) {
		this.subanswer = subanswer;
	}
	public int getSubtime() {
		return subtime;
	}
	public void setSubtime(int subtime) {
		this.subtime = subtime;
	}
	
}
