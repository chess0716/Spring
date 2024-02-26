package com.ccp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccp4.dto.MemberDTO;
import com.ccp4.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService mService;
	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}
	@PostMapping("/login")
	@ResponseBody
	public String login(HttpSession session, HttpServletRequest request) { 
	    String id = request.getParameter("id");
	    String password = request.getParameter("password");

	    MemberDTO m = mService.loginCheck(id);
	    if (m == null) { // 회원이 아님
	        return "no";
	    }
	    if (password.equals(m.getPassword())) { // 회원이면서 비밀번호 일치
	        session.setAttribute("sMember", m);
	        return "redirect:/"; // 로그인 성공 시 index 페이지로 리다이렉트
	    } else { // 회원이지만 비밀번호 불일치
	        return "fail";
	    }
	}
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/member/login";
	}
	// 회원가입
	@GetMapping("/register")
	public String join() {
		return "member/register";
	}
	@PostMapping("/register")
	public String join(@RequestBody MemberDTO member) {
		int cnt=mService.idCheck(member.getId());
		if (cnt !=0)
			return "fail";
		mService.join(member);
		return "success";
	}
}
