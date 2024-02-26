package com.ccp4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ccp4.dto.RequestBoard;

@Mapper
public interface RequestBoardMapper {
	// 요청 게시글 등록
	@Insert("insert into request_board(title, writer, content) values(#{title},#{writer},#{content})")
	public void insert(RequestBoard rboard);
	
	// 요청 게시글 목록 불러오기
	@Select("select * from request_board order by num desc")
	public List<RequestBoard> list();
}
