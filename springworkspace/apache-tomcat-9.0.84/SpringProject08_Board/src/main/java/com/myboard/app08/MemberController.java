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
//	@GetMapping("join")
//	public String join() {
//		return "member/join";
//	}
	//�쉶�썝媛��엯
	@PostMapping("/join")
	@ResponseBody
	public String join(@RequestBody  MemberDTO  member) {
		int chk = memberService.idCheck(member.getId());
		if(chk !=0 )   return "fail" ;//�븘�씠�뵒 議댁옱
		
		memberService.join(member);
		return "success";
		
	}
	//濡쒓렇�씤�뤌
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	//濡쒓렇�씤
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody MemberDTO member, 
			           HttpSession session) {
		MemberDTO m =	memberService.loginCheck(member.getId());
		if(m==null) {  //�쉶�썝�븘�떂
			return "no";
		}
		if(member.getPassword().equals(m.getPassword())  ) { //�쉶�썝(鍮꾨쾲留욎쓬
			session.setAttribute("sMember", m);
			return "success";
		}else { //鍮꾨쾲�삤瑜�
			return "fail";
		}

	}
	//濡쒓렇�븘�썐
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
	//�닔�젙�뤌
	@GetMapping("update")
	public void update() {
		
	}
	//�닔�젙
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody MemberDTO member,
			HttpSession session) {
		memberService.update(member);
		session.invalidate();
		return "success";
	}
	
	
	
	
	
	
	
	

}
