package com.villvay.blog_service.service.impl;

import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.model.Comment;
import com.villvay.blog_service.model.Post;
import com.villvay.blog_service.repository.CommentRepository;
import com.villvay.blog_service.repository.PostRepository;
import com.villvay.blog_service.service.CommentService;
import com.villvay.blog_service.util.exception.DataNotFoundException;
import com.villvay.blog_service.util.exception.ResourceFoundException;
import com.villvay.blog_service.util.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<CommentDto> getComments() {
        return commentRepository
                .findAll()
                .stream()
                .map(CommentMapper::comment)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getComment(int commentId) {
        return commentRepository
                .findById(commentId)
                .map(CommentMapper::comment)
                .orElseThrow(() -> new DataNotFoundException("Comment " + commentId + " is not found "));
    }

    @Override
    @Transactional
    public CommentDto addComment(CommentDto CommentDto) {
        Post post = postRepository
                .findById(CommentDto.getPostId())
                .orElseThrow(() -> new DataNotFoundException("Post " + CommentDto.getPostId() + " is not found "));

        if (commentRepository.findById(CommentDto.getId()).isPresent()) {
            throw new ResourceFoundException("Comment id " + CommentDto.getId() + " already exist");
        }
        Comment comment = CommentMapper.comment(CommentDto);
        comment.setPostId(post);
        return CommentMapper
                .comment(
                        commentRepository
                                .save(comment)
                );
    }

    @Override
    @Transactional
    public CommentDto updateComment(int commentId, CommentDto CommentDto) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("Comment " + commentId + " is not found "));

        CommentDto.setId(commentId);
        CommentMapper.comment(CommentDto, comment);
        return CommentMapper.comment(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("Comment " + commentId + " is not found "));
        commentRepository.delete(comment);
    }
}
