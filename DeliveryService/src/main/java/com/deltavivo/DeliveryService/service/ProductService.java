package com.deltavivo.DeliveryService.service;

import com.deltavivo.DeliveryService.domain.Category;
import com.deltavivo.DeliveryService.domain.CategoryDTO;
import com.deltavivo.DeliveryService.domain.Product;
import com.deltavivo.DeliveryService.domain.ProductDTO;
import com.deltavivo.DeliveryService.exception.CategoryNotFoundException;
import com.deltavivo.DeliveryService.exception.ProductNotFoundException;
import com.deltavivo.DeliveryService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final CategoryService categoryService;

    private final ProductRepository repository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.repository = productRepository;
    }

    public Product insert(ProductDTO productData){
        Category category = this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productData);
        this.repository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAll(){
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO productData){

        Product product = this.repository.findById(UUID.fromString(id))
                .orElseThrow(ProductNotFoundException::new);

        this.categoryService.getById(productData.categoryId())
                .ifPresent(product::setCategory);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());

        this.repository.save(product);
        return product;
    }

    public void delete(String id){

        Product product = this.repository.findById(UUID.fromString(id))
                .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);
    }
}
