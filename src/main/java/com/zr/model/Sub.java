package com.zr.model;

public class Sub {
private int subid;
private String subsummary;//题目描述
private String subtext;
private int subtype;//题目类型 1为简答题 2为填空题 3为单选题 4为多选题
private String subaccuracy;//题目准确率
private String subanswer;
private int subtime;//出题时间
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
