package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;



@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void blogInsert(BlogVo blogVo) {
		System.out.println("다오 블로그생성");	
		sqlSession.insert("blogInsert", blogVo);
	}
	
	public BlogVo blogSelect(String id) {
		System.out.println("다오 블로그정보");	
		return sqlSession.selectOne("blogSelect", id);		
	}
	
	public void blogUpdate(BlogVo blogVo) {
		System.out.println("다오 블로그업데이트");			
		System.out.println(blogVo);
		sqlSession.update("blogUpdate", blogVo);		
	}
	
	public List<CategoryVo> categoryList(String id) {
		System.out.println("다오 리스트");
		List<CategoryVo> categoryList = sqlSession.selectList("categoryList", id);
		System.out.println(categoryList);
		return categoryList;
	}
	
	public void cateInsert(CategoryVo categoryVo) {
		System.out.println("다오 카테고리 등록");
		sqlSession.insert( "cateInsert", categoryVo);
	}
	
	public void cateDelete(CategoryVo categoryVo) {
		System.out.println("다오 카테고리 삭제");
		sqlSession.delete( "cateDelete", categoryVo);
	}

}
