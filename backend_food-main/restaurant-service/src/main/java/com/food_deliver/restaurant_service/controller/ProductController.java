package com.food_deliver.restaurant_service.controller;

import com.food_deliver.restaurant_service.Security.JwtUtil;
import com.food_deliver.restaurant_service.dto.ProductRequest;
import com.food_deliver.restaurant_service.entity.ProductEntity;
import com.food_deliver.restaurant_service.entity.RestaurantEntity;
import com.food_deliver.restaurant_service.repositories.ProductRepo;
import com.food_deliver.restaurant_service.repositories.RestaurantRepo;
import com.food_deliver.restaurant_service.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final JwtUtil jwtUtil;
//
//    @PostMapping("/add")
//    public ResponseEntity<?> addProduct(
//            @RequestParam String name,
//            @RequestParam String description,
//            @RequestParam Double price,
//            @RequestParam String category,
//            @RequestParam(required = false) MultipartFile image,
//            HttpServletRequest request
//    ) {
//
//        String token = request.getHeader("Authorization").substring(7);
//        Long ownerId = jwtUtil.extractUserId(token);
//
//        return ResponseEntity.ok(
//                productService.addProduct(
//                        name, description, price, category, image, ownerId
//                )
//        );
//    }
@PostMapping("/add")
public ResponseEntity<?> addProduct(
        @RequestBody ProductRequest requestBody,
        HttpServletRequest request
) {

    String token = request.getHeader("Authorization").substring(7);
    Long ownerId = jwtUtil.extractUserId(token);

    return ResponseEntity.ok(
            productService.addProduct(
                    requestBody,
                    ownerId
            )
    );
}

    @GetMapping("/my-products")
    public ResponseEntity<?> getMyProducts(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(
                productService.getMyProducts(ownerId)
        );
    }
}