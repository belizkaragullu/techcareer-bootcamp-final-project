package com.example.blogproject.service.impl;

import com.example.blogproject.payload.CommentDto;
import com.example.blogproject.repository.CommentRepository;
import com.example.blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


}
