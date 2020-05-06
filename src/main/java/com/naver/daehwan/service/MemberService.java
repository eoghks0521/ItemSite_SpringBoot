package com.naver.daehwan.service;

import java.util.List;

import com.naver.daehwan.model.Member;

public interface MemberService {

	public void register(Member member) throws Exception;

	public Member read(Long userNo) throws Exception;

	public void modify(Member member) throws Exception;

	// 삭제 처리
	public void remove(Long userNo) throws Exception;

	// 목록 화면
	public List<Member> list() throws Exception;
	
	public long countAll() throws Exception;

	public void setupAdmin(Member member) throws Exception;
}
