package com.deltavivo.DeliveryService.controller;

import com.deltavivo.DeliveryService.domain.Category;
import com.deltavivo.DeliveryService.domain.CategoryDTO;
import com.deltavivo.DeliveryService.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData){
        Category newCategory = this.service.insert(categoryData);
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/id")
    public ResponseEntity<Category> update(@RequestParam String id, @RequestBody CategoryDTO categoryData ){
        Category updatedCategory = this.service.update(id, categoryData);
        return ResponseEntity.ok().body(updatedCategory);

    }

    @DeleteMapping("/id")
    public ResponseEntity<Category> delete(@RequestParam String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
