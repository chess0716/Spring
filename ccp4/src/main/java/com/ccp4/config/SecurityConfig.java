package com.ccp4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	@Bean
	public  BCryptPasswordEncoder  encodePwd() {
		return new  BCryptPasswordEncoder();
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