package com.villvay.blog_service.repository;

import com.villvay.blog_service.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
