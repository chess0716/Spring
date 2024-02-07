package com.myspring.app06.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import com.myspring.app06.model.GuestVO;

public interface GuestMapper {
    // 추가
    @Insert("INSERT INTO guest (name, content, grade, created, ipaddr) " +
            "VALUES (#{name}, #{content}, #{grade}, NOW(), #{ipaddr})")
    void insert(GuestVO guest);

    // 전체보기
    @Select("SELECT * FROM guest")
    List<GuestVO> list();

    // 상세보기
    @Select("SELECT * FROM guest WHERE num = #{num}")
    GuestVO view(int num);

    // 수정
    @Update("UPDATE guest SET name = #{name}, content = #{content}, grade = #{grade}, ipaddr = #{ipaddr} WHERE num = #{num}")
    void update(GuestVO guest);

    // 삭제
    @Delete("DELETE FROM guest WHERE num = #{num}")
    void delete(int num);

    // 개수
    @Select("SELECT COUNT(*) FROM guest")
    int count();
    
    // 검색
    @Select("SELECT * FROM guest WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<GuestVO> searchGuest(HashMap<String, String> hm);
}
