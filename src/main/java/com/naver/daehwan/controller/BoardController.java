package com.naver.daehwan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.dto.CodeLabelValue;
import com.naver.daehwan.dto.PaginationDTO;
import com.naver.daehwan.model.Board;
import com.naver.daehwan.model.CustomUser;
import com.naver.daehwan.model.Member;
import com.naver.daehwan.service.BoardService;
import com.naver.daehwan.vo.PageRequestVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;

	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void registerForm(Model model, Authentication authentication) throws Exception {
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		Board board = new Board();

		board.setWriter(member.getUserId());

		model.addAttribute(board);

	}

	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String register(Board board, RedirectAttributes rttr) throws Exception {
		service.register(board);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}

	@GetMapping("/list")
	public void list(@ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model) throws Exception {
		Page<Board> page = service.list(pageRequestVO);
		model.addAttribute("pgntn", new PaginationDTO<Board>(page));

		List<CodeLabelValue> searchTypeCodeValueList = new ArrayList<CodeLabelValue>();
		searchTypeCodeValueList.add(new CodeLabelValue("n", "---"));
		searchTypeCodeValueList.add(new CodeLabelValue("t", "Title"));
		searchTypeCodeValueList.add(new CodeLabelValue("c", "Content"));
		searchTypeCodeValueList.add(new CodeLabelValue("w", "Writer"));
		searchTypeCodeValueList.add(new CodeLabelValue("tc", "Title OR Content"));
		searchTypeCodeValueList.add(new CodeLabelValue("cw", "Content OR Writer"));
		searchTypeCodeValueList.add(new CodeLabelValue("tcw", "Title OR Content OR Writer"));
		model.addAttribute("searchTypeCodeValueList", searchTypeCodeValueList);
	}

	//	@GetMapping("/read")
	//	public void read(Long boardNo, Model model) throws Exception {
	//		model.addAttribute(service.read(boardNo));
	//
	//	}

	@GetMapping("/read")
	public void read(Long boardNo, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	//	@GetMapping("/modify")
	//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	//	public void modifyForm(Long boardNo, Model model) throws Exception {
	//		model.addAttribute(service.read(boardNo));
	//	}

	@GetMapping("/modify")
	public void modifyForm(Long boardNo, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model)
		throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	//	@PostMapping("/modify")
	//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	//	public String modify(Board board, RedirectAttributes rttr) throws Exception {
	//		service.modify(board);
	//		rttr.addFlashAttribute("msg", "SUCCESS");
	//
	//		return "redirect:/board/list";
	//
	//	}

	@PostMapping("/modify")
	public String modify(Board board, PageRequestVO pageRequestVO, RedirectAttributes rttr) throws Exception {
		service.modify(board);
		rttr.addAttribute("page", pageRequestVO.getPage());
		rttr.addAttribute("sizePerPage", pageRequestVO.getSizePerPage());

		rttr.addAttribute("searchType", pageRequestVO.getSearchType());
		rttr.addAttribute("keyword", pageRequestVO.getKeyword());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}

	//	@PostMapping("/remove")
	//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	//	public String remove(Long boardNo, RedirectAttributes rttr) throws Exception {
	//		service.remove(boardNo);
	//
	//		rttr.addFlashAttribute("msg", "SUCCESS");
	//
	//		return "redirect:/board/list";
	//	}

	@PostMapping("remove")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public String remove(Long boardNo, PageRequestVO pageRequestVO, RedirectAttributes rttr) throws Exception {
		service.remove(boardNo);
		rttr.addAttribute("page", pageRequestVO.getPage());
		rttr.addAttribute("sizePerPage", pageRequestVO.getSizePerPage());

		rttr.addAttribute("searchType", pageRequestVO.getSearchType());
		rttr.addAttribute("keyword", pageRequestVO.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}

}
