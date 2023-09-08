package com.alive_backend.daoimpl.basic_data;

import com.alive_backend.entity.basic_data.Food;
import com.alive_backend.repository.basic_data.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodDaoImplTest {

    @Mock
    private FoodRepository mockFoodRepository;

    @InjectMocks
    private FoodDaoImpl foodDaoImplUnderTest;

    @Test
    void testAddFood() {
        // Setup
        final Food food = new Food();
        food.setId(UUID.fromString("19a0816d-f875-40b9-b8e7-6b05abc5c584"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);

        // Run the test
        foodDaoImplUnderTest.addFood(food);

        // Verify the results
        verify(mockFoodRepository).save(any(Food.class));
    }

    @Test
    void testFindFoodByName() {
        // Setup
        // Configure FoodRepository.findByName(...).
        final Food food = new Food();
        food.setId(UUID.fromString("19a0816d-f875-40b9-b8e7-6b05abc5c584"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodRepository.findByName("name")).thenReturn(food);

        // Run the test
        final Food result = foodDaoImplUnderTest.findFoodByName("name");

        // Verify the results
    }

    @Test
    void testDeleteFood() {
        // Setup
        final Food food = new Food();
        food.setId(UUID.fromString("19a0816d-f875-40b9-b8e7-6b05abc5c584"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);

        // Run the test
        foodDaoImplUnderTest.deleteFood(food);

        // Verify the results
        verify(mockFoodRepository).delete(any(Food.class));
    }

    @Test
    void testFindFoodByUserId() {
        // Setup
        // Configure FoodRepository.findByUserId(...).
        final Food food = new Food();
        food.setId(UUID.fromString("19a0816d-f875-40b9-b8e7-6b05abc5c584"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        final List<Food> foods = Arrays.asList(food);
        when(mockFoodRepository.findByUserId(0)).thenReturn(foods);

        // Run the test
        final List<Food> result = foodDaoImplUnderTest.findFoodByUserId(0);

        // Verify the results
    }

    @Test
    void testFindFoodByUserId_FoodRepositoryReturnsNoItems() {
        // Setup
        when(mockFoodRepository.findByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Food> result = foodDaoImplUnderTest.findFoodByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetFoodById() {
        // Setup
        // Configure FoodRepository.getFoodById(...).
        final Food food = new Food();
        food.setId(UUID.fromString("19a0816d-f875-40b9-b8e7-6b05abc5c584"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodRepository.getFoodById(UUID.fromString("ae3c0278-6d67-4d05-97d1-c01adef1fe50"))).thenReturn(food);

        // Run the test
        final Food result = foodDaoImplUnderTest.getFoodById(UUID.fromString("ae3c0278-6d67-4d05-97d1-c01adef1fe50"));

        // Verify the results
    }
}
