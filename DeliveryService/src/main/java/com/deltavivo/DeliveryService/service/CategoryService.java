package com.deltavivo.DeliveryService.service;

import com.deltavivo.DeliveryService.domain.Category;
import com.deltavivo.DeliveryService.domain.CategoryDTO;
import com.deltavivo.DeliveryService.exception.CategoryNotFoundException;
import com.deltavivo.DeliveryService.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository){
        this.repository = categoryRepository;
    }

    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData){

        Category category = this.repository.findById(UUID.fromString(id))
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.repository.save(category);
        return category;
    }

    public void delete(String id){

        Category category = this.repository.findById(UUID.fromString(id))
                .orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);
    }
}
