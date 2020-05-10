package com.naver.daehwan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.daehwan.model.Item;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.model.PayCoin;
import com.naver.daehwan.model.UserItem;
import com.naver.daehwan.repo.MemberRepository;
import com.naver.daehwan.repo.PayCoinRepository;
import com.naver.daehwan.repo.UserItemRepository;

@Service
public class UserItemServiceImpl implements UserItemService {

	@Autowired
	private UserItemRepository userItemRepository;
	@Autowired
	private PayCoinRepository payCoinRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	@Override
	public void register(Member member, Item item) throws Exception {
		Long userNo = member.getUserNo();
		Long itemId = item.getItemId();
		int price = item.getPrice();
		UserItem userItem = new UserItem();
		userItem.setUserNo(userNo);
		userItem.setItemId(itemId);
		PayCoin payCoin = new PayCoin();
		payCoin.setUserNo(userNo);
		payCoin.setItemId(itemId);
		payCoin.setAmount(price);
		Member memberEntity = memberRepository.getOne(userNo);
		int coin = memberEntity.getCoin();
		int amount = payCoin.getAmount();
		memberEntity.setCoin(coin - amount);
		memberRepository.save(memberEntity);

		payCoinRepository.save(payCoin);

		userItemRepository.save(userItem);
	}

	@Override
	public List<UserItem> list(Long userNo) throws Exception {
		List<Object[]> valueArrays = userItemRepository.listUserItem(userNo);
		List<UserItem> userItemList = new ArrayList<UserItem>();
		for (Object[] valueArray : valueArrays) {
			UserItem userItem = new UserItem();
			userItem.setUserItemNo((Long)valueArray[0]);
			userItem.setUserNo((Long)valueArray[1]);
			userItem.setItemId((Long)valueArray[2]);
			userItem.setRegDate((Date)valueArray[3]);
			userItem.setItemName((String)valueArray[4]);
			userItem.setPrice((int)valueArray[5]);
			userItem.setDescription((String)valueArray[6]);
			userItem.setPictureUrl((String)valueArray[7]);
			userItemList.add(userItem);
		}

		return userItemList;
	}

	@Override
	public UserItem read(Long userItemNo) throws Exception {
		List<Object[]> valueArrays = userItemRepository.readUserItem(userItemNo);
		Object[] valueArray = valueArrays.get(0);
		UserItem userItem = new UserItem();
		userItem.setUserItemNo((Long)valueArray[0]);
		userItem.setUserNo((Long)valueArray[1]);
		userItem.setItemId((Long)valueArray[2]);
		userItem.setRegDate((Date)valueArray[3]);
		userItem.setItemName((String)valueArray[4]);
		userItem.setPrice((int)valueArray[5]);
		userItem.setDescription((String)valueArray[6]);
		userItem.setPictureUrl((String)valueArray[7]);
		return userItem;
	}

}
