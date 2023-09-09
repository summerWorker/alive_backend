package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Height;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.HeightService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.service.health_data.WeightService;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainRecordController.class)
class MainRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;

    @MockBean
    private MainRecordService mockMainRecordService;
    @MockBean
    private WeightService mockWeightService;
    @MockBean
    private HeightService mockHeightService;
    @MockBean
    private TokenService mockTokenService;

    @Test
    void testGetMainRecordByUserId() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure MainRecordService.getMainRecordByUserId(...).
        final MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(0);
        mainRecord.setHeight(0.0);
        mainRecord.setWeight(0.0);
        mainRecord.setExerciseTime(0.0);
        mainRecord.setUpdateTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/main_record")
                        .content("{}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAnalyseBMI() throws Exception {

        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        final MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(0);
        mainRecord.setHeight(180.0);
        mainRecord.setWeight(60.0);
       when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bmi")
                        .content("{}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }

    @Test
    void testAnalyseBMI_MainRecordServiceReturnsNull() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bmi")
                        .content("{}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetLatestRecord() throws Exception {
        // Setup
        // Configure MainRecordService.getMainRecordByUserId(...).
        final MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(0);
        mainRecord.setHeight(0.0);
        mainRecord.setWeight(0.0);
        mainRecord.setExerciseTime(0.0);
        mainRecord.setUpdateTime(Timestamp.valueOf(LocalDateTime.of(2000, 1, 1, 0, 0, 0, 0)));
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Configure WeightService.getLatestWeight(...).
        final Weight weight = new Weight(0, UUID.fromString("51e90d2c-bc38-46bb-8ef5-ee01aa4973cb"), 0.0,
                Date.valueOf(LocalDate.of(2020, 1, 1)), 0.0);
        when(mockWeightService.getLatestWeight(0)).thenReturn(weight);

        // Configure HeightService.getLatestHeight(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2023, 1, 1)));
        when(mockHeightService.getLatestHeight(0)).thenReturn(height);

        // Configure MainRecordService.updateMainRecord(...).
        final MainRecord mainRecord1 = new MainRecord();
        mainRecord1.setUserId(0);
        mainRecord1.setHeight(0.0);
        mainRecord1.setWeight(0.0);
        mainRecord1.setExerciseTime(0.0);
        mainRecord1.setUpdateTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final MainRecord mainRecord2 = new MainRecord();
        mainRecord2.setUserId(0);
        mainRecord2.setHeight(0.0);
        mainRecord2.setWeight(0.0);
        mainRecord2.setExerciseTime(0.0);
        mainRecord2.setUpdateTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        when(mockMainRecordService.updateMainRecord(mainRecord2)).thenReturn(mainRecord1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/update_main_record")
                        .content("{}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
