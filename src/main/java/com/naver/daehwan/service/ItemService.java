package com.naver.daehwan.service;

import java.util.List;

import com.naver.daehwan.model.Item;

public interface ItemService {

	public void register(Item item) throws Exception;

	public Item read(Long itemId) throws Exception;

	//수정 처리
	public void modify(Item item) throws Exception;

	//삭제 처리
	public void remove(Long itemId) throws Exception;

	//목록 화면
	public List<Item> list() throws Exception;

	//미리보기 이미지 표시
	public String getPreview(Long itemId) throws Exception;

}
