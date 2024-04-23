package com.deltavivo.DeliveryService.controller;

import com.deltavivo.DeliveryService.domain.Category;
import com.deltavivo.DeliveryService.domain.CategoryDTO;
import com.deltavivo.DeliveryService.domain.Product;
import com.deltavivo.DeliveryService.domain.ProductDTO;
import com.deltavivo.DeliveryService.service.CategoryService;
import com.deltavivo.DeliveryService.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData){
        Product newProduct = this.service.insert(productData);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> producties = this.service.getAll();
        return ResponseEntity.ok().body(producties);
    }

    @PutMapping("/id")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody ProductDTO productData ){
        Product updatedProduct = this.service.update(id, productData);
        return ResponseEntity.ok().body(updatedProduct);

    }

    @DeleteMapping("/id")
    public ResponseEntity<Category> delete(@PathVariable String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
