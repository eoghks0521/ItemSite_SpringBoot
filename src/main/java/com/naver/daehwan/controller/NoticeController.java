package com.naver.daehwan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.model.Notice;
import com.naver.daehwan.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	NoticeService service;

	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void registerForm(Model model) throws Exception {
		Notice notice = new Notice();

		model.addAttribute(notice);
	}

	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Notice notice, RedirectAttributes rttr) throws Exception {
		service.register(notice);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}

	@GetMapping("/list")
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	} // 상세 화면

	@GetMapping("/read")
	public void read(Long noticeNo, Model model) throws Exception {
		model.addAttribute(service.read(noticeNo));
	}

	// 삭제 처리
	@PostMapping("/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(Long noticeNo, RedirectAttributes rttr) throws Exception {
		service.remove(noticeNo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}

	// 수정 화면
	@GetMapping("/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void modifyForm(Long noticeNo, Model model) throws Exception {
		model.addAttribute(service.read(noticeNo));
	} // 수정 처리

	@PostMapping("/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(Notice notice, RedirectAttributes rttr) throws Exception {
		service.modify(notice);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}
}
