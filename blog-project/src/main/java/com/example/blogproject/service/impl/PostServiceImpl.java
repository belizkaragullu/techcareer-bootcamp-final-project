package com.example.blogproject.service.impl;

import com.example.blogproject.entity.Post;
import com.example.blogproject.payload.PostDto;
import com.example.blogproject.repository.PostRepository;
import com.example.blogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        postDto.setId(post.getId());

        return postDto;
    }
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDto(post);

        return postResponse;
    }


}
