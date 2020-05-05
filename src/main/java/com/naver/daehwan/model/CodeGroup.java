package com.naver.daehwan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of="groupCode")
@Table(name="code_group")
public class CodeGroup {
	
	 @Id
	 @Column(length=3)
	 String groupCode;
	 
	 @Column(length=30, nullable=false)
	 String groupName;
	 
	 @Column(length=1)
	 String useTn="Y";
	 
	 @CreationTimestamp
	 Date regDate;
	 
	 @UpdateTimestamp
	 Date updDate;

}
