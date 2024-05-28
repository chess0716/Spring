package com.demo.api.controller;

import com.demo.api.dto.MembersDTO;
import com.demo.api.dto.ResponseDTO;
import com.demo.api.service.MembersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
  private final MembersService membersService;

  @PostMapping(value = "/join")
  public ResponseEntity<Long> register(@RequestBody MembersDTO membersDTO) {
    log.info("register..."+membersDTO);

    long num = membersService.registMembersDTO(membersDTO);
    return new ResponseEntity<>(num, HttpStatus.OK);
  }

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
