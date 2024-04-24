package com.deltavivo.DeliveryService.service;

import com.deltavivo.DeliveryService.domain.Category;
import com.deltavivo.DeliveryService.domain.CategoryDTO;
import com.deltavivo.DeliveryService.domain.MessageDTO;
import com.deltavivo.DeliveryService.exception.CategoryNotFoundException;
import com.deltavivo.DeliveryService.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    
    private final AwsSnsService snsService;

    public CategoryService(CategoryRepository categoryRepository, AwsSnsService snsService){
        this.repository = categoryRepository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO data){
        Category category = new Category(data);
        this.repository.save(category);

        this.snsService.publish(new MessageDTO(category.toString()));
        return category;
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(UUID.fromString(id));
    }

    public Category update(String id, CategoryDTO data){

        Category category = this.repository.findById(UUID.fromString(id))
                .orElseThrow(CategoryNotFoundException::new);

        if(!data.title().isEmpty()) category.setTitle(data.title());
        if(!data.description().isEmpty()) category.setDescription(data.description());

        this.snsService.publish(new MessageDTO(data.toString()));
        this.repository.save(category);
        return category;
    }

    public void delete(String id){

        Category category = this.repository.findById(UUID.fromString(id))
                .orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);
    }
}
