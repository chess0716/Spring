package com.ccp5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccp5.dto.Comment;
import com.ccp5.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardService boardService;

    public void insert(Comment comment) {
        commentRepository.save(comment);
        boardService.updateReplyCnt(comment.getBoard().getNum());
        
    }

    public List<Comment> list(long bnum) {
        return commentRepository.findByBnum(bnum);
    }

    public void delete(long cnum) {
        commentRepository.deleteById(cnum);
    }
   
}