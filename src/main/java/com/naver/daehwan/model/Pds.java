package com.naver.daehwan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pds")
public class Pds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long itemId;
	String itemName;
	String description;
	@OneToMany(cascade = CascadeType.ALL)
	List<PdsFile> pdsFiles = new ArrayList<>();

	@Transient
	String[] files;

	Integer viewCnt = 0;

	@CreationTimestamp
	Date regDate;
	@UpdateTimestamp
	Date updDate;

	public void addItemFile(PdsFile itemFile) {
		pdsFiles.add(itemFile);
	}

	public void clearItemFile() {
		pdsFiles.clear();
	}
}
