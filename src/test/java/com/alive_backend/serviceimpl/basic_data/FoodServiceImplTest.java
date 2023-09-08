package com.alive_backend.serviceimpl.basic_data;

import com.alive_backend.dao.basic_data.FoodDao;
import com.alive_backend.entity.basic_data.Food;
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
class FoodServiceImplTest {

    @Mock
    private FoodDao mockFoodDao;

    @InjectMocks
    private FoodServiceImpl foodServiceImplUnderTest;

    @Test
    void testAddFood() {
        // Setup
        final Food food = new Food();
        food.setId(UUID.fromString("c7024e3c-7f7a-41ce-8c0c-1411c2f4d65e"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);

        // Run the test
        foodServiceImplUnderTest.addFood(food);

        // Verify the results
        verify(mockFoodDao).addFood(any(Food.class));
    }

    @Test
    void testFindFoodByName() {
        // Setup
        // Configure FoodDao.findFoodByName(...).
        final Food food = new Food();
        food.setId(UUID.fromString("c7024e3c-7f7a-41ce-8c0c-1411c2f4d65e"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodDao.findFoodByName("name")).thenReturn(food);

        // Run the test
        final Food result = foodServiceImplUnderTest.findFoodByName("name");

        // Verify the results
    }

    @Test
    void testDeleteFood() {
        // Setup
        final Food food = new Food();
        food.setId(UUID.fromString("c7024e3c-7f7a-41ce-8c0c-1411c2f4d65e"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);

        // Run the test
        foodServiceImplUnderTest.deleteFood(food);

        // Verify the results
        verify(mockFoodDao).deleteFood(any(Food.class));
    }

    @Test
    void testFindFoodByUserId() {
        // Setup
        // Configure FoodDao.findFoodByUserId(...).
        final Food food = new Food();
        food.setId(UUID.fromString("c7024e3c-7f7a-41ce-8c0c-1411c2f4d65e"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        final List<Food> foods = Arrays.asList(food);
        when(mockFoodDao.findFoodByUserId(0)).thenReturn(foods);

        // Run the test
        final List<Food> result = foodServiceImplUnderTest.findFoodByUserId(0);

        // Verify the results
    }

    @Test
    void testFindFoodByUserId_FoodDaoReturnsNoItems() {
        // Setup
        when(mockFoodDao.findFoodByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Food> result = foodServiceImplUnderTest.findFoodByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetFoodById() {
        // Setup
        // Configure FoodDao.getFoodById(...).
        final Food food = new Food();
        food.setId(UUID.fromString("c7024e3c-7f7a-41ce-8c0c-1411c2f4d65e"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodDao.getFoodById(UUID.fromString("78762d24-5b15-4064-9427-98ce5289661c"))).thenReturn(food);

        // Run the test
        final Food result = foodServiceImplUnderTest.getFoodById(
                UUID.fromString("78762d24-5b15-4064-9427-98ce5289661c"));

        // Verify the results
    }
}
