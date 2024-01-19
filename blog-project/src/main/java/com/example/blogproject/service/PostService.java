package com.example.blogproject.service;

import com.example.blogproject.entity.Post;
import com.example.blogproject.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
