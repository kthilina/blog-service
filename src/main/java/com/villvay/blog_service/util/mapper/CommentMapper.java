package com.villvay.blog_service.util.mapper;

import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.model.Comment;
import org.springframework.beans.BeanUtils;

public class CommentMapper {
    public static CommentDto comment(Comment comment) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(comment, commentDto);
        commentDto.setPostId(comment.getPostId().getId());
        return commentDto;
    }

    public static Comment comment(CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        return comment;
    }

    public static void comment(CommentDto commentDto, Comment comment) {
        BeanUtils.copyProperties(commentDto, comment);
    }
}
