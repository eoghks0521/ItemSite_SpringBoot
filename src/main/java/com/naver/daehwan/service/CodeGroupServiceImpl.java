package com.naver.daehwan.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.naver.daehwan.repo.CodeGroupRepository;

public class CodeGroupServiceImpl implements CodeGroupService{

	@Autowired
	CodeGroupRepository repository;
}
