package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.BloodSugarService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.serviceimpl.user_info.UserAuthServiceImpl;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import net.sf.json.JSONArray;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        final List<BloodSugar> bloodSugars = Collections.singletonList(bloodSugar);
        // 注入任意参数固定返回值
        when(mockBloodSugarService.getBloodSugarByDate(anyInt(),any(Date.class),any(Date.class))).thenReturn(bloodSugars);

        // Run the test
       final MockHttpServletResponse response = mockMvc.perform(post("/blood_sugar")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                       .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JSONArray jsonArray = JSONArray.fromObject(bloodSugars, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("blood_sugar", jsonArray);
        assertEquals(jsonObject,JSONObject.fromObject(response.getContentAsString()).get("data"));
    }

    @Test
    void testGetPeriodBloodSugar_BloodSugarServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockBloodSugarService.getBloodSugarByDate(0, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/blood_sugar")
                        .content("{\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(-1, JSONObject.fromObject(response.getContentAsString()).get("status"));

        final MockHttpServletResponse response0 = mockMvc.perform(post("/blood_sugar")
                        .content("{\"start_date\":\"202\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response0.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(-1, JSONObject.fromObject(response0.getContentAsString()).get("status"));

        final MockHttpServletResponse response1 = mockMvc.perform(post("/blood_sugar")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2010-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(-1, JSONObject.fromObject(response1.getContentAsString()).get("status"));
    }

    @Test
    void testAddBloodSugar() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        MainRecord mainRecord = new MainRecord();
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

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
                        .content("{\"blood_sugar\":\"0.0\",\"date\":\"2020-01-01 10:00\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(JSONObject.fromObject(bloodSugar1, new CustomJsonConfig()), JSONObject.fromObject(response.getContentAsString()).get("data"));


        final MockHttpServletResponse response1 = mockMvc.perform(post("/add_blood_sugar")
                        .content("{\"date\":\"2020-01-01 10:00\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(-1, JSONObject.fromObject(response1.getContentAsString()).get("status"));

        final MockHttpServletResponse response2 = mockMvc.perform(post("/add_blood_sugar")
                        .content("{\"blood_sugar\":\"0.0\",\"date\":\"2020-01-\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response2.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(-1, JSONObject.fromObject(response2.getContentAsString()).get("status"));
    }

}
