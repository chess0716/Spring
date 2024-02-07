package com.myapp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myapp.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	//추가
	@Insert("insert into member values(#{id},#{password},#{name},#{addr},now())")
	public void join(MemberDTO member);
	
	//아이디중복확인
	@Select("select count(*) from member where id=#{id}")
	public int idCheck(String id);
	//로그인체크
	@Select("select * from member where id=#{id}")
	public MemberDTO loginCheck(String id);
	//수정
	@Update("update member set name=#{name}, password=#{password}, "
			+ " addr=#{addr}, regdate=now()  where id=#{id}")
	public void update(MemberDTO member);

}






