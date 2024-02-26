package com.ccp4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccp4.dto.MemberDTO;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 사용자 아이디(id)를 기반으로 사용자 정보를 가져옴
        MemberDTO member = memberService.findById(id);
        
        // 사용자 정보가 없으면 예외 발생
        if (member == null) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }

        // UserDetails 객체로 변환하여 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getId()) // 아이디로 설정
                .password(member.getPassword())
                .roles(member.getAuth()) // 권한 정보 설정
                .build();
    }
}
