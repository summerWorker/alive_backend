package com.alive_backend.daoimpl.basic_data;

import com.alive_backend.dao.basic_data.FoodDao;
import com.alive_backend.entity.basic_data.Food;
import com.alive_backend.repository.basic_data.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FoodDaoImpl implements FoodDao {
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public void addFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public Food findFoodByName(String name) {
        return foodRepository.findByName(name);
    }

    @Override
    public void deleteFood(Food food) {
        foodRepository.delete(food);
    }

    @Override
    public List<Food> findFoodByUserId(int userId) {
        return foodRepository.findByUserId(userId);
    }

    @Override
    public Food getFoodById(UUID id) {
        return foodRepository.getFoodById(id);
    }


}
