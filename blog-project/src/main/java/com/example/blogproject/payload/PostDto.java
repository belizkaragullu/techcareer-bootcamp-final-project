package com.example.blogproject.payload;

import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String description;
    private String content;
    private String title;
    private Set<CommentDto> comments;
    private Long categoryId;
}
