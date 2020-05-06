package com.naver.daehwan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.daehwan.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
