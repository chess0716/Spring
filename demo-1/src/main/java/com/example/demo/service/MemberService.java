package com.example.demo.service;

import com.example.demo.dto.MemberDTO;

public interface MemberService {
	//추가
	public void join(MemberDTO member);
	//아이디중복확인
	public int idCheck(String id);
	//로그인체크
	public MemberDTO loginCheck(String id);
	//수정
	public void update(MemberDTO member);
	

}
