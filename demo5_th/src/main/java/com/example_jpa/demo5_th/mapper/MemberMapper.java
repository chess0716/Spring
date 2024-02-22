package com.example_jpa.demo5_th.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example_jpa.demo5_th.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	//추가
		@Insert("insert into member values(#{id}, #{password},#{name},#{addr}, now())")
		public void join(MemberDTO member);
		//아이디중복확인
		@Select("select count(*)  from member where id=#{id}")
		public int idCheck(String id);
		//로그인체크
		@Select("select * from member where id=#{id}")
		public MemberDTO loginCheck(String id);
		//전체리스트
		@Select("select * from member")
		public List<MemberDTO>mlist();
		//회원수
		@Select("select count(*) from member")
		public int count();
}
