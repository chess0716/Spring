package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.dto.BoardDTO;



public interface BoardRepository {
	   //추가
		public void dao_insert(BoardDTO board);
		//전체보기
		public List<BoardDTO> dao_list(HashMap<String, Object> hm);
		//상세보기
		public BoardDTO dao_view(int num);
		//수정
		public void dao_update(BoardDTO board);
		//삭제
		public void dao_delete(int num);
		//개수
		public int getCount(HashMap<String, Object> hm);
	
}







