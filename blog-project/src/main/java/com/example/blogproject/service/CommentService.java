package com.example.blogproject.service;

import com.example.blogproject.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

}
