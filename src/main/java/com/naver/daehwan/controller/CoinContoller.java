package com.naver.daehwan.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.model.ChargeCoin;
import com.naver.daehwan.model.CustomUser;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.service.CoinService;

@Controller
@RequestMapping("/coin")
public class CoinContoller {

	@Autowired
	CoinService service;

	@Autowired
	MessageSource messgaeSource;

	@GetMapping("/charge")
	public void chargeForm(Model model) throws Exception {
		ChargeCoin chargeCoin = new ChargeCoin();
		chargeCoin.setAmount(1000);

		model.addAttribute(chargeCoin);

	}

	@PostMapping("/charge")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String charge(int amount, RedirectAttributes rttr, Authentication authentication) throws Exception {
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();

		Long userNo = member.getUserNo();

		ChargeCoin chargeCoin = new ChargeCoin();

		chargeCoin.setUserNo(userNo);
		chargeCoin.setAmount(amount);

		service.charge(chargeCoin);

		String message = messgaeSource.getMessage("coin.chargingComplete", null, Locale.KOREA);
		rttr.addFlashAttribute("msg", message);

		return "redirect:/coin/success";

	}

	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void list(Model model, Authentication authentication) throws Exception {
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();

		Long userNo = member.getUserNo();

		model.addAttribute("list", service.list(userNo));
	}

	@GetMapping("/success")
	public String success() throws Exception {
		return "coin/success";
	}

	@GetMapping("/listPay")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void listPayHistory(Model model, Authentication authentication) throws Exception {
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		Long userNo = member.getUserNo();
		model.addAttribute("list", service.listPayHistory(userNo));
	}

}
