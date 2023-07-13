package com.alive_backend.repository.basic_data;

import com.alive_backend.entity.basic_data.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
    Food findByName(String name);
    List<Food> findByUserId(int userId);
    Food getById(UUID id);
}
