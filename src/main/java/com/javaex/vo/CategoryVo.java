package com.javaex.vo;

public class CategoryVo {
	
	private int cateNo;
	private String id;
	private int num;
	private int post;
	private String cateName;
	private String description;	
	private String regDate;
		
	public CategoryVo() {}
	
	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate) {
		super();
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}	

	public CategoryVo(int cateNo, String id, int num, int post, String cateName, String description, String regDate) {
		super();
		this.cateNo = cateNo;
		this.id = id;
		this.num = num;
		this.post = post;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}

	public int getCateNo() {
		return cateNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", id=" + id + ", num=" + num + ", post=" + post + ", cateName="
				+ cateName + ", description=" + description + ", regDate=" + regDate + "]";
	}

	
	
	
	

}
