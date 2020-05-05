package com.naver.daehwan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
}
