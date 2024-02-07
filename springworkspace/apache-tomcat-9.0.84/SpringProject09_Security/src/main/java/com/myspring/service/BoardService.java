package com.myspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.dto.BoardDTO;
import com.myspring.mapper.BoardMapper;

@Service
public class BoardService  {
    
    @Autowired
    private BoardMapper boardMapper;
    
    public void insert(BoardDTO board) {
        boardMapper.insert(board);
    }
    public List<BoardDTO> list() {
      
        return boardMapper.list();
    }
    public int count() {
    	return boardMapper.count();
    }
	
	public BoardDTO view(int num) {
		
		return boardMapper.view(num);
	}
	public void update(BoardDTO board) {
        boardMapper.update(board);
    }

    public void delete(int num) {
        boardMapper.delete(num);
    }
}
