package com.deltavivo.DeliveryService.service;

import com.deltavivo.DeliveryService.domain.*;
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

    private final AwsSnsService snsService;

    public ProductService(CategoryService categoryService, ProductRepository productRepository, AwsSnsService snsService) {
        this.categoryService = categoryService;
        this.repository = productRepository;
        this.snsService = snsService;
    }

    public Product insert(ProductDTO productData){
        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product product = new Product(productData);
        this.repository.save(product);

        this.snsService.publish(new MessageDTO(product.toString()));
        return product;
    }

    public List<Product> getAll(){
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO data){

        Product product = this.repository.findById(UUID.fromString(id))
                    .orElseThrow(ProductNotFoundException::new);

        if(data.categoryId()!=null) {
            this.categoryService.getById(data.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

            product.setCategory(data.categoryId());
        }

        if(!data.title().isEmpty()) product.setTitle(data.title());
        if(!data.description().isEmpty()) product.setDescription(data.description());
        if(!(data.price() == null)) product.setPrice(data.price());

        this.repository.save(product);

        this.snsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public void delete(String id){

        Product product = this.repository.findById(UUID.fromString(id))
                .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);
    }
}
