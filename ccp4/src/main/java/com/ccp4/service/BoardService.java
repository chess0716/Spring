package com.ccp4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccp4.dto.BoardDTO;
import com.ccp4.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	public final BoardMapper boardMapper;
	
	//메인
	public List<BoardDTO> getAllBoards(){
		return boardMapper.getAllBoards();
	}
	//뷰
	public BoardDTO getBoardByNum(int num) {
		return boardMapper.getBoardByNum(num);
	}
}
