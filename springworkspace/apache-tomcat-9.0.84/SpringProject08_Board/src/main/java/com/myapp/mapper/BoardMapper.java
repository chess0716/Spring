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
    //�뙆�씪異붽�
	@Insert("insert into fileboard(title, writer, content, fileimage) "
			+ " values(#{title},#{writer},#{content}, #{fileimage})")
	public void fileInsert(FileBoardDTO fboard);
	//�뙆�씪由ъ뒪�듃
	@Select("select * from fileboard")
	public List<FileBoardDTO> fileList();
	
	
	//異붽�
	@Insert("insert into board(title, writer,content) values(#{title}, #{writer},#{content})")
	public void insert(BoardDTO board);
	//�쟾泥대낫湲�  ==>    findAll()
	@Select("select * from board")
	public List<BoardDTO> findAll(HashMap<String, Object> hm);
	
	//媛쒖닔
	@Select("select count(*) from board")
	public int count(HashMap<String, Object> hm);
	
	//�긽�꽭蹂닿린  ==>findByNum()
	@Select("select * from board where num=#{num}")
	public BoardDTO findByNum(int num);
	//�닔�젙
	@Update("update board set title=#{title}, content=#{content}, regdate=now() where num=#{num}")
	public void update(BoardDTO board);
	//�궘�젣
	@Delete("delete from board where num=#{num}")
	public void delete(int num);

	//議고쉶�닔利앷�
	@Update("update board set hitcount = hitcount+1 where num=#{num}")
	public void upReadCount(int num);
	
	///////////////
	//replyCnt 利앷컧
	@Update("update board set replyCnt = replyCnt + #{amount} where num=#{bnum}")
   public void replyCnt(@Param("bnum") int  bnum, 
		   @Param("amount")    int amount);
	

	
	
	
	
	
}
