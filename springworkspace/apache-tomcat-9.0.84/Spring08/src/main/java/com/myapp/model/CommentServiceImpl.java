package com.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.dto.CommentDTO;
import com.myapp.mapper.BoardMapper;
import com.myapp.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
 @Autowired
	private BoardMapper boardMapper;
  @Autowired
  private CommentMapper commentMapper;
  //추가
   @Transactional
	@Override
	public void insert(CommentDTO comment) {
		boardMapper.replyCnt(comment.getBnum(), 1);  //replyCnt 증가
		commentMapper.insert(comment);
		
	}
  //전체보기
	@Override
	public List<CommentDTO> getList(int bnum) {
		return commentMapper.list(bnum);
	}
  //삭제
	@Override
	@Transactional
	public void delete(int cnum) {
		CommentDTO comment = commentMapper.read(cnum);
		boardMapper.replyCnt(comment.getBnum(), -1);
		commentMapper.delete(cnum);
	}

	@Override
	public int getReplyCnt(int bnum) {
		// TODO Auto-generated method stub
		return 0;
	}

}
