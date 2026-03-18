package org.example.examenud8y9.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@Data
@NoArgsConstructor
public class Item {
    @Id
    private String id;
    private String title;
    private Integer price;
    private String description;
    private String category;
    private String image;
    private Double rate;
    private Integer count;
    private String color;
    private String EAN;
    private String manufacturer;
}