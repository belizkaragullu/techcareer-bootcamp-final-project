package com.example.blogproject.service.impl;

import com.example.blogproject.entity.Comment;
import com.example.blogproject.entity.Post;
import com.example.blogproject.exception.BlogException;
import com.example.blogproject.exception.ResourceNotFoundException;
import com.example.blogproject.payload.CommentDto;
import com.example.blogproject.repository.CommentRepository;
import com.example.blogproject.repository.PostRepository;
import com.example.blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogException("This comment doesn't belong to this post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogException("This comment doesn't belong to this post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogException("This comment doesn't belong to this post");
        }
        commentRepository.delete(comment);
    }
}
