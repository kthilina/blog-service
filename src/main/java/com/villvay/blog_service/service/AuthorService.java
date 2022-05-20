package com.villvay.blog_service.service;

import com.villvay.blog_service.dto.AuthorDto;
import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.dto.PostDto;

import java.util.List;

public interface AuthorService {
    /**
     * Get all authors
     *
     * @return
     */
    List<AuthorDto> getAuthors();

    /**
     * Get single author according to the requested author id
     *
     * @param authorId
     * @return
     */
    AuthorDto getAuthor(int authorId);

    /**
     * Get posts which are posted by author who has author id requested
     *
     * @param authorId
     * @return
     */
    List<PostDto> getPosts(int authorId);

    /**
     * Get comments which are related to the post id requested and posted by author who has author id requested
     *
     * @param authorId
     * @param postId
     * @return
     */
    List<CommentDto> getComments(int authorId, int postId);

    /**
     * Add new author
     *
     * @param authorDto
     * @return
     */
    AuthorDto addAuthor(AuthorDto authorDto);

    /**
     * Update existing author
     *
     * @param authorId
     * @param authorDto
     * @return
     */
    AuthorDto updateAuthor(int authorId, AuthorDto authorDto);

    /**
     * Delete specific author according to the author id requested.
     *
     * @param authorId
     */
    void deleteAuthor(int authorId);
}
