package com.gzb.service;

public class PreAmountCheckBean {
	
	private String userid;
	private String userName;
	private String fristData;
	private String keyword1;
	private String keyword2;
	private String remark;
	private String customID;
	
	public String getCustomID() {
		return customID;
	}
	public void setCustomID(String customID) {
		this.customID = customID;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFristData() {
		return fristData;
	}
	public void setFristData(String fristData) {
		this.fristData = fristData;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "to_Send [userid=" + userid + ", userName="
				+ userName + ", fristData=" + fristData + ", keyword1="
				+ keyword1 + ", keyword2=" + keyword2 + ", remark=" + remark
				+ "]";
	}
	
	
	
}
