package com.ccp4.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ccp4.dto.MemberDTO;


@Mapper
public interface MemberMapper {
	@Insert("insert into member(id,password) values(#{id},#{password}")
	public void insert(MemberDTO member);

	// 아이디 중복체크
	@Select("select count(*) from member where id=#{id}")
	public int idCheck(String id);

	// 로그인 체크
	@Select("select * from member where id=#{id}")
	public MemberDTO loginCheck(String id);

	// 수정
	@Update("update member set password=#{password}, name=#{name}")
	public void update(MemberDTO member);
}
