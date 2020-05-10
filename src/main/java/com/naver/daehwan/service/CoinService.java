package com.naver.daehwan.service;

import java.util.List;

import com.naver.daehwan.model.ChargeCoin;
import com.naver.daehwan.model.PayCoin;

public interface CoinService {

	public void charge(ChargeCoin chargeCoin) throws Exception;

	public List<ChargeCoin> list(Long userNo) throws Exception;

	public List<PayCoin> listPayHistory(Long userNo);

}
