package com.food_deliver.restaurant_service.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private String description;
    private Double price;
    private String category;
    private Long menuId;
    private String foodType;
}
