package com.example.demo.service.impl;

import com.example.demo.dto.PostWithAuthorIdDTO;
import com.example.demo.dto.PostWithAuthorNameDTO;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public PostWithAuthorIdDTO createPost(PostWithAuthorIdDTO postWithAuthorIdDTO){
        Post post = modelMapper.map(postWithAuthorIdDTO, Post.class);
        post.setAuthor(userRepository.findById(postWithAuthorIdDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found")));
        postRepository.save(post);
        return postWithAuthorIdDTO;
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
