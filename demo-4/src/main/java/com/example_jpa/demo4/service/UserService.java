package com.example_jpa.demo4.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example_jpa.demo4.model.User;
import com.example_jpa.demo4.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final BCryptPasswordEncoder encoder;
	private final UserRepository userRepository;
	
	public void join(User user) {
		//비번암호화
		 String encPassword = 	encoder.encode(user.getPassword()) ;
		 user.setPassword(encPassword);
		//role  추가
		 user.setRole("ROLE_USER");
		userRepository.save(user);
	}

}
