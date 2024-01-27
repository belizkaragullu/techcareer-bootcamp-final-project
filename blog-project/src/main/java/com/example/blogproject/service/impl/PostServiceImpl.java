package com.example.blogproject.service.impl;

import com.example.blogproject.entity.Category;
import com.example.blogproject.entity.Post;
import com.example.blogproject.exception.ResourceNotFoundException;
import com.example.blogproject.payload.PostDto;
import com.example.blogproject.repository.CategoryRepository;
import com.example.blogproject.repository.PostRepository;
import com.example.blogproject.response.PostResponse;
import com.example.blogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    private PostDto mapToDto(Post post){
       PostDto postDto = modelMapper.map(post,PostDto.class);
      //  PostDto postDto = new PostDto();
      //  postDto.setContent(post.getContent());
      //  postDto.setDescription(post.getDescription());
      //  postDto.setTitle(post.getTitle());
      //  postDto.setId(post.getId());

        return postDto;
    }
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
     //   Post post = new Post();
     //   post.setId(postDto.getId());
     //   post.setContent(postDto.getContent());
     //   post.setTitle(postDto.getTitle());
     //   post.setDescription(postDto.getDescription());

        return post;
    }
    @Override
    public PostDto createPost(PostDto postDto) {

        Category category =categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id",postDto.getCategoryId()));

        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDto(newPost);

        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy ) {
        //pageable object
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page<Post> posts = postRepository.findAll(pageable);

        //to get content
        List<Post> listOfPost = posts.getContent();

        List<PostDto> content = listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content); //postDto list
        postResponse.setPageNumber(posts.getNumber()); //page method
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        Category category =categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id",postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {

        Category category =categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id",categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return  posts.stream().map((post -> mapToDto(post)))
                .collect(Collectors.toList());
    }
}
