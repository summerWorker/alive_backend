package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Steps;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.StepsService;
import com.alive_backend.serviceimpl.TokenService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StepsController.class)
class StepsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StepsService mockStepsService;
    @MockBean
    private TokenService mockTokenService;
    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;

    @Test
    void testTest() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/steps")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetSteps() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure StepsService.getSteps(...).
        final Steps steps1 = new Steps();
        steps1.setUserId(0);
        steps1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps1.setStep(0);
        steps1.setGoal(0);
        steps1.setDistance(0);
        steps1.setCalories(0);
        final List<Steps> steps = Arrays.asList(steps1);
        when(mockStepsService.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(steps);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/get_steps")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response.getContentAsString()).get("status")).isEqualTo(1);
    }

    @Test
    void testGetSteps_StepsServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockStepsService.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(post("/get_steps")
                        .content("{\"stae\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response0 = mockMvc.perform(post("/get_steps")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2000-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response0.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
