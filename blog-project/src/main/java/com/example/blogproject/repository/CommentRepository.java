package com.example.blogproject.repository;

import com.example.blogproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//no need to specify repository annotation
    List<Comment> findByPostId(Long id);
}
