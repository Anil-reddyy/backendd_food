package com.food_deliver.restaurant_service.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Breakfast, Lunch, Dinner

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<ProductEntity> products;
}
