package com.naver.daehwan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.dto.CodeLabelValue;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.service.CodeService;
import com.naver.daehwan.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService service;
	@Autowired
	private CodeService codeService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public void registerForm(Member member, Model model) throws Exception {
		String classCode = "A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
		model.addAttribute("jobList", jobList);
	}

	@PostMapping("/register")
	public String register(@Validated Member member, BindingResult result, Model model, RedirectAttributes rttr)
			throws Exception {
		if (result.hasErrors()) {
			String classCode = "A01";
			List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
			model.addAttribute("jobList", jobList);

			return "user/register";
		}
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));

		service.register(member);

		rttr.addFlashAttribute("userName", member.getUserName());
		return "redirect:/user/registerSuccess";
	}

	@GetMapping("/registerSuccess")
	public void registerSuccess(Model model) throws Exception {

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	// 상세 화면
	@GetMapping("/read")
	public void read(Long userNo, Model model) throws Exception {
		String classCode = "A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
		model.addAttribute("jobList", jobList);
		model.addAttribute(service.read(userNo));

	}

	// 삭제 처리
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/remove")
	public String remove(Long userNo, RedirectAttributes rttr) throws Exception {
		service.remove(userNo);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/user/list";
	}

	// 수정 화면
	@GetMapping("/modify")
	public void modifyForm(Long userNo, Model model) throws Exception {
		 String classCode = "A01"; 
		 List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
		 model.addAttribute("jobList", jobList);
		 model.addAttribute(service.read(userNo)); 
	}

	// 수정 처리 
	@PostMapping("/modify")
	public String modify(Member member, RedirectAttributes rttr) throws Exception { 
		service.modify(member);
		rttr.addFlashAttribute("msg", "SUCCESS"); return "redirect:/user/list";
	}
	
	//admin 권한을 가진 최초관리자 생성
	@GetMapping("/setup")
	public String setupAdminForm(Member member, Model model) throws Exception{
		if(service.countAll()==0) {
			return "user.setup";
		}
		
		return "user/setupFailure";
	}
	@PostMapping("/setup")
	public String setupAdmin(Member member, RedirectAttributes rttr) throws Exception{
		if(service.countAll()==0) {
			String inputPassword = member.getUserPw();
			member.setUserPw(passwordEncoder.encode(inputPassword));
			
			member.setJob("00");
			
			service.setupAdmin(member);
			
			rttr.addFlashAttribute("userName",member.getUserName());
			return "redirect:/user/registerSuccess";
			
		}
		
		return "regirect:/user/setupFailure";
	}
	
	
}