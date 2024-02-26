package com.ccp4	.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccp4.dto.MemberDTO;
import com.ccp4.mapper.MemberMapper;


@Service
public class MemberService {
	@Autowired
	private MemberMapper mMapper;

	// 회원가입
	public void join(MemberDTO member) {
		mMapper.insert(member);
	}

	// 아이디 체크
	public int idCheck(String id) {
		return mMapper.idCheck(id);
	}

	// 로그인 체크
	public MemberDTO loginCheck(String id) {
		return mMapper.loginCheck(id);
	}
}
