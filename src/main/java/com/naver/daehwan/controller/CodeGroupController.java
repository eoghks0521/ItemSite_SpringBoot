package com.naver.daehwan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.model.CodeGroup;
import com.naver.daehwan.service.CodeGroupService;

@Controller
@RequestMapping("/codegroup")
public class CodeGroupController {

	@Autowired
	CodeGroupService service;
	
	@GetMapping("/register")
	public void registerForm(Model model)throws Exception{
		CodeGroup codeGroup = new CodeGroup();
		
		model.addAttribute(codeGroup);
	}
	
	@PostMapping("/register")
	public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception{
		service.register(codeGroup);
		
		rttr.addFlashAttribute("msg","Success");
		return "redirect:/codegroup/list";
	}
	
	@GetMapping("/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list",service.list());
	}
	
	@GetMapping("/read")
	public void read(String groupCode, Model model) throws Exception{
		model.addAttribute(service.read(groupCode));
	}
	
	@PostMapping("/modify")
	public String modify(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception{
		service.modify(codeGroup);
		rttr.addFlashAttribute("msg","Success");
		
		return "redirect:/codegroup/list";
	}

	@PostMapping("/remove")
	public String remove(String groupCode, RedirectAttributes rttr) throws Exception{
		
		service.remove(groupCode);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/codegroup/list";
	}
	
}
