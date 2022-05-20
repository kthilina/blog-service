package com.villvay.blog_service.util.mapper;

import com.villvay.blog_service.dto.AuthorDto;
import com.villvay.blog_service.model.Author;
import org.springframework.beans.BeanUtils;

public class AuthorMapper {
    public static AuthorDto author(Author author) {
        AuthorDto authorDto = new AuthorDto();
        BeanUtils.copyProperties(author, authorDto);
        return authorDto;
    }

    public static Author author(AuthorDto AuthorDto) {
        Author author = new Author();
        BeanUtils.copyProperties(AuthorDto, author);
        return author;
    }

    public static void author(AuthorDto AuthorDto, Author author) {
        BeanUtils.copyProperties(AuthorDto, author);
    }
}
