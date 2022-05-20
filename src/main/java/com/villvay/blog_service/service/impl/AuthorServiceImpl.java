package com.villvay.blog_service.service.impl;

import com.villvay.blog_service.dto.AuthorDto;
import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.dto.PostDto;
import com.villvay.blog_service.model.Author;
import com.villvay.blog_service.repository.AuthorRepository;
import com.villvay.blog_service.repository.PostRepository;
import com.villvay.blog_service.service.AuthorService;
import com.villvay.blog_service.util.exception.DataNotFoundException;
import com.villvay.blog_service.util.exception.ResourceFoundException;
import com.villvay.blog_service.util.mapper.AuthorMapper;
import com.villvay.blog_service.util.mapper.CommentMapper;
import com.villvay.blog_service.util.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<AuthorDto> getAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(AuthorMapper::author)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthor(int authorId) {
        return authorRepository
                .findById(authorId)
                .map(AuthorMapper::author)
                .orElseThrow(() -> new DataNotFoundException("author " + authorId + " is not found "));
    }

    @Override
    public List<PostDto> getPosts(int authorId) {
        return authorRepository
                .findById(authorId)
                .orElseThrow(() -> new DataNotFoundException("author " + authorId + " is not found "))
                .getPostList()
                .stream()
                .map(PostMapper::post)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getComments(int authorId, int postId) {
        return postRepository
                .findByIdAndAuthorId_Id(postId, authorId)
                .orElseThrow(() -> new DataNotFoundException("post " + postId + " is not found for author " + authorId))
                .getCommentList()
                .stream()
                .map(CommentMapper::comment)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthorDto addAuthor(AuthorDto authorDto) {
        if (authorRepository.findById(authorDto.getId()).isPresent()) {
            throw new ResourceFoundException("Author id " + authorDto.getId() + " already exist");
        }
        return AuthorMapper
                .author(
                        authorRepository
                                .save(AuthorMapper.author(authorDto))
                );
    }

    @Override
    @Transactional
    public AuthorDto updateAuthor(int authorId, AuthorDto authorDto) {
        Author author = authorRepository
                .findById(authorId)
                .orElseThrow(() -> new DataNotFoundException("author " + authorId + " is not found "));

        authorDto.setId(authorId);
        AuthorMapper.author(authorDto, author);
        return AuthorMapper.author(authorRepository.save(author));
    }

    @Override
    @Transactional
    public void deleteAuthor(int authorId) {
        Author author = authorRepository
                .findById(authorId)
                .orElseThrow(() -> new DataNotFoundException("author " + authorId + " is not found "));
        authorRepository.delete(author);
    }
}
