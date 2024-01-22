package com.example.blogproject.payload;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String text;
    //no post, only comment details
}
