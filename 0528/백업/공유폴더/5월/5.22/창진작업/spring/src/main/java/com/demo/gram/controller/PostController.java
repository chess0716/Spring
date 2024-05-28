package com.demo.gram.controller;

import com.demo.gram.dto.PostDTO;
import com.demo.gram.entity.Post;
import com.demo.gram.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/list")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<List<PostDTO>> getAllPosts() {
    List<PostDTO> postDTOs = postService.findAllPosts();
    log.info("게시판리스트: " + postDTOs);
    return ResponseEntity.ok(postDTOs);
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
    PostDTO post = postService.findPostById(id);
    log.info("포스트 데이터(상세): " + post);
    return ResponseEntity.ok(post);
  }

  // MediaType 오류 해결하기 위한 옵션 consumes, produces 추가
  @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin(origins = "http://localhost:3000") // http://localhost:3000 요청 허용을 위해 추가
  public ResponseEntity<String> newPost(@RequestBody PostDTO postDto) {
    log.info("포스트 데이터(등록): " + postDto);
    postService.writePost(postDto);
    return new ResponseEntity<>("Successfully Register Post...", HttpStatus.OK);
  }

}