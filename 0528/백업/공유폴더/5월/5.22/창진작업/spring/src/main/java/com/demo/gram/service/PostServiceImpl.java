package com.demo.gram.service;


import com.demo.gram.dto.PostDTO;
import com.demo.gram.entity.Post;
import com.demo.gram.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public List<PostDTO> findAllPosts() {
    List<Post> posts = postRepository.findAll();
    if (posts == null || posts.isEmpty()) {
      return List.of(); // 빈 리스트 반환
    }
    return posts.stream().map(this::entityToDTO).collect(Collectors.toList());
  }

  @Override
  public PostDTO findPostById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }
    Optional<Post> postOptional = postRepository.findById(id);
    if (postOptional.isEmpty()) {
      throw new EntityNotFoundException("Post with ID " + id + " not found");
    }
    return entityToDTO(postOptional.get());
  }

  @Override
  public void writePost(PostDTO postDTO) {
    if (postDTO == null) {
      throw new IllegalArgumentException("PostDTO cannot be null");
    }
    postRepository.save(dtoToEntity(postDTO));

  }
}
