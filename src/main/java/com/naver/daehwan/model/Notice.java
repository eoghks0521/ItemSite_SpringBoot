package com.naver.daehwan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "noticeNo")
@ToString
@Entity
@Table(name = "notice")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long noticeNo;

	@Column(length = 200, nullable = false)
	String title;

	@Lob
	String content;

	@CreationTimestamp
	Date regDate;

	@UpdateTimestamp
	Date updDate;

}
