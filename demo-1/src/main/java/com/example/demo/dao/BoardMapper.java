package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.FileBoardDTO;

@Mapper
public interface BoardMapper {
		
	//파일추가
		@Insert("insert into fileboard(title, writer, content, fileimage) "
				+ " values(#{title},#{writer},#{content}, #{fileimage})")
		public void fileInsert(FileBoardDTO fboard);
		//파일리스트
		@Select("select * from fileboard")
		public List<FileBoardDTO> fileList();
		
	
		//추가
		@Insert("insert into board(title, writer,content) values(#{title}, #{writer},#{content})")
		public void insert(BoardDTO board) ;
		//전체보기 
		@Select ("select * from board")
		public List<BoardDTO> list(HashMap<String, Object> hm);
		
		//개수
		@Select("select count(*) from board")
		public int count(HashMap<String, Object> hm);
		
		//상세보기 
		@Select("select * from board where num=#{num}")
		public BoardDTO view(int num);
		// 수정
		@Update("update board set title=#{title}, writer=#{writer}, content=#{content}, regdate=now() where num=#{num}")
		public void update(BoardDTO board);

		//삭제
		@Delete("delete from board where num=#{num}")
		public void delete(int num);
		
	    // 조회수 
	    @Update("update board set hitcount=hitcount+1 where num=#{num}")
	    public void updateHitCount(int num);
	    
	    //replyCnt 증감
		@Update("update board set replyCnt = replyCnt + #{amount} where num=#{bnum}")
	   public void replyCnt(@Param("bnum") int  bnum, 
			   @Param("amount")    int amount);
}
