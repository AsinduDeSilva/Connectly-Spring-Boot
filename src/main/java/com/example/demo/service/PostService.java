package com.example.demo.service;

import com.example.demo.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostByID(Long id);
    List<PostDTO> getPosts();
}
