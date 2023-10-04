package com.javaex.vo;

public class UserVo {
	
	private int userNo;
	private String id;
	private String userName;
	private String password;
	private String date;
	
	public UserVo() {}
	
	public UserVo(int userNo, String id, String userName, String password, String date) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.date = date;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "users [userNo=" + userNo + ", id=" + id + ", userName=" + userName + ", password=" + password
				+ ", date=" + date + "]";
	}
	
	
	
	
	

}
