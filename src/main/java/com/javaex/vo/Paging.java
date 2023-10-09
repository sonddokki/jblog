package com.javaex.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paging {
		
	
	
	
	public Map<String, Object> pagingList(int crtPage, String search) {
		System.out.println("pagingList 클래스");

		int listCnt = 10; // 페이지당 글갯수

		// 현재페이지 crtPage 파라미터 받는다
		// 없는 페이지면 1로 보낸다
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		int startRNum = (crtPage - 1) * listCnt + 1; // 시작글번호

		int endRNum = (startRNum + listCnt) - 1; // 끝글번호

		List<BoardVo> pList = boardDao.boardSelectList4(startRNum, endRNum, search);

		///////////////////////////////////////////
		// 페이징 계산
		int pageBtnCount = 5; // 페이지당 버튼 갯수

		int totalCnt = boardDao.selectTotalCnt(search); // 전체 글 갯수

		// 마지막버튼번호
		int endPageBtnNo = (int) Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount;

		// 시작버튼번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount) + 1;

		// 다음화살표 유무
		boolean next = false;
		if (listCnt * endPageBtnNo < totalCnt) {
			next = true;
		} else { // 다음버튼이 없을때
			endPageBtnNo = (int) Math.ceil(totalCnt / (double) listCnt);
		}

		// 이전화살표 유무
		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		}
		
		System.out.println("==========================================");
		System.out.println("시작페이지 " + startPageBtnNo);
		System.out.println("끝페이지 " + endPageBtnNo);
		System.out.println("이전버튼 " + prev);
		System.out.println("다음버튼 " + next);

		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("search", search);
		pMap.put("pList", pList);

		return pMap;
	}
	

}
