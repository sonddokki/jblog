package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.CommentsVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService;

	// 블로그 메인 /////////////////////////////////////////////////
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable("id") String id,
			@RequestParam(value = "cate", required = false, defaultValue = "0") int cate,
			@RequestParam(value = "post", required = false, defaultValue = "0") int post, Model model, Model cateL,
			Model postL, Model postOne) {

		System.out.println("블로그 메인");

		System.out.println("메인 기본 카테고리 " + cate);
		System.out.println("메인 기본 포스트 " + post);

		// id로 블로그 이름,사진 가져오기
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);

		// id로 카테고리 가져오기
		List<CategoryVo> cateList = blogService.categoryList(id);
		cateL.addAttribute("cateList", cateList);

		// id로 포스트 가져오기
		List<PostVo> postList = blogService.postListSelect(id, cate); // cate 넣기
		System.out.println(postList);
		postL.addAttribute("postList", postList);

		// id로 포스트 최근글가져오기
		PostVo postVo = blogService.postSelectOne(id, post); // post 넣기
		postOne.addAttribute("postOne", postVo);

		return "blog/blog-main";
	}

	// 블로그 기본설정 /////////////////////////////////////////////////
	@RequestMapping("/{id}/admin/basic")
	public String basic(@PathVariable("id") String id, Model model) {
		System.out.println("블로그 기본설정");
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}

	// 블로그 대문명, 사진 변경하기
	@RequestMapping(value = "/{id}/admin/basic/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@RequestParam(value = "file", required = false) MultipartFile logoFile,
			@RequestParam(value = "blogTitle") String blogTitle, HttpSession session) {
		System.out.println("블로그 업데이트");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = new BlogVo(authUser.getId(), authUser.getUserName(), blogTitle);
		blogService.blogUpdate(logoFile, blogVo);
		return "redirect:/{id}/admin/basic";
	}

	// 블로그 카테고리설정 /////////////////////////////////////////////////
	@RequestMapping("/{id}/admin/cate")
	public String cate(@PathVariable("id") String id, Model model) {
		System.out.println("blog cate");
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-cate";
	}

	// 블로그 카테고리리스트 ajex
	@ResponseBody
	@RequestMapping("/{id}/admin/list")
	public List<CategoryVo> list(@PathVariable("id") String id) {
		System.out.println("blog list");
		System.out.println(id);
		List<CategoryVo> categoryList = blogService.categoryList(id);
		System.out.println(categoryList);
		return categoryList;
	}

	// 블로그 카테고리 등록
	@ResponseBody
	@RequestMapping("/{id}/admin/cateInsert")
	public CategoryVo cateInsert(@RequestBody CategoryVo categoryVo, HttpSession session) {
		System.out.println("blog cateInsert");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		categoryVo.setId(authUser.getId());
		System.out.println(categoryVo);
		blogService.cateInsert(categoryVo);
		return categoryVo;
	}

	// 블로그 카테고리 삭제
	@ResponseBody
	@RequestMapping("/{id}/admin/delete")
	public CategoryVo cateDelete(@RequestBody int cateNo, HttpSession session) {
		System.out.println("blog cateDelete");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		System.out.println(cateNo);

		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(authUser.getId());
		categoryVo.setCateNo(cateNo);
		System.out.println(categoryVo);
		blogService.cateDelete(categoryVo);

		return categoryVo;
	}

	// 블로그 글쓰기 /////////////////////////////////////////////////
	@RequestMapping("/{id}/admin/writeFrom")
	public String writeFrom(@PathVariable("id") String id, Model model, Model model2) {
		System.out.println("blog Write");
		BlogVo blogVo = blogService.blogSelect(id);
		model.addAttribute("blogVo", blogVo);
		List<CategoryVo> cateList = blogService.categoryList(id);
		model2.addAttribute("cateList", cateList);

		return "blog/admin/blog-admin-write";
	}

	@RequestMapping("/{id}/admin/write")
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println("blog Write");

		System.out.println(postVo);
		blogService.postInsert(postVo);

		return "redirect:/{id}/admin/writeFrom";
	}

	// 블로그 코멘트 등록
	@ResponseBody
	@RequestMapping("/{id}/admin/CommentInsert")
	public CommentsVo CommentInsert(@RequestBody CommentsVo commentsVo) {
		System.out.println("CommentInsert");		
		System.out.println(commentsVo);
		
		blogService.cmtInsert(commentsVo);
		
		return commentsVo;
	}

}
