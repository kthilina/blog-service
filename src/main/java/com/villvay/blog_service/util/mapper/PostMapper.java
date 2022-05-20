package com.villvay.blog_service.util.mapper;

import com.villvay.blog_service.dto.PostDto;
import com.villvay.blog_service.model.Post;
import org.springframework.beans.BeanUtils;

public class PostMapper {
    public static PostDto post(Post post) {
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);
        postDto.setAuthorId(post.getAuthorId().getId());
        return postDto;
    }

    public static Post post(PostDto postDto) {
        Post post = new Post();
        BeanUtils.copyProperties(postDto, post);
        return post;
    }

    public static void post(PostDto postDto, Post post) {
        BeanUtils.copyProperties(postDto, post);
    }
}
