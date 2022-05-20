package com.villvay.blog_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Integer id;
    private String name;
    private String email;
    private String body;
    private Date createdOn;
    private Date modifiedOn;
    private Integer postId;
}
