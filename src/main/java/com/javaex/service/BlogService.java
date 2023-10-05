package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

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

	public void blogUpdate(MultipartFile logoFile, BlogVo blogVo) {
		System.out.println("서비스 블로그 정보 업데이트 ");
		System.out.println(blogVo);		
		System.out.println(logoFile.isEmpty());

		// 로고파일이 없으면 기존정보에 있던 로고파일을 가져와서 set 해주기
		if (logoFile.isEmpty() == true) {
			BlogVo blogUser = blogDao.blogSelect(blogVo.getId());
			blogVo.setLogoFile(blogUser.getLogoFile());
			blogDao.blogUpdate(blogVo);
		} else {
		//로고파일이 있으면 그대로 진행
			// 0.파일 경로
			String saveDir = "C:\\\\javaStudy\\\\upload";

			// 1.파일관련 자료 추출/////////////////////////////////////////////////////////
			// (1) 오리지날네임 추출
			String orgName = logoFile.getOriginalFilename();
			System.out.println(orgName);

			// (2) 확장자 추출
			String exName = orgName.substring(orgName.lastIndexOf("."));
			// 몇번째에 "." 있는지 확인하는 메소드 + 그걸 잘라내고 추출하는 메소드
			System.out.println(exName);

			// (3) 저장파일명 (겹치지 않아야 한다)
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			// long + 문자열 + 확장자
			System.out.println(saveName);

			// (4) 파일 사이즈
			long fileSize = logoFile.getSize();
			System.out.println(fileSize);

			// (5) 파일 경로
			String filePath = saveDir + "\\" + saveName;
			System.out.println(filePath);

			// (6) Vo로 묶기
			blogVo.setLogoFile(saveName);

			// (7) Dao 만들어서 저장하기
			System.out.println("db에 저장 " + blogVo);
			blogDao.blogUpdate(blogVo);

			// 2.파일저장 (서버쪽 하드디스크에 저장) ////////////////////////////////////////////
			try {
				byte[] fileData;
				fileData = logoFile.getBytes();

				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);

				bos.write(fileData);
				bos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}	

	}
	
	public List<CategoryVo> categoryList(String id) {
		System.out.println("서비스 리스트");
		return blogDao.categoryList(id);
	}
	
	public void cateInsert(CategoryVo categoryVo) {
		System.out.println("서비스 카테고리 등록");
		blogDao.cateInsert(categoryVo);
	}
	
	public void cateDelete(CategoryVo categoryVo) {
		System.out.println("서비스 카테고리 삭제");
		blogDao.cateDelete(categoryVo);
	}

}
