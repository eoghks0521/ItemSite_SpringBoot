package com.naver.daehwan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long itemId;

	@Column(length = 50, nullable = false)
	String itemName;

	Integer price;

	@Column(length = 250)
	String description;

	@Transient
	MultipartFile picture;

	@Column(length = 200)
	String pictureUrl;

	@Transient
	MultipartFile preview;

	@Column(length = 200)
	String previewUrl;

	@CreationTimestamp
	Date regDate;
	@UpdateTimestamp
	Date updDate;

}
