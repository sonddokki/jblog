package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService;

	@RequestMapping("/{id}")
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("blogMain");
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-main";
	}

	@RequestMapping("/{id}/admin/basic")
	public String basic(@PathVariable("id") String id, Model model) {
		System.out.println("블로그 기본설정");
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping("/{id}/admin/basic/update")
	public String update(@ModelAttribute BlogVo blogVo) {
		System.out.println("블로그 업데이트");
		System.out.println(blogVo);
		//blogService.blogUpdate(blogVo);
		
		return "redirect:/{id}/admin/basic";
	}
	
	
	

	@RequestMapping("/{id}/admin/cate")
	public String cate(@PathVariable("id") String id, Model model) {
		System.out.println("blogBasic");
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-cate";
	}

	@RequestMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id, Model model) {
		System.out.println("blogWrite");
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-write";
	}

}
