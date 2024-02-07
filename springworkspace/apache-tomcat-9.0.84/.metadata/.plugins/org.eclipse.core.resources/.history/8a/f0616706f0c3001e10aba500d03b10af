package com.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.dto.MemberDTO;
import com.myapp.mapper.MemberMapper;

@Service
public class MemberServiceImpl  implements MemberService{
  @Autowired
  private MemberMapper memberMapper;
  //추가
	@Override
	public void join(MemberDTO member) {
		memberMapper.join(member);
	}
	//아이디중복확인
	@Override
	public int idCheck(String id) {
		return memberMapper.idCheck(id);
	}
	//로그인체크
	@Override
	public MemberDTO loginCheck(String id) {
			return memberMapper.loginCheck(id);
	}
	//수정
	@Override
	public void update(MemberDTO member) {
		memberMapper.update(member);
		
	}

}
