package com.example.blogproject.repository;

import com.example.blogproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//no need to specify repository annotation
}
