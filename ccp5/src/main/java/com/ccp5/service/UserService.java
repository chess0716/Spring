package com.ccp5.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ccp5.dto.User;
import com.ccp5.repository.UserRepository;
import com.ccp5.dto.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder; // BCryptPasswordEncoder 주입
	
	public void join(User user) {
		// Role 추가
		if (user.getRole() == null || user.getRole() == Role.USER) {
			// 기본적으로 USER 권한을 가지도록 설정
			user.setRole(Role.USER);
		} else if (user.getRole() == Role.ADMIN) {
			// Role이 ADMIN인 경우
			// 다른 권한을 가진 사용자가 ADMIN으로 가입하려는 시도 등을 방지하기 위해 기존 권한을 무시하고 ADMIN으로 설정
			user.setRole(Role.ADMIN);
		}
		
		// 비밀번호 암호화
		String encPassword = encoder.encode(user.getPassword()); // 주입받은 encoder를 사용하여 비밀번호 암호화
		user.setPassword(encPassword);
		
		userRepository.save(user);        
	}
}
