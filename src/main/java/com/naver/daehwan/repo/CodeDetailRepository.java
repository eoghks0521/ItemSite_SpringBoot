package com.naver.daehwan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naver.daehwan.model.CodeDetail;
import com.naver.daehwan.model.CodeDetailId;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId> {

	@Query("SELECT max(cd.sortSeq) FROM CodeDetail cd WHERE cd.groupCode = ?1")
	public List<Object[]> getMaxSortSeq(String groupCode);
	
	public List<CodeDetail> findByGroupCode(String groupCode);
}
