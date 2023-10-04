package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	public void blogInsert(BlogVo blogVo) {
		System.out.println("서비스 블로그생성");		
		blogDao.blogInsert(blogVo);	
	}
	
	public BlogVo blogSelect(String id) {
		System.out.println("서비스 블로그정보");	
		System.out.println("블로그주소아이디 " + id);
		return blogDao.blogSelect(id);	
	}

	
	
}
