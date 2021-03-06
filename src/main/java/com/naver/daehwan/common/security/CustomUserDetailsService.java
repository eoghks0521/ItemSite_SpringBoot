package com.naver.daehwan.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.naver.daehwan.common.security.domain.CustomUser;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.repo.MemberRepository;

import lombok.extern.java.Log;

@Log
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("userName :" + username);
		
		Member member = repository.findByUserId(username).get(0);
		
		log.info("member: "+member);
		
		return member == null ? null : new CustomUser(member);
	}

}
