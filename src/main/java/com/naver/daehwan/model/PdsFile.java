package com.naver.daehwan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pds_file")
public class PdsFile {

	@Id
	@GeneratedValue
	Long pdsFileId;

	@Column(length = 200)
	String fullName;

	Integer downCnt = 0;

	@CreationTimestamp
	Date regDate;
	@UpdateTimestamp
	Date updDate;

}
