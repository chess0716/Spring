package com.example_jpa.demo5_th.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example_jpa.demo5_th.dto.MemberDTO;
import com.example_jpa.demo5_th.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@GetMapping("join")
	public String join() {
		return "member/join";
	}

	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody MemberDTO member) {
		int cnt = memberService.idCheck(member.getId());
		if (cnt != 0)
			return "fail"; // 아이디 존재
		memberService.join(member);
		return "success";
	}

	// 로그인폼
	@GetMapping("login")
	public String login() {
		return "member/login";
	}

	@PostMapping("login")
	@ResponseBody
	public String login(@RequestBody MemberDTO member, HttpSession session) {
		MemberDTO m = memberService.loginCheck(member.getId());
		if(m==null) {  //회원아님
			return "no";
		}
		if (m.getPassword().equals(member.getPassword())) { // 회원(비번맞음)
			session.setAttribute("sMember", m);
			return "success";
		}else { //비번오류
			return "fail";
		}
	}
	
	//회원리스트
	@GetMapping("mlist")
	public String list(Model model) {
		model.addAttribute("members", memberService.mlist());
		model.addAttribute("mcount", memberService.count());
		return "member/mlist";
	}
	//로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}

}
