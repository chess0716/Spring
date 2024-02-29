package com.ccp5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp5.dto.Comment;
import com.ccp5.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	 @Autowired
    private CommentService commentService;

   
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }
}
