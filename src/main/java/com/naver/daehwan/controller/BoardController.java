package com.naver.daehwan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.model.Board;
import com.naver.daehwan.model.CustomUser;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void registerForm(Model model, Authentication authentication) throws Exception{
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		Board board  =new Board();
		
		board.setWriter(member.getUserId());
		
		model.addAttribute(board);
		
	}
	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String register(Board board, RedirectAttributes rttr) throws Exception{
		service.register(board);
		
		rttr.addFlashAttribute("msg","Success");
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list",service.list());
	}
	@GetMapping("/read")
	public void read(Long boardNo ,Model model) throws Exception{
		model.addAttribute(service.read(boardNo));
		
	}
	
	@GetMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public void modifyForm(Long boardNo, Model model) throws Exception{
		model.addAttribute(service.read(boardNo));
	}
	
	@PostMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public String modify(Board board, RedirectAttributes rttr) throws Exception{
		service.modify(board);
		rttr.addFlashAttribute("msg","Success");
		
		return "redirect:/board/list";
		
	}
	
	@PostMapping("/remove")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public String remove(Long boardNo, RedirectAttributes rttr) throws Exception{
		service.remove(boardNo);
		
		rttr.addFlashAttribute("msg", "Success");
		
		return "redirect:/board/list";
	}
}
