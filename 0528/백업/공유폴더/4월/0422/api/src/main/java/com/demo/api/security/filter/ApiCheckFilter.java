package com.demo.api.security.filter;

import com.demo.api.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
  private String[] pattern;
  private JWTUtil jwtUtil;

  public ApiCheckFilter(String[] pattern, JWTUtil jwtUtil) {
    this.pattern = pattern;
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    log.info("REQUEST URI: " + request.getRequestURI());
    log.info("REQUEST URI: " + request.getContextPath() + pattern);
    log.info("REQUEST match: " + request.getContextPath() + pattern, request.getRequestURI());
    boolean check = false;
    for (int i = 0; i < pattern.length; i++) {
      if ((request.getContextPath() + pattern).equals(request.getRequestURI())) {
        check = true;
        break;
      }
    }
    if (check) {
      log.info("ApiCheckFilter.................");
      boolean checkHeader = checkAuthHeader(request);
      if (checkHeader) {
        filterChain.doFilter(request, response);
        return;
      } else {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        String message = "FAIL CHECK API TOKEN";
        jsonObject.put("code", "403");
        jsonObject.put("message", message);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  private boolean checkAuthHeader(HttpServletRequest request) {
    boolean checkResult = false;
    String authHeader = request.getHeader("Authorization");
    if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
      log.info("Authorization exist: " + authHeader);
      try {
        String email = jwtUtil.validateAndExtract(authHeader.substring(7));
        log.info("validate result: " + email);
        checkResult = email.length() > 0;
      } catch (Exception e) {e.printStackTrace();}
    }
    return checkResult;
  }
}
