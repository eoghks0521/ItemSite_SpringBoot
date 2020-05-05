package com.naver.daehwan.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class CodeDetailId implements Serializable{
	
	private static final long serialVersionUID = 8895333963560612007L;
	
	String groupCode;
	String codeValue;
	

}
