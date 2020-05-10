package com.naver.daehwan.service;

import java.util.List;

import com.naver.daehwan.model.Item;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.model.UserItem;

public interface UserItemService {

	public void register(Member member, Item item) throws Exception;

	public List<UserItem> list(Long userNo) throws Exception;

	public UserItem read(Long userItemNo) throws Exception;

}
