package com.ccp4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccp4.dto.BoardDTO;
import com.ccp4.mapper.BoardMapper;

@Service

public class BoardService {
	@Autowired
	public  BoardMapper boardMapper;
	
	//메인
	public List<BoardDTO> getAllBoards(){
		return boardMapper.getAllBoards();
	}
	//뷰
	public BoardDTO getBoardByNum(int num) {
		return boardMapper.getBoardByNum(num);
	}
}
