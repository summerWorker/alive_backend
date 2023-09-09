package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.SleepDetail;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.health_data.SleepDetailService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.utils.analysis.Age;
import com.alive_backend.utils.analysis.SleepQuality;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SleepController.class)
class SleepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SleepDetailService mockSleepDetailService;
    @MockBean
    private TokenService mockTokenService;
    @MockBean
    private Age mockAge;
    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;
    @MockBean
    private SleepQuality mockSleepQuality;

    @Test
    void testGetDaySleep() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure SleepDetailService.getDaySleepDetail(...).
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue("detailValue");
        sleepDetail.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail);
        when(mockSleepDetailService.getDaySleepDetail(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(sleepDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/day_sleep")
                        .content("{\"start_date\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetDaySleep_SleepDetailServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockSleepDetailService.getDaySleepDetail(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/day_sleep")
                        .content("{\"stte\":\"2020-01-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final MockHttpServletResponse response1 = mockMvc.perform(post("/day_sleep")
                        .content("{\"start_date\":\"-01\",\"end_date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAnalyseSleep() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure SleepDetailService.getDaySleepDetail(...).
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue("{\"awake_count\":0,\"sleep_awake_duration\":0,\"bedtime\":1687365360,\"sleep_deep_duration\":88,\"sleep_light_duration\":335,\"sleep_rem_duration\":88,\"duration\":511,\"items\":[{\"end_time\":1687366140,\"state\":3,\"start_time\":1687365360},{\"end_time\":1687367220,\"state\":2,\"start_time\":1687366140},{\"end_time\":1687367520,\"state\":3,\"start_time\":1687367220},{\"end_time\":1687367820,\"state\":4,\"start_time\":1687367520},{\"end_time\":1687368480,\"state\":3,\"start_time\":1687367820},{\"end_time\":1687369920,\"state\":2,\"start_time\":1687368480},{\"end_time\":1687370220,\"state\":3,\"start_time\":1687369920},{\"end_time\":1687370580,\"state\":4,\"start_time\":1687370220},{\"end_time\":1687370640,\"state\":3,\"start_time\":1687370580},{\"end_time\":1687370940,\"state\":4,\"start_time\":1687370640},{\"end_time\":1687372440,\"state\":3,\"start_time\":1687370940},{\"end_time\":1687373520,\"state\":2,\"start_time\":1687372440},{\"end_time\":1687373760,\"state\":3,\"start_time\":1687373520},{\"end_time\":1687374780,\"state\":2,\"start_time\":1687373760},{\"end_time\":1687375020,\"state\":3,\"start_time\":1687374780},{\"end_time\":1687375440,\"state\":4,\"start_time\":1687375020},{\"end_time\":1687376280,\"state\":3,\"start_time\":1687375440},{\"end_time\":1687376580,\"state\":4,\"start_time\":1687376280},{\"end_time\":1687381620,\"state\":3,\"start_time\":1687376580},{\"end_time\":1687382280,\"state\":4,\"start_time\":1687381620},{\"end_time\":1687382700,\"state\":3,\"start_time\":1687382280},{\"end_time\":1687383060,\"state\":4,\"start_time\":1687382700},{\"end_time\":1687384800,\"state\":3,\"start_time\":1687383060},{\"end_time\":1687385460,\"state\":2,\"start_time\":1687384800},{\"end_time\":1687387080,\"state\":3,\"start_time\":1687385460},{\"end_time\":1687387380,\"state\":4,\"start_time\":1687387080},{\"end_time\":1687387440,\"state\":3,\"start_time\":1687387380},{\"end_time\":1687387860,\"state\":4,\"start_time\":1687387440},{\"end_time\":1687388460,\"state\":3,\"start_time\":1687387860},{\"end_time\":1687388820,\"state\":4,\"start_time\":1687388460},{\"end_time\":1687389420,\"state\":3,\"start_time\":1687388820},{\"end_time\":1687389780,\"state\":4,\"start_time\":1687389420},{\"end_time\":1687392660,\"state\":3,\"start_time\":1687389780},{\"end_time\":1687393140,\"state\":4,\"start_time\":1687392660},{\"end_time\":1687393380,\"state\":3,\"start_time\":1687393140},{\"end_time\":1687393740,\"state\":4,\"start_time\":1687393380},{\"end_time\":1687393800,\"state\":3,\"start_time\":1687393740},{\"end_time\":1687394100,\"state\":4,\"start_time\":1687393800},{\"end_time\":1687396020,\"state\":3,\"start_time\":1687394100}],\"date_time\":1687392000,\"timezone\":32,\"wake_up_time\":1687396020}");
        sleepDetail.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail);
        when(mockSleepDetailService.getDaySleepDetail(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(sleepDetails);

        when(mockAge.getAge(0)).thenReturn(10);

//        when(mockSleepQuality.analyseDaySleep(JSONObject.fromObject(sleepDetails.get(0).getDetailValue()),10)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/analyse_sleep")
                        .content("{\"date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAnalyseSleep_nosleep() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure SleepDetailService.getDaySleepDetail(...).
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue(null);
        sleepDetail.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail);
        List<SleepDetail> emptyList = Collections.emptyList();

        when(mockAge.getAge(0)).thenReturn(10);
        when(mockSleepDetailService.getDaySleepDetail(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(emptyList);

        when(mockAge.getAge(0)).thenReturn(10);

//        when(mockSleepQuality.analyseDaySleep(JSONObject.fromObject(sleepDetails.get(0).getDetailValue()),10)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/analyse_sleep")
                        .content("{\"date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAnalyseSleep_nosleep_thatday() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);

        // Configure SleepDetailService.getDaySleepDetail(...).
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue(null);
        sleepDetail.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail);

        when(mockAge.getAge(0)).thenReturn(10);
        when(mockSleepDetailService.getDaySleepDetail(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(sleepDetails);

        when(mockAge.getAge(0)).thenReturn(10);

//        when(mockSleepQuality.analyseDaySleep(JSONObject.fromObject(sleepDetails.get(0).getDetailValue()),10)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/analyse_sleep")
                        .content("{\"date\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAnalyseSleep_SleepDetailServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTokenService.getUserIdFromToken("token")).thenReturn(0);
        when(mockSleepDetailService.getDaySleepDetail(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());
        when(mockAge.getAge(0)).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response1 = mockMvc.perform(post("/analyse_sleep")
                        .content("{\"dte\":\"2020-01-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
        final MockHttpServletResponse response = mockMvc.perform(post("/analyse_sleep")
                        .content("{\"date\":\"2001-01\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
