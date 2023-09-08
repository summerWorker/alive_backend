package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.BloodPressure;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.BloodPressureService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BloodPressureController.class)
class BloodPressureControllerTest {

    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;
    @Test
    void testAddBloodPressure1() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        MainRecord mainRecord = new MainRecord();
        when(mockMainRecordService.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Configure BloodPressureService.getLatestBloodPressure(...).
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("f287c49e-cf23-493a-9bd5-0da2239632b5"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureService.getLatestBloodPressure(0)).thenReturn(bloodPressure);

        // Configure BloodPressureService.getBloodPressureByDate(...).
        final BloodPressure bloodPressure1 = new BloodPressure();
        bloodPressure1.setUserId(0);
        bloodPressure1.setId(UUID.fromString("f287c49e-cf23-493a-9bd5-0da2239632b5"));
        bloodPressure1.setSystolic(0);
        bloodPressure1.setDiastolic(0);
        bloodPressure1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureService.getBloodPressureByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(bloodPressure1);

        final BloodPressure bloodPressure11 = new BloodPressure();
        bloodPressure11.setUserId(0);
        bloodPressure11.setId(UUID.fromString("f287c49e-cf23-493a-9bd5-0da2239632b5"));
        bloodPressure11.setSystolic(0);
        bloodPressure11.setDiastolic(0);
        bloodPressure11.setDate(Date.valueOf(LocalDate.of(2020, 1, 2)));
        when(mockBloodPressureService.addBloodPressure(any(BloodPressure.class))).thenReturn(bloodPressure11);
        when(mockBloodPressureService.getBloodPressureByDate(0, Date.valueOf(LocalDate.of(2020, 1, 2))))
                .thenReturn(null);

        // Configure BloodPressureService.addBloodPressure(...).
        final BloodPressure bloodPressure2 = new BloodPressure();
        bloodPressure2.setUserId(0);
        bloodPressure2.setId(UUID.fromString("f287c49e-cf23-493a-9bd5-0da2239632b5"));
        bloodPressure2.setSystolic(0);
        bloodPressure2.setDiastolic(999);
        bloodPressure2.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureService.addBloodPressure(any(BloodPressure.class))).thenReturn(bloodPressure2);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/add_blood_pressure")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"wrong\":\"wrong\"}")
                .param("token", "token"))
                .andReturn()
                .getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(-1 ,JSONObject.fromObject(response.getContentAsString()).getInt("status"));

        final MockHttpServletResponse response1 = mockMvc.perform(post("/add_blood_pressure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":0,\"id\":\"f287c49e-cf23-493a-9bd5-0da2239632b5\",\"systolic\":0,\"diastolic\":0,\"date\":\"2020-01\"}")
                        .param("token", "token"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), response1.getStatus());
        assertEquals(-1 ,JSONObject.fromObject(response1.getContentAsString()).getInt("status"));

        final MockHttpServletResponse response2 = mockMvc.perform(post("/add_blood_pressure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":0,\"id\":\"f287c49e-cf23-493a-9bd5-0da2239632b5\",\"systolic\":10000,\"diastolic\":0,\"date\":\"2020-01-01\"}")
                        .param("token", "token"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), response2.getStatus());
        assertEquals(-1 ,JSONObject.fromObject(response2.getContentAsString()).getInt("status"));

        final MockHttpServletResponse response3 = mockMvc.perform(post("/add_blood_pressure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":0,\"id\":\"f287c49e-cf23-493a-9bd5-0da2239632b5\",\"systolic\":50,\"diastolic\":50,\"date\":\"2020-01-01\"}")
                        .param("token", "token"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), response3.getStatus());
        assertEquals(JSONObject.fromObject(bloodPressure2, new CustomJsonConfig()) ,JSONObject.fromObject(response3.getContentAsString()).get("data"));

        final MockHttpServletResponse response4 = mockMvc.perform(post("/add_blood_pressure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":0,\"id\":\"f287c49e-cf23-493a-9bd5-0da2239632b5\",\"systolic\":0,\"diastolic\":0,\"date\":\"2020-01-02\"}")
                        .param("token", "token"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.OK.value(), response4.getStatus());
        assertEquals(JSONObject.fromObject(bloodPressure2, new CustomJsonConfig()), JSONObject.fromObject(response4.getContentAsString()).get("data"));

    }

    @Test
    void testGetPeriodBloodPressure1() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure BloodPressureService.getBloodPressureByDate(...).
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("f287c49e-cf23-493a-9bd5-0da2239632b5"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureService.getBloodPressureByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(bloodPressure);

        // Run the test
       final MockHttpServletResponse response = mockMvc.perform(post("/blood_pressure")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("token", "token"))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(JSONArray.fromObject(bloodPressure, new CustomJsonConfig()), JSONObject.fromObject(JSONObject.fromObject(response.getContentAsString()).get("data")).get("blood_pressure"));
    }

    @Test
    void testGetPeriodBloodPressure_BloodPressureServiceReturnsNull1() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
//        when(mockBloodPressureService.getBloodPressureByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
//                .thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/blood_pressure")
                        .content("{\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("token", "token"))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(-1, JSONObject.fromObject(response.getContentAsString()).getInt("status"));

        final MockHttpServletResponse response2 = mockMvc.perform(post("/blood_pressure")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2000-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("token", "token"))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(-1, JSONObject.fromObject(response.getContentAsString()).getInt("status"));
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BloodPressureService mockBloodPressureService;
    @MockBean
    private TokenService mockTokenService;
    @MockBean
    private MainRecordService mockMainRecordService;


}
