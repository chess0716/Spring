package com.ccp4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccp4.dto.RequestBoard;
import com.ccp4.mapper.RequestBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestBoardSevice {
	private final RequestBoardMapper rbMapper;
	
	// 요청 게시글 추가
	public void insert(RequestBoard rboard) {
		rbMapper.insert(rboard);
	}
	
	// 요청 게시글 목록 불러오기
	public List<RequestBoard> list(){
		return rbMapper.list();
	}
}
