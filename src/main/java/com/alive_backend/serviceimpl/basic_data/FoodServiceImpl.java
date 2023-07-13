package com.alive_backend.serviceimpl.basic_data;

import com.alive_backend.dao.basic_data.FoodDao;
import com.alive_backend.entity.basic_data.Food;
import com.alive_backend.service.basic_data.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodDao foodDao;

    @Override
    public void addFood(Food food) {
        foodDao.addFood(food);
    }

    @Override
    public Food findFoodByName(String name) {
        return foodDao.findFoodByName(name);
    }

    @Override
    public void deleteFood(Food food) {
        foodDao.deleteFood(food);
    }

    @Override
    public List<Food> findFoodByUserId(int userId) {
        return foodDao.findFoodByUserId(userId);
    }

    @Override
    public Food getFoodById(UUID foodId) {
        return foodDao.getFoodById(foodId);
    }


}
