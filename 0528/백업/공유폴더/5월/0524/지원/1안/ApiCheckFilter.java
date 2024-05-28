package com.demo.gram.security.filter;

import com.demo.gram.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.util.List;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
  private final List<String> patterns;
  private final JWTUtil jwtUtil;

  public ApiCheckFilter(List<String> patternStrings, JWTUtil jwtUtil) {
    this.patterns = patternStrings;
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    log.info("Checking request URI: " + requestURI);

    // "/auth" 경로에 대해서는 필터를 실행하지 않습니다.
    if (requestURI.startsWith("/auth")) {
      filterChain.doFilter(request, response);
      return;
    }

    // 나머지 경로에 대해서만 필터를 실행합니다.
    if (patterns.stream().anyMatch(requestURI::startsWith)) {
      String token = extractToken(request);
      if (StringUtils.hasText(token)) {
        log.info("Extracted token: " + token);
        try {
          String username = jwtUtil.validateAndExtract(token);
          log.info("Extracted username: " + username);
          if (StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 사용자의 보안 컨텍스트를 설정하여 인증된 사용자로 설정
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
        } catch (Exception e) {
          log.error("Error validating or extracting JWT token: " + e.getMessage());
          // 예외 처리: 유효하지 않은 토큰이나 추출 오류 등에 대한 처리
          // 예를 들어, 토큰이 유효하지 않거나 만료되었을 때, 인증 실패 응답을 전송할 수 있습니다.
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }
      } else {
        log.warn("No token found in request");
        // 예외 처리: 요청에서 토큰이 발견되지 않았을 때
        // 예를 들어, 헤더에 토큰이 없는 경우 인증 실패 응답을 전송할 수 있습니다.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
    }

    // 필터 체인을 통해 요청을 다음 필터로 전달
    filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
