package com.naver.daehwan.service;

import java.util.List;

import com.naver.daehwan.model.Notice;

public interface NoticeService {

	// 등록 처리
	public void register(Notice notice) throws Exception;

	// 상세 화면
	public Notice read(Long noticeNo) throws Exception;

	// 수정 처리
	public void modify(Notice notice) throws Exception;

	// 삭제 처리
	public void remove(Long noticeNo) throws Exception;

	// 목록 화면
	public List<Notice> list() throws Exception;

}
