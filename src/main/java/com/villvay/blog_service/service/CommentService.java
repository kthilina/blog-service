package com.villvay.blog_service.service;

import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.dto.PostDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getComments();

    CommentDto getComment(int commentId);

    CommentDto addComment(CommentDto CommentDto);

    CommentDto updateComment(int commentId, CommentDto CommentDto);

    void deleteComment(int commentId);
}
