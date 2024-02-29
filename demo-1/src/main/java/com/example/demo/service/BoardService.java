package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.FileBoardDTO;

public interface BoardService {
	
	//파일
	public void fileInsert(FileBoardDTO fboard); //파일추가
	public List<FileBoardDTO> fileList(); //파일 전체보기
	
	
	
	//////////
	//추가
	public void insert(BoardDTO board);
	//전체보기
	public List<BoardDTO> list(HashMap<String, Object> hm);
	//상세보기
	public BoardDTO view(int num);
	//수정
	public void update(BoardDTO board);
	//삭제
	public void delete(int num);
	//개수
	public int getCount(HashMap<String, Object> hm);
	public void updateReplyCnt(long num);
	
}
