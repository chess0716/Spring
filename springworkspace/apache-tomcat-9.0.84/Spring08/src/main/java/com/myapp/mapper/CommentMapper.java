package com.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.myapp.dto.CommentDTO;

@Mapper
public interface CommentMapper {
	//추가
	@Insert("insert into commentboard(userid, content,bnum) values(#{userid}, #{content}, #{bnum})")
	public void insert(CommentDTO comment);
	
  //전체보기
	@Select("select * from commentboard where bnum = #{bnum}")
	public List<CommentDTO> list(int bnum);
 //삭제
	@Delete("delete from commentboard where cnum =#{cnum}")
	public void delete(int cnum);
	//read
	@Select("select * from commentboard where cnum=#{cnum}")
	public CommentDTO read(int cnum);

}
