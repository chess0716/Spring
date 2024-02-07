package com.myspring.app07.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.myspring.app07.model.GuestVO;

@Mapper
@Repository
public interface GuestMapper {
    // �߰�
    @Insert("INSERT INTO guest (name, content, grade, created, ipaddr) " +
            "VALUES (#{name}, #{content}, #{grade}, NOW(), #{ipaddr})")
    void insert(GuestVO guest);

    // ��ü����
    @Select("SELECT * FROM guest")
    List<GuestVO> list();

    // �󼼺���
    @Select("SELECT * FROM guest WHERE num = #{num}")
    GuestVO view(int num);

    // ����
    @Update("UPDATE guest SET name = #{name}, content = #{content}, grade = #{grade}, ipaddr = #{ipaddr} WHERE num = #{num}")
    void update(GuestVO guest);

    // ����
    @Delete("DELETE FROM guest WHERE num = #{num}")
    void delete(int num);

    // ����
    @Select("SELECT COUNT(*) FROM guest")
    int count();
    
    // �˻�
    @Select("SELECT * FROM guest WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<GuestVO> searchGuest(HashMap<String, String> hm);
}

