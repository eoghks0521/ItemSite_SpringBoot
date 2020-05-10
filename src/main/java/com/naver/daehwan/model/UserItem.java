package com.naver.daehwan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "userItemNo")
@ToString
@Entity
@Table(name = "user_item")
public class UserItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userItemNo;

	Long userNo;
	Long itemId;

	@Transient
	String itemName;
	@Transient
	Integer price;
	@Transient
	String description;
	@Transient
	String pictureUrl;

	@CreationTimestamp
	Date regDate;
	@UpdateTimestamp
	Date updDate;
}
