package com.example.blogproject.payload;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String field;
    private String description;
    private String content;
}
