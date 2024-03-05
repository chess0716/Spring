package com.ccp5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccp5.dto.RequestBoard;
import com.ccp5.repository.RequestBoardRepository;

@Service

public class RequestBoardSevice {
	@Autowired
	private  RequestBoardRepository rbRepo;
	
	// 요청 게시글 추가
	public void insert(RequestBoard rboard) {
		rbRepo.save(rboard);
	}
	
	// 요청 게시글 목록 불러오기
	public List<RequestBoard> list(){
		return rbRepo.findAll();
	}
}
