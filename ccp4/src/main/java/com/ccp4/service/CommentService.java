package com.ccp4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccp4.dto.CommentDTO;
import com.ccp4.mapper.CommentMapper;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

   
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Transactional
    public void addComment(CommentDTO comment) {
        commentMapper.insertComment(comment);
    }

    public List<CommentDTO> getCommentsByBnum(int bnum) {
        return commentMapper.getCommentsByBnum(bnum);
    }

    @Transactional
    public void deleteComment(int cnum) {
        commentMapper.deleteComment(cnum);
    }

    public CommentDTO getCommentByCnum(int cnum) {
        return commentMapper.getCommentByCnum(cnum);
    }

    public int countCommentsByBnum(int bnum) {
        return commentMapper.countCommentsByBnum(bnum);
    }
}
