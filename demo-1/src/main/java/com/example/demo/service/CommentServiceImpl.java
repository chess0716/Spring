package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardMapper;
import com.example.demo.dao.CommentMapper;
import com.example.demo.dto.CommentDTO;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private CommentMapper commentMapper;

    // 댓글 추가
    @Override
    public void insert(CommentDTO comment) {
      
        boardMapper.replyCnt(comment.getBnum(), 1);
     
        commentMapper.insert(comment);
    }

    // 해당 게시물의 댓글 리스트 조회
    @Override
    public List<CommentDTO> getList(int bnum) {
        return commentMapper.list(bnum);
    }

    // 댓글 삭제
    @Override
    public void delete(int cnum) {
        // 삭제할 댓글의 정보를 조회합니다.
        CommentDTO comment = commentMapper.read(cnum);
        // 해당 게시물의 댓글 수를 1 감소시킵니다.
        boardMapper.replyCnt(comment.getBnum(), -1);
        // 댓글을 데이터베이스에서 삭제합니다.
        commentMapper.delete(cnum);
    }

    // 해당 게시물의 댓글 수 조회
    @Override
    public int getReplyCnt(int bnum) {
        // 게시물 번호를 기반으로 해당 게시물의 댓글 수를 조회합니다.
        return commentMapper.countByBnum(bnum);
    }

}
