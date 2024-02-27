package com.ccp5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccp5.dto.User;
import com.ccp5.repository.UserRepository;
import com.ccp5.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor

public class HomeController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "fail";
        }
        userService.join(user);
        return "success";
    }
}
