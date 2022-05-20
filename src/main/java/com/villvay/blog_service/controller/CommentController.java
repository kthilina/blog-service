package com.villvay.blog_service.controller;

import com.villvay.blog_service.dto.CommentDto;
import com.villvay.blog_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments() {
        return new ResponseEntity<>(commentService.getComments(), HttpStatus.OK);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable int commentId) {
        return new ResponseEntity<>(commentService.getComment(commentId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.addComment(commentDto), HttpStatus.CREATED);
    }

    @PutMapping("{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int commentId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(commentId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
