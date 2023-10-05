package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BlogService blogService;		
	
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("joinForm");		
		return "user/joinForm";
	}
	
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo, BlogVo blogVo) {
		System.out.println("join");			
		userService.userInsert(userVo);	
		
		// 아이디 생성과 동시에 블로그도 생성
		String id = userVo.getId();
		String name = userVo.getUserName();
		blogVo.setId(id);
		blogVo.setBlogTitle(name+"의 블로그입니다.");
		blogVo.setLogoFile("spring-logo.jpg");	
		blogService.blogInsert(blogVo);
		
		return "user/joinSuccess";
	}
	
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("loginForm");		
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("login");
		UserVo authUser = userService.userSelect(userVo);				
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			System.out.println(authUser);
			return "redirect:/";
		} else {
			return "redirect:/user/loginForm?result=fail";
		}			
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("logout");	
		session.invalidate();		
		return "redirect:/";
	}

}
