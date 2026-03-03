package com.food_deliver.restaurant_service.repositories;
import com.food_deliver.restaurant_service.entity.MenuEntity;
import com.food_deliver.restaurant_service.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepo extends JpaRepository<MenuEntity, Long> {

    List<MenuEntity> findByRestaurant(RestaurantEntity restaurant);
}
