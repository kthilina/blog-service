package com.villvay.blog_service.service;

import com.villvay.blog_service.dto.AuthorDto;
import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();

    PostDto getPost(int postId);

    List<CommentDto> getComments(int postId);

    PostDto addPost(PostDto postDto);

    PostDto updatePost(int postId, PostDto postDto);

    void deletePost(int postId);
}
