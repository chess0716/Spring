package com.ccp5.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ccp5.service.UserService;

import lombok.RequiredArgsConstructor;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
		
	private final AuthenticationFailureHandler customFailureHandler;
	private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    	  http
          .authorizeHttpRequests((auth) -> auth
                  .requestMatchers("/", "/login", "/loginPro").permitAll()
                  .requestMatchers("/admin").hasRole("ADMIN")
                 
                  .anyRequest().permitAll()
          );
    	
        http
                .formLogin((auth) -> auth.loginPage("/login")
                		.usernameParameter("username") // 사용자 이름 파라미터 지정
                        .passwordParameter("password") // 비밀번호 파라미터 지정
                        .loginProcessingUrl("/loginPro")
                        .failureHandler(customFailureHandler)
                       
                        .defaultSuccessUrl("/")  
                        .permitAll()
                       
                );
       

        http
                .csrf((auth) -> auth.disable());
        
        
        return http.build();
    }public void configure(WebSecurity web) throws Exception {
    web
        .ignoring()
        .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**");
}	
   
}