package com.example.hacker.controller;

import com.example.hacker.dto.MembersDTO;
import com.example.hacker.dto.ResponseDTO;
import com.example.hacker.security.util.JWTUtil;
import com.example.hacker.service.MembersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@Log4j2
@RequestMapping("auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

  private final MembersService membersService;
  private final JWTUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;


  // 회원가입
  @PostMapping(value = "/join")
  public ResponseEntity<Long> register(@RequestBody MembersDTO membersDTO) {
    log.info("register..."+membersDTO);

    long num = membersService.registMembersDTO(membersDTO);
    return new ResponseEntity<>(num, HttpStatus.OK);
  }

  // 로그인
  // (클라이언트가 보낸 이메일과 비밀번호를 사용하여 로그인을 시도, 성공하면 JWT 토큰을 생성하여 Map에 담아 반환)
  @PostMapping(value = "/login", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> getToken(HttpServletResponse response, @RequestBody Map<String, Object> mapObj) {
    String email = (mapObj.get("email") != null) ? mapObj.get("email").toString() : null;
    String pass = (mapObj.get("pass") != null) ? mapObj.get("pass").toString() : null;

    if (email == null || pass == null) {
      // 요청 바디에서 이메일 또는 비밀번호가 null일 경우에 대한 처리
      String errorMessage = "Email or password is null in login request";
      log.error("errormessage" + errorMessage);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", errorMessage));
    }

    log.info("Login attempt with email: " + email);

    // 사용자의 비밀번호를 해시화
    String encodedPassword = passwordEncoder.encode(pass);

    String token = membersService.login(email, encodedPassword, jwtUtil);
    Map<String, String> map = new HashMap<>();
    if (token != null && !token.isEmpty()) {
      map.put("token", token);
      return ResponseEntity.ok(map);
    } else {
      String errorMessage = "Login failed. Invalid email or password.";
      log.error(errorMessage);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", errorMessage));
    }
  }

  // 로그아웃
  // 로그아웃 시 토큰 기록이 사라지게 하고 싶은 경우 서버보다 클라이언트측에서 코드 작성하는 것이 더 좋음
  @PostMapping("/logout")
  public ResponseEntity<ResponseDTO> logout(HttpServletRequest request, HttpServletResponse response) {
    try {
      log.info(request);
      log.info(response);
      log.info(SecurityContextHolder.getContext().getAuthentication());
      new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
      return new ResponseEntity<>(new ResponseDTO("success", true), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(new ResponseDTO(e.getMessage(), false), HttpStatus.NOT_FOUND);
    }
  }
}
