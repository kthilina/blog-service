package com.villvay.blog_service.repository;

import com.villvay.blog_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByIdAndAuthorId_Id(Integer id, Integer authorId);
}
