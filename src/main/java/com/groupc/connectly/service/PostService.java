package com.groupc.connectly.service;

import com.groupc.connectly.dto.PostDTO;
import com.groupc.connectly.dto.PostWithAuthorIdDTO;
import com.groupc.connectly.dto.PostWithAuthorNameDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    PostWithAuthorIdDTO getPostByID(Long id);
    List<PostWithAuthorIdDTO> getPosts();
    List<PostWithAuthorNameDTO> getFeedPosts(Long userId);
}
