package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void userInsert(UserVo userVo) {
		System.out.println("다오 유저등록");		
		System.out.println(userVo);
		sqlSession.insert("insert", userVo);		
	}
	
	public UserVo userSelect(UserVo userVo) {
		System.out.println("다오 유저로그인");
		UserVo authUser = sqlSession.selectOne("select", userVo);
		System.out.println(authUser);
		return authUser;
	}

}
