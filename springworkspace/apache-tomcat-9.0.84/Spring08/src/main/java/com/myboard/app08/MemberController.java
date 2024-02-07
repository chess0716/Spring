package com.myboard.app08;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myapp.dto.MemberDTO;
import com.myapp.model.MemberService;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@GetMapping("join")
	public void join() {
		
	}
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody MemberDTO member) {
		int chk=memberService.idCheck(member.getId());
		if(chk !=0) return "fail";
		
		memberService.join(member);
		return "success";
	}
	
	@GetMapping("login")
	@ResponseBody
	public String login(@RequestBody MemberDTO member,HttpSession session) {
		MemberDTO m = memberService.loginCheck(member.getId());
		if(m == null) {
			return "no";
		}
		if(member.getPassword().equals(m.getPassword())) {
			session.setAttribute("sessionMember", m);
			return "success";
		}else {
			return "fail";
		}
		
	

		
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
	
	@GetMapping("update")
	public void update () {
	
	}
	@PutMapping("update")
	@ResponseBody
	public String update (@RequestBody MemberDTO member, HttpSession session) {
		memberService.update(member);
		session.invalidate();
		return "success";
	}
	
	
}
