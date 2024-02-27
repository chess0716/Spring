package com.ccp5.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccp5.dto.Board;
import com.ccp5.dto.Comment;
import com.ccp5.dto.User;
import com.ccp5.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)  // Transaction 을 수동으로 처리
@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	

	public Page<Board> findAll(String field, String word, Pageable pageable){
		Page<Board> lists = boardRepository.findAll(pageable);
		if(field.equals("title")) {
			lists = boardRepository.findByTitleContaining(word, pageable);
		}else if(field.equals("content")) {
			lists=boardRepository.findByContentContaining(word, pageable);
		}
		return lists;
		
	}
	
	//전체보기(페이징)
	public Page<Board> findAll(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	//전체보기(기본)
	public List<Board> list(){
		return boardRepository.findAll();
	}
	//개수(검색)
	public long count(String field, String word) {
		long count = boardRepository.count();
		if(field.equals("title")) {
			count = boardRepository.cntTitleSearch(word);
		}else if(field.equals("content")) {
			count=boardRepository.cntContentSearch(word);
		}
		
		return count;
		
	}
	//개수
	public long count() {
		return boardRepository.count();
	}
	//상세보기
	@Transactional
	public Board view(long num) {
		//조회수 증가
		Board board = boardRepository.findById(num).get();
		board.setHitcount(board.getHitcount()+1);
		return board ;
	}
	//삭제
	@Transactional
	public void delete(long num) {
		boardRepository.deleteById(num);
	}
	//수정 ==>더티체킹
	@Transactional
	public void update(Board board) {
		Board b = boardRepository.findById(board.getNum()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		b.setRegdate(new Date());
		
	}
	
	@Transactional
    public void updateReplyCnt(Long boardNum) {
        Board board = boardRepository.findById(boardNum).orElse(null);
        if (board != null) {
            List<Comment> comments = board.getComments();
            int replyCnt = comments != null ? comments.size() : 0;
            board.setReplyCnt(replyCnt);
            boardRepository.save(board);
        }
    }


}
