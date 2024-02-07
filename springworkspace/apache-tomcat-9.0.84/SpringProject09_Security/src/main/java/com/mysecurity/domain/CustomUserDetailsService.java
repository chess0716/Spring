package com.mysecurity.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myspring.dto.CustomUser;
import com.myspring.dto.MemberDTO;
import com.myspring.mapper.MemberMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberMapper memberMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	MemberDTO member = memberMapper.read(username);
    	System.out.println("member"+member);
    	CustomUser user = new CustomUser(member);
    	System.out.println("user"+user);
    	return member == null ? null:user;
    }
}
