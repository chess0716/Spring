package com.ccp4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ccp4.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	public void insertBoard(BoardDTO board);
	   @Select("SELECT * FROM board WHERE num = #{num}")
	    BoardDTO getBoardByNum(int num);
    
    @Select ("select * from board")
    public List<BoardDTO> getAllBoards();
    
    public void updateBoard(BoardDTO board);
    public void deleteBoard(int num);
    
    @Update("update board set replyCnt = replyCnt + #{amount} where num=#{bnum}")
    public void replyCnt(@Param("bnum") int  bnum, 
			  @Param("amount")    int amount);
}
