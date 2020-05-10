package com.naver.daehwan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.daehwan.model.ChargeCoin;

public interface ChargeCoinRepository extends JpaRepository<ChargeCoin, Long> {

}
