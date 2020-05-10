package com.naver.daehwan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naver.daehwan.model.PayCoin;

public interface PayCoinRepository extends JpaRepository<PayCoin, Long> {
	@Query("SELECT a.historyNo, a.userNo, a.itemId, b.itemName, a.amount, a.regDate "
		+ "FROM PayCoin a INNER JOIN Item b ON a.itemId = b.itemId " + "WHERE a.historyNo > 0 AND a.userNo = ?1 "
		+ "ORDER BY a.historyNo DESC")
	public List<Object[]> listPayHistory(Long userNo);

}
