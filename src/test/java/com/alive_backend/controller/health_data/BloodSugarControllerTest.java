package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.BloodSugarService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.serviceimpl.user_info.UserAuthServiceImpl;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BloodSugarController.class)
class BloodSugarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BloodSugarService mockBloodSugarService;
    @MockBean
    private TokenService mockTokenService;
    @MockBean
    private MainRecordService mockMainRecordService;

    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;

    @Test
    void testGetPeriodBloodSugar() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure BloodSugarService.getBloodSugarByDate(...).
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("1fb504cf-0a9b-4147-bdf5-fbb991a23c62"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<BloodSugar> bloodSugars = Arrays.asList(bloodSugar);
        when(mockBloodSugarService.getBloodSugarByDate(0, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(bloodSugars);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/blood_sugar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetPeriodBloodSugar_BloodSugarServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockBloodSugarService.getBloodSugarByDate(0, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/blood_sugar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAddBloodSugar() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure BloodSugarService.getLatestBloodSugar(...).
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("1fb504cf-0a9b-4147-bdf5-fbb991a23c62"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockBloodSugarService.getLatestBloodSugar(0)).thenReturn(bloodSugar);

        // Configure BloodSugarService.addBloodSugar(...).
        final BloodSugar bloodSugar1 = new BloodSugar();
        bloodSugar1.setUserId(0);
        bloodSugar1.setId(UUID.fromString("1fb504cf-0a9b-4147-bdf5-fbb991a23c62"));
        bloodSugar1.setBloodSugar(0.0);
        bloodSugar1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockBloodSugarService.addBloodSugar(any(BloodSugar.class))).thenReturn(bloodSugar1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_blood_sugar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAddBloodSugar_BloodSugarServiceGetLatestBloodSugarReturnsNull() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockBloodSugarService.getLatestBloodSugar(0)).thenReturn(null);

        // Configure MainRecordService.getMainRecordByUserId(...).
        final MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(0);
        mainRecord.setHeight(0.0);
        mainRecord.setWeight(0.0);
        mainRecord.setBloodSugar(0.0);
        mainRecord.setUpdateTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Configure BloodSugarService.addBloodSugar(...).
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("1fb504cf-0a9b-4147-bdf5-fbb991a23c62"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockBloodSugarService.addBloodSugar(any(BloodSugar.class))).thenReturn(bloodSugar);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_blood_sugar")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");

        // Confirm MainRecordService.updateMainRecord(...).
        final MainRecord mainRecord1 = new MainRecord();
        mainRecord1.setUserId(0);
        mainRecord1.setHeight(0.0);
        mainRecord1.setWeight(0.0);
        mainRecord1.setBloodSugar(0.0);
        mainRecord1.setUpdateTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        verify(mockMainRecordService).updateMainRecord(mainRecord1);
    }
}
