package com.xuyu.message;


import java.util.Date;

/**
 * 老师类，存取老师的信息
 */
public class Teacher {
	private String userId;
	private String name;
	private String mobile;
	private String itemNo;
	private String email;
	private Date setClassFlag;

	public Teacher(String userId, String name, String mobile, String email) {
		this.userId = userId;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}
	public Teacher(String userId,String name,Date setClassFlag){
		this.userId=userId;
		this.name=name;
		this.setClassFlag=setClassFlag;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public void setFlag(Date setClassFlag){
		this.setClassFlag=setClassFlag;
	}
	public String getName() {
		return name;
	}

	public String getUserId() {
		return userId;
	}

	public String getMobile() {
		return mobile;
	}

	public String getItemNo() {
		return itemNo;
	}

	public String getemail() {
		return email;
	}

	public Date getFlag(){
		return setClassFlag;
	}
}
