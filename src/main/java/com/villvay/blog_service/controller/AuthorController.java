package com.villvay.blog_service.controller;

import com.villvay.blog_service.dto.AuthorDto;
import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.dto.PostDto;
import com.villvay.blog_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable int authorId) {
        return new ResponseEntity<>(authorService.getAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping("{authorId}/posts")
    public ResponseEntity<List<PostDto>> getPosts(@PathVariable int authorId) {
        return new ResponseEntity<>(authorService.getPosts(authorId), HttpStatus.OK);
    }

    @GetMapping("{authorId}/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable int authorId, @PathVariable int postId) {
        return new ResponseEntity<>(authorService.getComments(authorId, postId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.addAuthor(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("{authorId}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable int authorId, @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updateAuthor(authorId, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity deleteAuthor(@PathVariable int authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
