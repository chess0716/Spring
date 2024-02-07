package com.myspring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myspring.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	//�߰�
	 @Insert("insert into board(title, writer, content) values(#{title}, #{writer}, #{content})")
	    public void insert(BoardDTO board);
	 @Select("select * from board")
	 public List<BoardDTO> list();
	 
	 @Select("select count(*) from board")
	 public int count();
	 
	 @Select("select * from board where num = #{num}")
	 public BoardDTO view(int num);
	 
     @Update("update board set title = #{title}, content = #{content} where num = #{num}")
     public void update(BoardDTO board);
    
     @Delete("delete from board where num = #{num}")
     public void delete(int num);
     
     @Update("update board set replyCnt = replyCnt + #{amount} where num=#{bnum}")
     public void updateReplyCnt(@Param("bnum") int bnum, @Param("amount") int amount);

}
