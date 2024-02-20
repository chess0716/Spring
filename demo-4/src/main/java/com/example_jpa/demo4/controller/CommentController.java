package com.example_jpa.demo4.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example_jpa.demo4.service.BoardService;

import com.example_jpa.demo4.config.auth.PrincipalUser;
import com.example_jpa.demo4.model.Board;
import com.example_jpa.demo4.model.Comment;
import com.example_jpa.demo4.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    @GetMapping("commentList/{num}")
    public ResponseEntity<List<Comment>> list(@PathVariable long num) {
        List<Comment> comments = commentService.list(num);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("commentInsert/{num}")
    public ResponseEntity<String> insert(
            @PathVariable long num,
            @RequestBody Comment comment,
            @AuthenticationPrincipal PrincipalUser principal) {
        Board b = new Board();
        b.setNum(num);
        comment.setBoard(b);
        comment.setUser(principal.getUser());
        commentService.insert(comment);
        
        boardService.updateReplyCnt(num);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
  
    @DeleteMapping("delete/{cnum}")
    public long delete(@PathVariable long cnum) {
        commentService.delete(cnum);
        return cnum;
    }
}
