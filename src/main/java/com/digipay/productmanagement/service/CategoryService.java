package com.digipay.productmanagement.service;

import com.digipay.productmanagement.model.dto.CategoryDto;
import com.digipay.productmanagement.model.entity.Category;
import com.digipay.productmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService implements CrudService<CategoryDto, Long>{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static CategoryDto convertEntityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    public static Category convertDtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    @Override
    public Set<CategoryDto> findAll() {
        Set<CategoryDto> categoriesDto = new HashSet<>();
        for (Category category : categoryRepository.findAll()) {
            categoriesDto.add(convertEntityToDto(category));
        }
        return categoriesDto;
    }

    @Override
    public CategoryDto findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(CategoryService::convertEntityToDto).orElse(null);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        categoryRepository.save(convertDtoToEntity(categoryDto));
        return categoryDto;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setTitle(categoryDto.getTitle());
            category.get().setDescription(categoryDto.getDescription());
            categoryRepository.save(category.get());

            categoryDto.setId(id);
            return categoryDto;
        } else return null;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
