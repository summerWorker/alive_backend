package com.alive_backend.controller.health_data;

import com.alive_backend.entity.basic_data.Food;
import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.basic_data.FoodService;
import com.alive_backend.service.health_data.DietService;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DietController.class)
class DietControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DietService mockDietService;
    @MockBean
    private FoodService mockFoodService;
    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;

    @Test
    void testAddDiet() throws Exception {
        // Setup
        // Configure FoodService.findFoodByName(...).
        final Food food = new Food();
        food.setId(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodService.findFoodByName("name")).thenReturn(food);

        // Configure DietService.findDietByUserIdAndFoodIdAndDateAndType(...).
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("237e8517-7191-40fe-aba7-105867bc5935"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"));
        diet.setType(0);
        diet.setUserId(0);
        when(mockDietService.findDietByUserIdAndFoodIdAndDateAndType(0,
                UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"), Date.valueOf(LocalDate.of(2020, 1, 1)),
                0)).thenReturn(diet);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_diet")
                        .content("{\"user_id\":1, \"name\":\"面包\", \"date\":\"2020-01-01\", \"type\":\"BREAKFAST\", \"amount\":1}}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // Run the test
        final MockHttpServletResponse response1 = mockMvc.perform(post("/add_diet")
                        .content("{\"uid\":1, \"name\":\"面包\", \"date\":\"2020-01-01\", \"type\":\"BREAKFAST\", \"amount\":1}}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
        // Run the test
        final MockHttpServletResponse response2 = mockMvc.perform(post("/add_diet")
                        .content("{\"user_id\":1, \"name\":\"面包\", \"date\":\"201\", \"type\":\"BREAKFAST\", \"amount\":1}}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());
        // Run the test
        final MockHttpServletResponse response3 = mockMvc.perform(post("/add_diet")
                        .content("{\"user_id\":1, \"name\":\"面包\", \"date\":\"2020-01-01\", \"type\":\"BREAKFAST\", \"amount\":-1}}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response3.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAddDiet_DietServiceFindDietByUserIdAndFoodIdAndDateAndTypeReturnsNull() throws Exception {
        // Setup
        // Configure FoodService.findFoodByName(...).
        final Food food = new Food();
        food.setId(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"));
        food.setName("面包");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodService.findFoodByName("面包")).thenReturn(food);

        when(mockDietService.findDietByUserIdAndFoodIdAndDateAndType(0,
                UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"), Date.valueOf(LocalDate.of(2020, 1, 1)),
                0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_diet")
                        .content("{\"user_id\":1, \"name\":\"面包\", \"date\":\"2020-01-01\", \"type\":\"BREAKFAST\", \"amount\":1}}").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response.getContentAsString()).get("status")).isEqualTo(1);

    }
@Test
    void testAddDiet_DietServiceFindDietByUserIdAndFoodIdAndDateAndTypeReturn() throws Exception {
        // Setup
        // Configure FoodService.findFoodByName(...).
        final Food food = new Food();
        food.setId(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"));
        food.setName("面包");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodService.findFoodByName("面包")).thenReturn(food);

        Diet diet = new Diet();
        diet.setId(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"));

        when(mockDietService.findDietByUserIdAndFoodIdAndDateAndType(anyInt(), any(UUID.class), any(Date.class), anyInt())).thenReturn(diet);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_diet")
                        .content("{\"user_id\":1, \"name\":\"面包\", \"date\":\"2020-01-01\", \"type\":\"BREAKFAST\", \"amount\":1}}").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response.getContentAsString()).get("status")).isEqualTo(1);

    }

    @Test
    void testGetDiet() throws Exception {
        // Setup
        // Configure DietService.findDietByUserIdAndDate(...).
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("237e8517-7191-40fe-aba7-105867bc5935"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"));
        diet.setType(0);
        diet.setUserId(0);
        final List<Diet> diets = Arrays.asList(diet);
        when(mockDietService.findDietByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(diets);

        // Configure FoodService.getFoodById(...).
        final Food food = new Food();
        food.setId(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"));
        food.setName("name");
        food.setPicture("picture");
        food.setUserId(0);
        food.setCarbohydrate(0.0);
        when(mockFoodService.getFoodById(UUID.fromString("ecab9e57-a8cb-4cfe-9822-b2730b15bce2"))).thenReturn(food);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/get_diet")
                        .param("user_id", "0")
                        .param("date", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetDiet_DietServiceReturnsNull() throws Exception {
        // Setup
        when(mockDietService.findDietByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/get_diet")
                        .param("user_id", "0")
                        .param("date", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetDiet_DietServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockDietService.findDietByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/get_diet")
                        .param("user_id", "0")
                        .param("date", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
