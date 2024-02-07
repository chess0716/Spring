package com.myapp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myapp.dto.MemberDTO;

@Mapper
public interface MemberMapper {
    // 추가: 회원 가입 메서드
    @Insert("INSERT INTO member (id, password, name, addr) VALUES (#{id}, #{password}, #{name}, #{addr})")
    public void join(MemberDTO member);
    
    // 수정: ID 중복 확인 메서드
    @Select("SELECT COUNT(*) FROM member WHERE id = #{id}")
    public int idCheck(String id);
    // 로그인체크
    @Select ("select * from member where id =#(id")
    public MemberDTO loginCheck(String id);
    
    @Update("UPDATE member SET name = #{name},password = #(password)"+" addr = #{addr},regdate = no() WHERE id = #{id}")
	public void update(MemberDTO member);
}
