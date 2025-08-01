package com.groupc.connectly.controller;

import com.groupc.connectly.dto.CRUDResponseDTO;
import com.groupc.connectly.dto.PostDTO;
import com.groupc.connectly.dto.PostWithAuthorIdDTO;
import com.groupc.connectly.dto.PostWithAuthorNameDTO;
import com.groupc.connectly.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<CRUDResponseDTO<PostDTO>> createPost(@RequestBody PostDTO postDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CRUDResponseDTO<>(true, "Post Created",  postService.createPost(postDTO)));
    }

    @GetMapping("/{id}")
    public PostWithAuthorIdDTO getPostById(@PathVariable Long id){
        return postService.getPostByID(id);
    }

    @GetMapping("/all")
    public List<PostWithAuthorIdDTO> getAllPosts(){
        return postService.getPosts();
    }

    @GetMapping("/feed/{userId}")
    public List<PostWithAuthorNameDTO> getFeed(@PathVariable Long userId) {
        return postService.getFeedPosts(userId);
    }
}
