package com.naver.daehwan.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.naver.daehwan.model.CustomUser;
import com.naver.daehwan.model.Member;

import lombok.extern.java.Log;

@Log
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	//로그인 성공 처리자 메서드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();

		log.info("Userid = " + member.getUserId());

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
