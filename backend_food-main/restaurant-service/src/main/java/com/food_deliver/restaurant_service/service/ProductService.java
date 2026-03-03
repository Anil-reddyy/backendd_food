package com.food_deliver.restaurant_service.service;

import com.food_deliver.restaurant_service.Enum.FoodType;
import com.food_deliver.restaurant_service.dto.ProductRequest;
import com.food_deliver.restaurant_service.entity.MenuEntity;
import com.food_deliver.restaurant_service.entity.ProductEntity;
import com.food_deliver.restaurant_service.entity.RestaurantEntity;
import com.food_deliver.restaurant_service.repositories.MenuRepo;
import com.food_deliver.restaurant_service.repositories.ProductRepo;
import com.food_deliver.restaurant_service.repositories.RestaurantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final RestaurantRepo restaurantRepo;
    private final MenuRepo menuRepo;

//    public ProductEntity addProduct(
//            String name,
//            String description,
//            Double price,
//            String category,
//            MultipartFile image,
//            Long ownerId
//    ) {
//
//        RestaurantEntity restaurant =
//                restaurantRepo.findByOwnerId(ownerId)
//                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));
//
//        ProductEntity product = new ProductEntity();
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setCategory(category);
//        product.setRestaurant(restaurant);
//
//        try {
//            if (image != null && !image.isEmpty()) {
//                product.setImage(image.getBytes());
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Image upload failed");
//        }
//
//        return productRepo.save(product);
//    }

    public ProductEntity addProduct(ProductRequest request, Long ownerId) {

        RestaurantEntity restaurant =
                restaurantRepo.findByOwnerId(ownerId)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuEntity menu =
                menuRepo.findById(request.getMenuId())
                        .orElseThrow(() -> new RuntimeException("Menu not found"));

        if (!restaurant.getId().equals(menu.getRestaurant().getId())) {
            throw new RuntimeException("Unauthorized menu access");
        }

        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setRestaurant(restaurant);
        product.setMenu(menu);
        product.setFoodType(
                FoodType.valueOf(request.getFoodType().toUpperCase())
        );

        return productRepo.save(product);
    }

    public List<ProductEntity> getMyProducts(Long ownerId) {

        RestaurantEntity restaurant =
                restaurantRepo.findByOwnerId(ownerId)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        return productRepo.findByRestaurant(restaurant);
    }
}
