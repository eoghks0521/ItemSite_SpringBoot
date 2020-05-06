package com.naver.daehwan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(of="userNo")
@ToString
@Entity
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_no")
	Long userNo;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	String userId;
	
	@NotBlank
	@Column(length = 200, nullable = false)
	String userPw;
	
	@NotBlank
	@Column(length = 100, nullable = false)
	String userName;
	
	@Column(length = 3, nullable = false)
	String job;
	
	int coin;
	
	@CreationTimestamp
	Date regDate;
	
	@UpdateTimestamp
	Date updDate;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="user_no")
	List<MemberAuth> authList = new ArrayList<>();
	
	public void addAuth(MemberAuth auth) {
		authList.add(auth);
	}
	public void clearAuthList() {
		authList.clear();
	}
}

