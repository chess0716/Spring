package com.demo.api.config;

import com.demo.api.security.filter.ApiCheckFilter;
import com.demo.api.security.filter.ApiLoginFilter;
import com.demo.api.security.handler.ApiLoginFailHandler;
import com.demo.api.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Log4j2
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private static final String[] PERMIT_ALL_LIST = {
      "/error/**", "/", "/members/join", "/members/login"
  };
  private static final String[] AUTHENTICATED_LIST = {
      "/members/get/**", "/members/delete/**", "/members/update", "/follow/**", "/image/**", "/like/board"
  };
  private static final String[] ADMIN_LIST = {
      "/admin/update", "/report/**", "/users/approve/**", "/users/filter-find/**"
  };

  @Bean
  PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

  @Bean
  protected SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    // httpSecurity.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfig.corsConfigurationSource()));
    httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
      authorizationManagerRequestMatcherRegistry.requestMatchers(PERMIT_ALL_LIST).permitAll();
      authorizationManagerRequestMatcherRegistry.requestMatchers(AUTHENTICATED_LIST).authenticated();
      authorizationManagerRequestMatcherRegistry.requestMatchers(ADMIN_LIST).hasAuthority("[ADMIN]");
      authorizationManagerRequestMatcherRegistry.anyRequest().denyAll();
    });

    // UsernamePasswordAuthenticationFilter 이전에 동작하도록 filter chain에 지정
    httpSecurity.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);

    // httpSecurity.getSharedObject는 자신이 가지고 있는 AuthenticationConfiguration을 끄집어 낸 것
    // apiLoginFilter는 apiCheckFilter로 가기 전에 사용할 토큰을 발행하는 필터
    httpSecurity.addFilterBefore(apiLoginFilter(httpSecurity.getSharedObject(AuthenticationConfiguration.class)), UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
  }

  @Bean
  public JWTUtil jwtUtil() {return new JWTUtil();}

  @Bean // 클라이언트로부터 요청된 주소에 권한이 있는지 없는지 확인
  public ApiCheckFilter apiCheckFilter() {
    String[] pattern = {"/members/get/**"}; // AntPathMatcher의 패턴 표기법
    return new ApiCheckFilter(pattern, jwtUtil());
  }

  @Bean
  public ApiLoginFilter apiLoginFilter(AuthenticationConfiguration ac) throws Exception {
    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/members/login", jwtUtil());
    apiLoginFilter.setAuthenticationManager(ac.getAuthenticationManager());
    apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
    return apiLoginFilter;
  }

  @Bean
  // authentication bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}