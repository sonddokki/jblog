package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;



@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void blogInsert(BlogVo blogVo) {
		System.out.println("다오 블로그생성");	
		System.out.println(blogVo);
		sqlSession.insert("blogInsert", blogVo);
	}
	
	public BlogVo blogSelect(String id) {
		System.out.println("다오 블로그정보");	
		return sqlSession.selectOne("blogSelect", id);		
	}

}
