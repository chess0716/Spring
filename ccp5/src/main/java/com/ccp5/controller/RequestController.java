package com.ccp5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccp5.dto.RequestBoard;
import com.ccp5.service.RequestBoardSevice;



@Controller
@RequestMapping("/request/*")
public class RequestController {
	@Autowired
	private RequestBoardSevice rbService;
	
	// 요청 게시글 작성폼
	@GetMapping("/request_insert")
	public String r_insert() {
		return "/request/request_insert";
	}
	// 요청 게시글 추가
	@PostMapping("/request_insert")
	public String r_insert(RequestBoard rboard) {
		rbService.insert(rboard);
		return "redirect:/request/request_list";
	}
	
	// 요청 게시글 불러오기
	@GetMapping("/request_list")
	public String r_list(Model model) {
		model.addAttribute("boards",rbService.list());
		return "/request/request_list";
	}
	
	
	// 요청 게시글 자세히 보기
	@GetMapping("/request_view/{num}")
	public String r_view(@PathVariable long num) {
		return "request/request_view";
	}

}
