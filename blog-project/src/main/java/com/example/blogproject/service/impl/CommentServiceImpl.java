package com.example.blogproject.service.impl;

import com.example.blogproject.entity.Comment;
import com.example.blogproject.payload.CommentDto;
import com.example.blogproject.repository.CommentRepository;
import com.example.blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setText(commentDto.getText());
        commentDto.setEmail(comment.getEmail());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setName(commentDto.getName());

        return comment;
    }

}
