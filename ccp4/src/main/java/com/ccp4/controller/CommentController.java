package com.ccp4.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp4.dto.CommentDTO;
import com.ccp4.service.CommentService;




@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

   
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{bnum}")
    public void addComment(@PathVariable int bnum, @RequestBody CommentDTO comment) {
        comment.setBnum(bnum);
        commentService.addComment(comment);
    }


    @GetMapping("/{bnum}")
    public List<CommentDTO> getCommentsByBnum(@PathVariable int bnum) {
        return commentService.getCommentsByBnum(bnum);
    }

    @DeleteMapping("/{cnum}")
    public void deleteComment(@PathVariable int cnum) {
        commentService.deleteComment(cnum);
    }

    @GetMapping("/comment/{cnum}")
    public CommentDTO getCommentByCnum(@PathVariable int cnum) {
        return commentService.getCommentByCnum(cnum);
    }

    @GetMapping("/count/{bnum}")
    public int countCommentsByBnum(@PathVariable int bnum) {
        return commentService.countCommentsByBnum(bnum);
    }
}
