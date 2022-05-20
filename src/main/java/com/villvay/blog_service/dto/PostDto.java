package com.villvay.blog_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Integer id;
    private String title;
    private String body;
    private Date createdOn;
    private Date modifiedOn;
    private Integer authorId;
}
