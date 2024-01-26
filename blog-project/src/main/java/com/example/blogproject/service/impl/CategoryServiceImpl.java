package com.example.blogproject.service.impl;

import com.example.blogproject.entity.Category;
import com.example.blogproject.exception.ResourceNotFoundException;
import com.example.blogproject.payload.CategoryDto;
import com.example.blogproject.repository.CategoryRepository;
import com.example.blogproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category =modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id" , categoryId));
        return modelMapper.map(category, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map((category -> modelMapper.map(category,CategoryDto.class)))
                .collect(Collectors.toList());
    }
}
