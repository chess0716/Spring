package com.ccp4.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    	  http
          .authorizeHttpRequests((auth) -> auth
                  .requestMatchers("/", "/login", "/loginProc").permitAll()
                  .requestMatchers("/admin").hasRole("ADMIN")
                 
                  .anyRequest().permitAll()
          );
    	
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/member/login")
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