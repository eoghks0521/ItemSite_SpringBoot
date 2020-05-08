package com.naver.daehwan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.daehwan.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
