package com.example.blogproject.controller;

import com.example.blogproject.payload.CommentDto;
import com.example.blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value= "postId") Long postId,
                                                    @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }


}
