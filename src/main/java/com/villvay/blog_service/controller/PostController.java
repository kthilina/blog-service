package com.villvay.blog_service.controller;

import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.dto.PostDto;
import com.villvay.blog_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable int postId) {
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable int postId) {
        return new ResponseEntity<>(postService.getComments(postId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.addPost(postDto), HttpStatus.CREATED);
    }

    @PutMapping("{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable int postId, @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(postId, postDto), HttpStatus.OK);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
