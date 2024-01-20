package com.example.blogproject.service;

import com.example.blogproject.entity.Post;
import com.example.blogproject.payload.PostDto;
import com.example.blogproject.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNumber, int pageSize);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, Long id);
    void deletePostById(Long id);
}
