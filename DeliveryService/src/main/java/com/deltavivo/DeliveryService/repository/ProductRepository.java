package com.deltavivo.DeliveryService.repository;

import com.deltavivo.DeliveryService.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
