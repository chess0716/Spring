package com.demo.api.config;

import com.demo.api.security.filter.ApiCheckFilter;
import com.demo.api.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Log4j2
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  @Bean
  PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

  @Bean
  protected SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
    // MvcRequestMatcher.Builder mvcMatherBuilder = new MvcRequestMatcher.Builder()
    httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
        authorizationManagerRequestMatcherRegistry
            .requestMatchers("/members/**").permitAll()
            .requestMatchers("/test/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated());
    httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
    return httpSecurity.build();
  }

  @Bean
  public JWTUtil jwtUtil() {return new JWTUtil();}

  @Bean
  public ApiCheckFilter apiCheckFilter() {
    return new ApiCheckFilter(new String[]{"/test/**"}, jwtUtil());
  }
}
