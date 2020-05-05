package com.naver.daehwan.service;

import java.util.List;

import com.naver.daehwan.model.CodeDetail;

public interface CodeDetailService {

	public List<CodeDetail> list() throws Exception;

	public void register(CodeDetail codeDetail) throws Exception;

	public CodeDetail read(CodeDetail codeDetail) throws Exception;

	public void modify(CodeDetail codeDetail) throws Exception;

	public void remove(CodeDetail codeDetail) throws Exception;
}
