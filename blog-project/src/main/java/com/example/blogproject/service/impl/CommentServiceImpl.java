package com.example.blogproject.service.impl;

import com.example.blogproject.entity.Comment;
import com.example.blogproject.entity.Post;
import com.example.blogproject.exception.ResourceNotFoundException;
import com.example.blogproject.payload.CommentDto;
import com.example.blogproject.repository.CommentRepository;
import com.example.blogproject.repository.PostRepository;
import com.example.blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setText(comment.getText());
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

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));

        comment.setPost(post);

        Comment responseComment = commentRepository.save(comment);

        return mapToDto(responseComment);


    }
}
