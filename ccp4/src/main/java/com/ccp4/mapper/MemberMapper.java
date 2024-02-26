package com.ccp4.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;

import com.ccp4.dto.MemberDTO;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member (id, password, auth) VALUES (#{id}, #{password}, #{auth})")
    void insert(MemberDTO member);
    
    @Select("SELECT COUNT(*) FROM member WHERE id = #{id}")
    int idCheck(String id);
    
    @Select("SELECT * FROM member WHERE id = #{id}")
    MemberDTO loginCheck(String id);
    
    // 사용자 권한 조회
    @Select("SELECT auth FROM member WHERE id = #{id}")
    String getUserAuth(String id);
    // 운영자 수 조회
    @Select("SELECT COUNT(*) FROM member WHERE auth = 'ADMIN'")
    int countAdmins();

    @Select("SELECT * FROM member WHERE id = #{id}")
    MemberDTO findById(String id);

}
