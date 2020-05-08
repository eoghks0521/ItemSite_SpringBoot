package com.naver.daehwan.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageRequestVO {
	private int page;
	private int sizePerPage;

	private String searchType;
	private String keyword;

	public PageRequestVO() {
		this.page = 1;
		this.sizePerPage = 10;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public void setSizePerPage(int size) {
		if (size <= 0 || size > 100) {
			this.sizePerPage = 10;
			return;
		}
		this.sizePerPage = size;
	}

	public int getPage() {
		return page;
	}

	public int getPageStart() {
		return (this.page - 1) * sizePerPage;
	}

	public int getSizePerPage() {
		return this.sizePerPage;
	}

	//필드를 활용하여 다양한 형태의 쿼리파라미터 생성
	public String toUriString() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.queryParam("page", this.page)
			.queryParam("size", this.sizePerPage)
			.queryParam("searchType", this.searchType)
			.queryParam("keyword", this.keyword)
			.build();

		return uriComponents.toUriString();
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	//페이지번호를 받아 다야한 형태의 쿼리파라미터를 생성
	public String toUriStringByPage(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("size", this.sizePerPage)
			.build();
		return uriComponents.toString();
	}

	public String toUriString(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("size", this.sizePerPage)
			.queryParam("searchType", this.searchType)
			.queryParam("keyword", this.keyword)
			.build();

		return uriComponents.toUriString();
	}
}
