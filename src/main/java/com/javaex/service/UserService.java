package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	// 유저생성 + 블로그생성 + 카테고리생성
	public void userInsert(UserVo userVo) {
		System.out.println("서비스 유저등록");		
		
		// 유저생성
		userDao.userInsert(userVo);	
		
		String id = userVo.getId();
		String name = userVo.getUserName();
		
		// 아이디 생성과 동시에 블로그도 생성		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setBlogTitle(name+"의 블로그입니다.");
		blogVo.setLogoFile("spring-logo.jpg");	
		blogDao.blogInsert(blogVo);		
		
		// 기본카테고리 생성		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(id);
		categoryVo.setCateName("기본카테고리");
		categoryVo.setDescription(" ");
		blogDao.cateInsert(categoryVo);		
	}
	
	public UserVo userSelect(UserVo userVo) {
		System.out.println("서비스 유저로그인");		
		return userDao.userSelect(userVo);
		
	}
	

}
