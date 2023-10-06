package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.CommentsVo;
import com.javaex.vo.PostVo;

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
		System.out.println("다오 블로그리스트");
		List<CategoryVo> categoryList = sqlSession.selectList("categoryList", id);
		return categoryList;
	}

	// 카테고리 /////////////////////////////////////////////
	public void cateInsert(CategoryVo categoryVo) {
		System.out.println("다오 카테고리 등록");
		sqlSession.insert("cateInsert", categoryVo);
	}

	public void cateDelete(CategoryVo categoryVo) {
		System.out.println("다오 카테고리 삭제");
		sqlSession.delete("cateDelete", categoryVo);
	}

	// 포스트 /////////////////////////////////////////////
	public void postInsert(PostVo postVo) {
		System.out.println("다오 포스트 등록");
		sqlSession.insert("postInsert", postVo);
	}

	public List<PostVo> postListSelect(String id, int cate) {
		System.out.println("다오 포스트 리스트");
		
	    Map<String, Object> postListMap = new HashMap<String, Object>();
	    postListMap.put("id", id);
	    postListMap.put("cate", cate);
		
	    System.out.println(postListMap);
		return sqlSession.selectList("postListSelect", postListMap);
	}
	
	public PostVo postSelectOne(String id, int post) {
		System.out.println("다오 포스트 최근글");
		
		  Map<String, Object> PostVoMap = new HashMap<String, Object>();
		  PostVoMap.put("id", id);
		  PostVoMap.put("post", post);
			
		    System.out.println(PostVoMap);
		return sqlSession.selectOne("postSelectOne", PostVoMap);
	}
	
	// 코멘트 /////////////////////////////////////////////
		public void cmtInsert(CommentsVo commentsVo) {
			System.out.println("다오 코멘트 등록");
			sqlSession.insert("cmtInsert", commentsVo);
		}
	
		public List<CommentsVo> commentlist(int postNo) {
			System.out.println("다오 코멘트 리스트");
			return sqlSession.selectList("commentlist", postNo);
		}
		
}
