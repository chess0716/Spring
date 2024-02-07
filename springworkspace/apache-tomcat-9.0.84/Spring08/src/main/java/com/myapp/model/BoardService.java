package com.myapp.model;

import java.util.HashMap;
import java.util.List;

import com.myapp.dto.BoardDTO;
import com.myapp.dto.FileBoardDTO;

public interface BoardService {
    //추가
	public void insert(BoardDTO board);
	//전체보기
	public List<BoardDTO> findAll(HashMap<String, Object> hm);
	//상세보기
	public BoardDTO findByNum(int num);
	//수정
	public void update(BoardDTO board);
	//삭제
	public void delete(int num);
	//개수
	public int getCount(HashMap<String, Object> hm);
	
	//파일
	public void fileInsert(FileBoardDTO fboard);
	public List<FileBoardDTO> fileList();
	

}
