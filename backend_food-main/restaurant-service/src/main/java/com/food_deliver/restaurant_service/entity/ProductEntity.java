package com.food_deliver.restaurant_service.entity;

import com.food_deliver.restaurant_service.Enum.FoodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String category;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodType foodType;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;
}