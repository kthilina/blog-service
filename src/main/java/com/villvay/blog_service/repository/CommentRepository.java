package com.villvay.blog_service.repository;

import com.villvay.blog_service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
