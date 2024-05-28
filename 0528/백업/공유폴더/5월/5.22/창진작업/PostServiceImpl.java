package com.demo.gram.service;


import com.demo.gram.dto.PostDTO;
import com.demo.gram.entity.Post;
import com.demo.gram.repository.PostRepository;
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
    return postRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
  }

  @Override
  public Optional<Post> findPostById(Long id) {
    return postRepository.findById(id);
  }

  @Override
  public void writePost(PostDTO postDTO) {
    postRepository.save(dtoToEntity(postDTO));

  }
}
