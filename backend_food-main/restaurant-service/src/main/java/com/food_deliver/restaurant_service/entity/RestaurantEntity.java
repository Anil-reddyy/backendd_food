package com.food_deliver.restaurant_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
    private String name;
    private String location;
    private String description;
    private boolean status = true;
    private String timing;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuEntity> menus;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}