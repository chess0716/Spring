package com.example_jpa.demo5_th.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example_jpa.demo5_th.dto.BoardDTO;
import com.example_jpa.demo5_th.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper boardMapper;
  //추가
	public void insert(BoardDTO board) {
		boardMapper.insert(board);
	}
	//전체보기 ==> 페이징
	public List<BoardDTO>  pagelist(int pageStart,int pageSize){
		return boardMapper.pagelist(pageStart,pageSize);
	}
	//전체보기
	public List<BoardDTO> list(){
		return boardMapper.list();
	}
	//상세보기
	public BoardDTO view(int num) {
		return boardMapper.view(num);
	}
	//개수
	public int getCount() {
		return boardMapper.getCount();
	}
}
