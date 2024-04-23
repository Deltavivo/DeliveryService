package com.deltavivo.DeliveryService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private UUID id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private Category category;

    public Product(ProductDTO productDTO){
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.ownerId = productDTO.ownerId();
        this.price = productDTO.price();
    }
}
