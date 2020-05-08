package com.naver.daehwan.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.naver.daehwan.model.Board;

public interface CustomBoardRepository {

	public Page<Board> getSearchPage(String type, String keyword, Pageable pageable);
}
