package com.example_jpa.demo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example_jpa.demo4.model.User;
import com.example_jpa.demo4.repository.UserRepository;
import com.example_jpa.demo4.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final UserService userService;
	private final UserRepository userRepository;
	@GetMapping("/")
	public String home() {
		return "home";
	}
	//로그인폼
	@GetMapping("login")
	public String login() {
		return "user/login";
	}
	//회원가입폼
	@GetMapping("join")
	public String join() {
		return "user/join";
	}
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody User user) {
		
		 if(userRepository.findByUsername(user.getUsername())!=null) {
			 return "fail";
		 }
		userService.join(user);
		return "success";
		
	}
}




