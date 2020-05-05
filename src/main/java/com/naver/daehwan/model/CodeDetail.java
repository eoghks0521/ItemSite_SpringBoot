package com.naver.daehwan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of= {"groupCode","codeValue"})
@Entity
//@IdClass(codeDetailId.class)
@Table(name="code_detail")
public class CodeDetail {
	
	@Id
	@Column(length=3)
	String groupCode;

}
