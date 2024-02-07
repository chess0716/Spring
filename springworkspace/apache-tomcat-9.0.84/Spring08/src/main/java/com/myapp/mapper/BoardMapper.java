package com.myapp.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myapp.dto.BoardDTO;
import com.myapp.dto.FileBoardDTO;

@Mapper
public interface BoardMapper {
	//파일추가
	 @Insert("INSERT INTO fileboard (title, wirter, content, fileimage) VALUES (#{title}, #{writer}, #{content}, #{fileimage})")
	    public void fileInsert(FileBoardDTO file);

	    // 파일 목록 조회 메서드
	    @Select("SELECT * FROM fileboard")
	    public List<FileBoardDTO> boardList(HashMap<String, Object> hm);
	
	
    //추가
	@Insert("insert into board(title, writer,content) values(#{title}, #{writer},#{content})")
	public void insert(BoardDTO board);
	//전체보기  ==>    findAll()
	//@Select("select * from board")
	public List<BoardDTO> findAll(HashMap<String, Object> hm);
	
	//개수
	//@Select("select count(*) from board")
	public int count(HashMap<String, Object> hm);
	
	//상세보기  ==>findByNum()
	@Select("select * from board where num=#{num}")
	public BoardDTO findByNum(int num);
	//수정
	@Update("update board set title=#{title}, content=#{content}, regdate=now() where num=#{num}")
	public void update(BoardDTO board);
	//삭제
	@Delete("delete from board where num=#{num}")
	public void delete(int num);

	//조회수증가
	@Update("update board set hitcount = hitcount+1 where num=#{num}")
	public void upReadCount(int num);
	
	///////////////
	//replyCnt 증감
	@Update("update board set replyCnt = replyCnt + #{amount} where num=#{bnum}")
   public void replyCnt(@Param("bnum") int  bnum, 
		   @Param("amount")    int amount);
	
	
	
	
	
	
	
	
}
