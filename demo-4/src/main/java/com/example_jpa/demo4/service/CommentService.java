package com.example_jpa.demo4.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example_jpa.demo4.model.Comment;

import com.example_jpa.demo4.repository.CommentRepository;


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