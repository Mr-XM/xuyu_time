package com.xuyu.message;


/**
 * 老师类，存取老师的信息
 */
public class Teacher {
	private String userId;
	private String name;
	private String mobile;
	private String Item_no;
	private String email;
	private  int setClassFlag;

	public Teacher(String U, String n, String m, String e) {
		userId = U;
		name = n;
		mobile = m;
		email = e;
	}
	public Teacher(String U,String n,int flag){
		userId=U;
		name=n;
		setClassFlag=flag;
	}
	/**
	 * 判断第二天是否有课用
	 *
	 * @param U
	 * @param It 对应item_no
	 * @param n
	 */
	public Teacher(String U, String It, String n) {
		userId = U;
		Item_no = It;
		name = n;
	}

	public void setUserId(String U_id) {
		userId = U_id;
	}

	public void setItem_no(String It) {
		Item_no = It;
	}

	public void setName(String n) {
		name = n;
	}

	public void setMobile(String m) {
		mobile = m;
	}

	public void setemail(String e) {
		email = e;
	}

	public void setFlag(int flag){
		setClassFlag=flag;
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
		return Item_no;
	}

	public String getemail() {
		return email;
	}

	public int getFlag(){
		return setClassFlag;
	}
}
