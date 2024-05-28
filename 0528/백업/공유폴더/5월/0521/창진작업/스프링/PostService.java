package com.demo.gram.service;

import com.demo.gram.dto.PostDTO;
import com.demo.gram.entity.Post;
import com.demo.gram.formatter.DateConverter;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostService {
  List<Post> findAllPosts();
  Optional<Post> findPostById(Long id);
  void writePost(PostDTO postDTO);


  default Post dtoToEntity(PostDTO postDTO) {
    Post post = Post.builder()
        .id(postDTO.getId())
        .title(postDTO.getTitle())
        .content(postDTO.getContent())
        .endDate(LocalDate.parse(postDTO.getEndDate()))
        .numberOfUsers(postDTO.getNumberOfUsers())
//        .user(postDTO.getUser())
        .build();
    return post;
  }

  default PostDTO entityToDTO(Post post) {
    PostDTO postDTO = PostDTO.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .regdate(post.getRegDate().toString())
        .endDate(post.getEndDate().toString())

        .build();
    return postDTO;
  }

}
