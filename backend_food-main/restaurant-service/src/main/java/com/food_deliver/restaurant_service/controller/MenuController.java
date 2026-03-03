package com.food_deliver.restaurant_service.controller;

import com.food_deliver.restaurant_service.Security.JwtUtil;
import com.food_deliver.restaurant_service.dto.MenuRequestDto;
import com.food_deliver.restaurant_service.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createMenu(
            @RequestBody MenuRequestDto requestBody,
            HttpServletRequest request
    ) {

        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(
                menuService.createMenu(requestBody.getName(), ownerId)
        );
    }
    @GetMapping("/my-menus")
    public ResponseEntity<?> getMyMenus(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(
                menuService.getMyMenus(ownerId)
        );
    }

    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<?> deleteMenu(
            @PathVariable Long menuId,
            HttpServletRequest request
    ) {

        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(
                menuService.deleteMenu(menuId, ownerId)
        );
    }
}
