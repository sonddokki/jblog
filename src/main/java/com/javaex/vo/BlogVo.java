package com.javaex.vo;

public class BlogVo {

	private String id;
	private String userName;
	private String blogTitle;
	private String logoFile;

	public BlogVo() {
	}

	public BlogVo(String id, String userName, String blogTitle, String logoFile) {
		super();
		this.id = id;
		this.userName = userName;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}
		

	public BlogVo(String id, String userName, String blogTitle) {
		super();
		this.id = id;
		this.userName = userName;
		this.blogTitle = blogTitle;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", userName=" + userName + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile
				+ "]";
	}

	

}
