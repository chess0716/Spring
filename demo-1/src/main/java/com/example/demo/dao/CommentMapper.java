package com.example.demo.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.CommentDTO;



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

	 // 해당 게시물의 댓글 수 조회
    @Select("select count(*) from commentboard where bnum = #{bnum}")
    public int countByBnum(int bnum);

}
