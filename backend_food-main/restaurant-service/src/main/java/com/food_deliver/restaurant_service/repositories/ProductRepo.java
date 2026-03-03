package com.food_deliver.restaurant_service.repositories;

import com.food_deliver.restaurant_service.entity.ProductEntity;
import com.food_deliver.restaurant_service.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByRestaurantId(Long restaurantId);


    List<ProductEntity> findByRestaurant(RestaurantEntity restaurant);
}
