package com.myapp.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.dto.BoardDTO;
import com.myapp.dto.FileBoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
 @Autowired
 private BoardRepository boardRepository;
	@Override
	public void insert(BoardDTO board) {
		boardRepository.dao_insert(board);
		
	}

	@Override
	public List<BoardDTO> findAll(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardRepository.dao_findAll(hm);
	}

	//상세보기
	@Override
	public BoardDTO findByNum(int num) {
		boardRepository.dao_upReadCount(num);  //조회수 증가
		return boardRepository.dao_findByNum(num);
	}

	@Override
	public void update(BoardDTO board) {
		boardRepository.dao_update(board);
		
	}

	@Override
	public void delete(int num) {
	 boardRepository.dao_delete(num);
		
	}

	@Override
	public int getCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardRepository.getCount(hm);
	}

	@Override
	public void fileInsert(FileBoardDTO fboard) {
		boardRepository.dao_fileInsert(fboard);
		
	}

	@Override
	public List<FileBoardDTO> fileList() {
		// TODO Auto-generated method stub
		return boardRepository.dao_fileList();
	}

}
