package com.villvay.blog_service.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String address;
}
