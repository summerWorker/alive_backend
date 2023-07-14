package com.alive_backend.service.basic_data;

import com.alive_backend.entity.basic_data.Food;

import java.util.List;
import java.util.UUID;

public interface FoodService {
    void addFood(Food food);
    Food findFoodByName(String name);

    void deleteFood(Food food);

    List<Food> findFoodByUserId(int userId);

    Food getFoodById(UUID foodId);
}
