package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	BlogService blogService;		
	
	@RequestMapping ("/{id}")
	public String blogMain(@PathVariable ("id") String id ,Model model) {
		System.out.println("blogMain");	
		
		System.out.println(id);
		
		BlogVo blogVo = blogService.blogSelect(id);
		System.out.println(blogVo);
		
		model.addAttribute( "blogVo" , blogVo);

		return "blog/blog-main";
	}
	
	

}
