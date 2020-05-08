package com.naver.daehwan.service;

import org.springframework.data.domain.Page;

import com.naver.daehwan.model.Board;
import com.naver.daehwan.vo.PageRequestVO;

public interface BoardService {

	public void register(Board board) throws Exception;

	//public List<Board> list() throws Exception;

	public Board read(Long boardNo) throws Exception;

	public void modify(Board board) throws Exception;

	public void remove(Long boardNo) throws Exception;

	public Page<Board> list(PageRequestVO pageRequestVO) throws Exception;
	

}
