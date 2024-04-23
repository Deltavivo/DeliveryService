package com.deltavivo.DeliveryService.repository;

import com.deltavivo.DeliveryService.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends MongoRepository<Category, UUID> {
}
