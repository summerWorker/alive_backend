package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Height;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.HeightService;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HeightController.class)
class HeightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;

    @MockBean
    private HeightService mockHeightService;
    @MockBean
    private TokenService mockTokenService;
    @MockBean
    private MainRecordService mockMainRecordService;

    @Test
    void testGetHeightByDate() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure HeightService.getHeightByDate(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightService.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(height);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/height")
                        .content("{\"date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final MockHttpServletResponse response1 = mockMvc.perform(post("/height")
                        .content("{\"date1\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
        final MockHttpServletResponse response2 = mockMvc.perform(post("/height")
                        .content("{\"date\":\"202-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetHeightByUser() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure HeightService.getHeightByUser(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Height> heights = Arrays.asList(height);
        when(mockHeightService.getHeightByUser(0)).thenReturn(heights);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user_height")
                        .content("{}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAddHeight() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        MainRecord mainRecord = new MainRecord();
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Configure HeightService.getLatestHeight(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightService.getLatestHeight(0)).thenReturn(height);

        // Configure HeightService.getHeightByDate(...).
        final Height height1 = new Height();
        height1.setUserId(0);
        height1.setHeight(0.0);
        height1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightService.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(height1);
        when(mockHeightService.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 2)))).thenReturn(null);

        // Configure HeightService.addHeight(...).
        final Height height2 = new Height();
        height2.setUserId(0);
        height2.setHeight(0.0);
        height2.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Height height3 = new Height();
        height3.setUserId(0);
        height3.setHeight(0.0);
        height3.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightService.addHeight(height3)).thenReturn(height2);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_height")
                        .content("{\"height\":180.0,\"date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response0 = mockMvc.perform(post("/add_height")
                        .content("{\"height\":180.0,\"date\":\"2020-01-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response0.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response1 = mockMvc.perform(post("/add_height")
                        .content("{\"het\":180.0,\"date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response2 = mockMvc.perform(post("/add_height")
                        .content("{\"height\":180.0,\"date\":\"2001\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    void testGetPeriodHeight() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure HeightService.getHeightByDate(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightService.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(height);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/period_height")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response1 = mockMvc.perform(post("/period_height")
                        .content("{\"state\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response2 = mockMvc.perform(post("/period_height")
                        .content("{\"start_date\":\"2001\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());

        final MockHttpServletResponse response3 = mockMvc.perform(post("/period_height")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2010-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response3.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetPeriodHeight_HeightServiceReturnsNull() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockHeightService.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/period_height")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
