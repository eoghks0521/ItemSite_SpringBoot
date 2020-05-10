package com.naver.daehwan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.naver.daehwan.model.ChargeCoin;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.model.PayCoin;
import com.naver.daehwan.repo.ChargeCoinRepository;
import com.naver.daehwan.repo.MemberRepository;
import com.naver.daehwan.repo.PayCoinRepository;

@Service

public class CoinServiceImpl implements CoinService {
	@Autowired
	ChargeCoinRepository chargeCoinRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	PayCoinRepository payCoinRepository;

	@Transactional
	@Override
	public void charge(ChargeCoin chargeCoin) throws Exception {
		Member memberEntity = memberRepository.getOne(chargeCoin.getUserNo());

		int coin = memberEntity.getCoin();
		int amount = chargeCoin.getAmount();

		memberEntity.setCoin(coin + amount);

		memberRepository.save(memberEntity);

		chargeCoinRepository.save(chargeCoin);

	}

	@Override
	public List<ChargeCoin> list(Long userNo) throws Exception {
		return chargeCoinRepository.findAll(Sort.by(Direction.DESC, "historyNo"));
	}

	@Override
	public List<PayCoin> listPayHistory(Long userNo) {
		List<Object[]> valueArrays = payCoinRepository.listPayHistory(userNo);
		List<PayCoin> payCoinList = new ArrayList<PayCoin>();
		for (Object[] valueArray : valueArrays) {
			PayCoin payCoin = new PayCoin();
			payCoin.setHistoryNo((Long)valueArray[0]);
			payCoin.setUserNo((Long)valueArray[1]);
			payCoin.setItemId((Long)valueArray[2]);
			payCoin.setItemName((String)valueArray[3]);
			payCoin.setAmount((int)valueArray[4]);
			payCoin.setRegDate((Date)valueArray[5]);
			payCoinList.add(payCoin);
		}
		return payCoinList;
	}

}
