package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void userInsert(UserVo userVo) {
		System.out.println("서비스 유저등록");		
		userDao.userInsert(userVo);		
	}
	
	public UserVo userSelect(UserVo userVo) {
		System.out.println("서비스 유저로그인");		
		return userDao.userSelect(userVo);
		
	}
	

}
