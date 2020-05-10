package com.naver.daehwan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.daehwan.model.PdsFile;

public interface PdsFileRepository extends JpaRepository<PdsFile, Long> {
	// 첨부파일 다운로드 처리
	public List<PdsFile> findByFullName(String fullName);
}
