package com.food_deliver.restaurant_service.service;
import com.food_deliver.restaurant_service.entity.MenuEntity;
import com.food_deliver.restaurant_service.entity.RestaurantEntity;
import com.food_deliver.restaurant_service.repositories.MenuRepo;
import com.food_deliver.restaurant_service.repositories.RestaurantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepo menuRepo;
    private final RestaurantRepo restaurantRepo;

    public MenuEntity createMenu(String name, Long ownerId) {

        RestaurantEntity restaurant =
                restaurantRepo.findByOwnerId(ownerId)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuEntity menu = new MenuEntity();
        menu.setName(name);
        menu.setRestaurant(restaurant);

        return menuRepo.save(menu);
    }

    public List<MenuEntity> getMyMenus(Long ownerId) {

        RestaurantEntity restaurant =
                restaurantRepo.findByOwnerId(ownerId)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        return menuRepo.findByRestaurant(restaurant);
    }

    public String deleteMenu(Long menuId, Long ownerId) {

        RestaurantEntity restaurant =
                restaurantRepo.findByOwnerId(ownerId)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuEntity menu =
                menuRepo.findById(menuId)
                        .orElseThrow(() -> new RuntimeException("Menu not found"));

        if (!restaurant.getId().equals(menu.getRestaurant().getId())) {
            throw new RuntimeException("Unauthorized");
        }


        menuRepo.delete(menu);
        return "Menu deleted successfully";
    }
}