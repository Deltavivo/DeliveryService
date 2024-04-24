package com.deltavivo.DeliveryService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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
    private String category;

    public Product(ProductDTO data){
        this.title = data.title();
        this.description = data.description();
        this.ownerId = data.ownerId();
        this.price = data.price();
        this.category = data.categoryId();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("title",this.title);
        json.put("description",this.description);
        json.put("ownerId",this.ownerId);
        json.put("price",this.price);
        json.put("category",this.category);
        json.put("type","product");
        return json.toString();

    }
}
