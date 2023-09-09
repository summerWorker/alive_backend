package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.HeartRate;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.HeartRateService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.serviceimpl.TokenService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HeartRateController.class)
class HeartRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeartRateService mockHeartRateService;
    @MockBean
    private TokenService mockTokenService;
    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;
    @MockBean
    private MainRecordService mockMainRecordService;

    @Test
    void testGetHeartRate() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure HeartRateService.findHeartRateByUserIdAndDateBetween(...).
        final HeartRate heartRate = new HeartRate();
        heartRate.setId(0);
        heartRate.setUserId(0);
        heartRate.setTimeStamp(0L);
        heartRate.setDetailValue("detailValue");
        final List<HeartRate> heartRates = Arrays.asList(heartRate);
        when(mockHeartRateService.findHeartRateByUserIdAndDateBetween(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(heartRates);

        // Configure HeartRateService.findHeartRateByUserIdAndDate(...).
        final HeartRate heartRate1 = new HeartRate();
        heartRate1.setId(0);
        heartRate1.setUserId(0);
        heartRate1.setTimeStamp(0L);
        heartRate1.setDetailValue("detailValue");
        final List<HeartRate> heartRates1 = Arrays.asList(heartRate1);
        when(mockHeartRateService.findHeartRateByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(heartRates1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/heartRate")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response1 = mockMvc.perform(post("/heartRate")
                        .content("{\"start_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetHeartRate_HeartRateServiceFindHeartRateByUserIdAndDateBetweenReturnsNoItems() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockHeartRateService.findHeartRateByUserIdAndDateBetween(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/heartRate")
                        .content("{\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response1 = mockMvc.perform(post("/heartRate")
                        .content("{\"start_date\":\"201-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response2 = mockMvc.perform(post("/heartRate")
                        .content("{\"start_date\":\"201-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAddHeartRate() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        MainRecord mainRecord = new MainRecord();
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_heartRate")
                        .content("{\"timeStamp\":0,\"heartRate\":\"111\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response1 = mockMvc.perform(post("/add_heartRate")
                        .content("{\"timmp\":0,\"heartRate\":\"111\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response2 = mockMvc.perform(post("/add_heartRate")
                        .content("{\"timeStamp\":\"a\",\"heartRate\":\"111\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
