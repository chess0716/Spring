package com.example_jpa.demo4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();	
	}
	@Bean
	public  SecurityFilterChain fileChain(HttpSecurity http) throws Exception{
		  http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request -> request
                  .requestMatchers("/user/*").authenticated()
                .anyRequest().permitAll()
        )
        .formLogin(login -> login
      		  .loginPage("/login")
      		  .loginProcessingUrl("/loginPro")

      		  .defaultSuccessUrl("/")    
        );
		return http.build();
	}
}
