package com.ccp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccp4.dto.MemberDTO;
import com.ccp4.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String login() {
        return "member/login"; // member 폴더에 있는 login.html을 반환합니다.
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginPro(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        MemberDTO member = memberService.loginCheck(username);
        if (member != null && member.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", member);
            // 세션에 사용자 정보가 올바르게 저장되었음을 로그로 출력
            System.out.println("Logged in user: " + member.getId());
            System.out.println("Session ID: " + session.getId()); // 세션 ID 출력
            return "redirect:/"; // 로그인 후 리다이렉트할 페이지로 설정
        } else {
            System.out.println("Login failed for user: " + username); // 로그인 실패 시 메시지 출력
            return "redirect:/member/login?error=1"; // 로그인 실패 시 에러 페이지로 리다이렉트
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("User logged out."); // 로그아웃 시 메시지 출력
        System.out.println("Session ID: " + session.getId()); // 세션 ID 출력
        return "redirect:/member/login"; // 로그아웃 후 로그인 페이지로 이동
    }

    // 회원가입 페이지로 이동
    @GetMapping("/register")
    public String register() {
        return "member/register"; // member 폴더에 있는 register.html을 반환합니다.
    }

    @PostMapping("/register")
    public String register(MemberDTO member) {
        // 운영자가 있는지 확인합니다.
        boolean adminExists = memberService.adminExists();
        
        // 운영자가 없는 경우에는 새로 가입하는 회원의 권한을 ADMIN으로 설정합니다.
        // 운영자가 있는 경우에는 회원의 권한을 USER로 설정합니다.
        if (!adminExists) {
            member.setAuth("ADMIN");
        } else {
            member.setAuth("USER");
        }

        // 회원가입 처리
        memberService.join(member);

        // 성공 메시지 반환
        System.out.println("User registered: " + member.getId()); // 회원가입 성공 시 메시지 출력
        return "redirect:/member/login"; // 회원가입 후 로그인 페이지로 이동
    }
    @ModelAttribute("loginStatus")
    public String loginStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            // 사용자가 인증되었고 익명 사용자가 아닌 경우
            return "로그아웃";
        } else {
            // 그 외의 경우
            return "로그인";
        }
    }

 
}
