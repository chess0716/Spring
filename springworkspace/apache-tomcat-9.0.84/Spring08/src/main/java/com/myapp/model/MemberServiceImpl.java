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
	@Override
	public MemberDTO loginCheck(String id) {
		
		return memberMapper.loginCheck(id);
	}
	@Override
	public void update(MemberDTO member) {
		// TODO Auto-generated method stub
		memberMapper.update(member);
	}

}
