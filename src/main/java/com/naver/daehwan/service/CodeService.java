package com.naver.daehwan.service;

import java.util.List;

import com.naver.daehwan.dto.CodeLabelValue;

public interface CodeService {
	public List<CodeLabelValue> getCodeGroupList() throws Exception;
	
	public List<CodeLabelValue> getCodeList(String classCode) throws Exception;
	
}
