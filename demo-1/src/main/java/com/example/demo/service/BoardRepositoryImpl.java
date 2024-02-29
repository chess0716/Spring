package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.BoardMapper;
import com.example.demo.dto.BoardDTO;



@Repository
public class BoardRepositoryImpl implements BoardRepository {
 @Autowired
 	private BoardMapper boardMapper;

	@Override
	public void dao_insert(BoardDTO board) {
		boardMapper.insert(board);
		
	}
	
	@Override
	public List<BoardDTO> dao_list(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.list(hm);
	}
	
	@Override
	public BoardDTO dao_view(int num) {
		
		boardMapper.updateHitCount(num);
		return boardMapper.view(num);
	}
	
	@Override
	public void dao_update(BoardDTO board) {
		
		boardMapper.update(board);
		
	}
	
	@Override
	public void dao_delete(int num) {
		boardMapper.delete(num);
		
	}
	
	@Override
	public int getCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.count(hm);
	}
		


	

}
