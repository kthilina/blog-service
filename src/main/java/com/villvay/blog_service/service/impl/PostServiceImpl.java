package com.villvay.blog_service.service.impl;

import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.dto.PostDto;
import com.villvay.blog_service.model.Author;
import com.villvay.blog_service.model.Post;
import com.villvay.blog_service.repository.AuthorRepository;
import com.villvay.blog_service.repository.CommentRepository;
import com.villvay.blog_service.repository.PostRepository;
import com.villvay.blog_service.service.PostService;
import com.villvay.blog_service.util.exception.DataNotFoundException;
import com.villvay.blog_service.util.exception.ResourceFoundException;
import com.villvay.blog_service.util.mapper.CommentMapper;
import com.villvay.blog_service.util.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<PostDto> getPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(PostMapper::post)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(int postId) {
        return postRepository
                .findById(postId)
                .map(PostMapper::post)
                .orElseThrow(() -> new DataNotFoundException("Post " + postId + " is not found "));
    }

    @Override
    public List<CommentDto> getComments(int postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new DataNotFoundException("Post " + postId + " is not found "))
                .getCommentList()
                .stream()
                .map(CommentMapper::comment)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostDto addPost(PostDto postDto) {
        Author author = authorRepository
                .findById(postDto.getAuthorId())
                .orElseThrow(() -> new DataNotFoundException("Author " + postDto.getAuthorId() + " is not found "));

        if (postRepository.findById(postDto.getId()).isPresent()) {
            throw new ResourceFoundException("Post id " + postDto.getId() + " already exist");
        }
        Post post = PostMapper.post(postDto);
        post.setAuthorId(author);
        return PostMapper
                .post(
                        postRepository
                                .save(post)
                );
    }

    @Override
    @Transactional
    public PostDto updatePost(int postId, PostDto postDto) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new DataNotFoundException("Post " + postId + " is not found "));

        postDto.setId(postId);
        PostMapper.post(postDto, post);
        return PostMapper.post(postRepository.save(post));
    }

    @Override
    @Transactional
    public void deletePost(int postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new DataNotFoundException("Post " + postId + " is not found "));
        post.getCommentList().stream().forEach(comment -> commentRepository.delete(comment));
        postRepository.delete(post);
    }
}
