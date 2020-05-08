package com.naver.daehwan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.daehwan.model.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
