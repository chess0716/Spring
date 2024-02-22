package com.example_jpa.demo5_th.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example_jpa.demo5_th.dto.MemberDTO;
import com.example_jpa.demo5_th.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private  final MemberMapper  memberMapper;
	
	//추가
	public void join(MemberDTO member) {
		memberMapper.join(member);
	}
	//아이디 체크
	public int idCheck(String id) {
		return memberMapper.idCheck(id);
	}
	//로그인체크
	public MemberDTO loginCheck(String id) {
		return memberMapper.loginCheck(id);
	}
	//전체보기
	public List<MemberDTO> mlist(){
		return memberMapper.mlist();
	}
	//회원수
	public int count() {
		return memberMapper.count();
	}

}
