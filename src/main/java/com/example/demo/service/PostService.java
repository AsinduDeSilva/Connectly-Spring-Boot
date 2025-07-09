package com.example.demo.service;

import com.example.demo.dto.PostWithAuthorIdDTO;
import com.example.demo.dto.PostWithAuthorNameDTO;

import java.util.List;

public interface PostService {
    PostWithAuthorIdDTO createPost(PostWithAuthorIdDTO postWithAuthorIdDTO);
    PostWithAuthorIdDTO getPostByID(Long id);
    List<PostWithAuthorIdDTO> getPosts();
    List<PostWithAuthorNameDTO> getFeedPosts(Long userId);
}
