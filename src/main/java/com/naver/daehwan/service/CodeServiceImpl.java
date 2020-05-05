package com.naver.daehwan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.naver.daehwan.dto.CodeLabelValue;
import com.naver.daehwan.model.CodeGroup;
import com.naver.daehwan.repo.CodeGroupRepository;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	CodeGroupRepository repository;

	@Override
	public List<CodeLabelValue> getCodeGroupList() throws Exception {
		List<CodeGroup> codeGroups = repository.findAll(Sort.by(Direction.ASC, "groupCode"));

		List<CodeLabelValue> codeGroupList = new ArrayList<>();

		for (CodeGroup codeGroup : codeGroups) {
			codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(), codeGroup.getGroupName()));
		}

		return codeGroupList;
	}

}
