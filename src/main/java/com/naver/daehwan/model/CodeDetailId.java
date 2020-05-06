package com.naver.daehwan.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CodeDetailId implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	String groupCode;
	String codeValue;
	

}
