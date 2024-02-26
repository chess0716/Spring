package com.ccp4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ccp4.dto.MemberDTO;
import com.ccp4.mapper.MemberMapper;

@Service
public class MemberService {
    
    @Autowired
    private MemberMapper mMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void join(MemberDTO member) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        
        // 기본 권한 설정 (예: USER)
        member.setAuth("USER");
        
        // 회원 정보 저장
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
    
    // 사용자 권한 반환
    public String getUserAuth(String id) {
        return mMapper.getUserAuth(id);
    }

 // 운영자가 있는지 확인하는 메서드
    public boolean adminExists() {
        // 사용자 테이블에서 운영자인 회원 수를 조회합니다.
        int count = mMapper.countAdmins();
        
        // 운영자가 1명 이상이면 true를 반환합니다.
        return count > 0;
    }

    public MemberDTO findById(String id) {
        // 회원 정보를 데이터베이스에서 조회합니다.
        MemberDTO member = mMapper.findById(id);
        return member;
}


}