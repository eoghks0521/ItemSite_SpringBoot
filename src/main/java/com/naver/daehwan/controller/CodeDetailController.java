package com.naver.daehwan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.dto.CodeLabelValue;
import com.naver.daehwan.model.CodeDetail;
import com.naver.daehwan.service.CodeDetailService;
import com.naver.daehwan.service.CodeService;

@Controller
@RequestMapping("/codedetail")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CodeDetailController {

	@Autowired
	CodeDetailService codeDetailService;

	@Autowired
	CodeService codeService;

	@GetMapping("/register")
	public void registerForm(Model model) throws Exception {
		CodeDetail codeDetail = new CodeDetail();
		model.addAttribute(codeDetail);

		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	@PostMapping("/register")
	public String register(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.register(codeDetail);

		rttr.addFlashAttribute("msg", "Success");
		return "redirect:/codedetail/list";
	}

	@GetMapping("/list")
	public void list(Model model) throws Exception {
		model.addAttribute("list", codeDetailService.list());
	}

	@GetMapping("/read")
	public void read(CodeDetail codeDetail, Model model) throws Exception {
		model.addAttribute(codeDetailService.read(codeDetail));

		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	@PostMapping("/modify")
	public String modifyForm(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {

		codeDetailService.modify(codeDetail);
		rttr.addFlashAttribute("msg", "Success");

		return "redirect:/codedetail/list";
	}

	@PostMapping("/remove")
	public String remove(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.remove(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codedetail/list";
	}
}
