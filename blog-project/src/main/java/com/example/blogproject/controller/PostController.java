package com.example.blogproject.controller;

import com.example.blogproject.payload.PostDto;
import com.example.blogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    //interface to maintain loosecoupling
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(value = "pageSize", defaultValue ="10", required = false) int pageSize){
        return ResponseEntity.ok(postService.getAllPosts(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable(name= "id") Long id){
        PostDto postResponse = postService.updatePost(postDto,id);

        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name ="id") Long id){
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
