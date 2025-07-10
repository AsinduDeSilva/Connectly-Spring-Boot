package com.example.demo.service.impl;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.PostWithAuthorIdDTO;
import com.example.demo.dto.PostWithAuthorNameDTO;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, AuthService authService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO){
        Post post = modelMapper.map(postDTO, Post.class);
        post.setAuthor(authService.getLoggedUser());
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    public PostWithAuthorIdDTO getPostByID(Long id){
        return modelMapper.map((postRepository.findById(id)), PostWithAuthorIdDTO.class);
    }

    public List<PostWithAuthorIdDTO> getPosts(){
        return postRepository.findAll()
                .stream().map(post -> modelMapper.map(post, PostWithAuthorIdDTO.class))
                .collect(Collectors.toList());
    }

    public List<PostWithAuthorNameDTO> getFeedPosts(Long userId) {
        List<Post> posts = postRepository.findFeedPostsByUserId(userId);
        return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PostWithAuthorNameDTO convertToDTO(Post post) {
        return new PostWithAuthorNameDTO(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getAuthor().getFirstName()
        );
    }
}
