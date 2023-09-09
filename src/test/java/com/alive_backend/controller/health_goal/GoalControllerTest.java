package com.alive_backend.controller.health_goal;

import com.alive_backend.entity.goal.Goal;
import com.alive_backend.interceptor.AuthenticationInterceptor;
import com.alive_backend.service.goal.GoalService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GoalController.class)
class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoalService mockGoalService;
    @MockBean
    private AuthenticationInterceptor mockAuthenticationInterceptor;

    @Test
    void testGetGoalsByUserId() throws Exception {
        // Setup
        // Configure GoalService.getGoalsByUserId(...).
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setGoalName("goalName");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        goal.setGoalKey2("goalKey2");
        final List<Goal> goals = Arrays.asList(goal);
        when(mockGoalService.getGoalsByUserId(0)).thenReturn(goals);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/goals")
                        .content("{\"user_id\":1}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final MockHttpServletResponse response1 = mockMvc.perform(post("/goals")
                        .content("{\"ud\":1}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response1.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSetGoals() throws Exception {
        // Setup
        // Configure GoalService.getGoalByGoalName(...).
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setGoalName("weight_goal");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        goal.setGoalKey2("goalKey2");
        when(mockGoalService.getGoalByGoalName(0, "weight_goal")).thenReturn(goal);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/set_goal")
                        .content("{\"usd\": 1, \"goalName\": \"weight_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // Run the test
        final MockHttpServletResponse response1 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"weight_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response1.getContentAsString()).get("status")).isEqualTo(1);

        final MockHttpServletResponse response2 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"weight_goal\", \"goalDdl\": \"2-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response2.getContentAsString()).get("status")).isEqualTo(-1);

        final MockHttpServletResponse response3 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"weight_goal\", \"goalDdl\": \"2020-01-01\",  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response3.getContentAsString()).get("status")).isEqualTo(-1);


    }

    @Test
    void testSetGoals_GoalServiceGetGoalByGoalNameReturnsNull() throws Exception {
        Goal goal = new Goal();
        // Setup
        when(mockGoalService.getGoalByGoalName(0, "weight_goal")).thenReturn(goal);
        when(mockGoalService.getGoalByGoalName(0, "step_goal")).thenReturn(goal);
        when(mockGoalService.getGoalByGoalName(0, "sleep_length_goal")).thenReturn(goal);
        when(mockGoalService.getGoalByGoalName(0, "bedtime_goal")).thenReturn(goal);
        when(mockGoalService.getGoalByGoalName(0, "calorie_goal")).thenReturn(goal);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 0, \"goalName\": \"weight_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
       assertThat(JSONObject.fromObject(response.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response1 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"step_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response1.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response2 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 0, \"goalName\": \"step_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response2.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response3 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"step_goal\", \"goalDdl\": \"2020-01-01\",  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response3.getContentAsString()).get("status")).isEqualTo(-1);


        // Run the test
        final MockHttpServletResponse response4 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"sleep_length_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response4.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response5 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 0, \"goalName\": \"sleep_length_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response5.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response6 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"sleep_length_goal\", \"goalDdl\": \"2020-01-01\",  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response6.getContentAsString()).get("status")).isEqualTo(-1);

        // Run the test
        final MockHttpServletResponse response7 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"bedtime_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"20:00\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response7.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response711 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"bedtime_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"20\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response711.getContentAsString()).get("status")).isEqualTo(1);


        // Run the test
        final MockHttpServletResponse response8 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 0, \"goalName\": \"bedtime_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"20:00\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response8.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response9 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"bedtime_goal\", \"goalDdl\": \"2020-01-01\",  \"goalKey2\": \"20202\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response9.getContentAsString()).get("status")).isEqualTo(-1);
        // Run the test
        final MockHttpServletResponse response11 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"bedtime_goal\", \"goalDdl\": \"2020-01-01\",  \"go\": \"20202\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response11.getContentAsString()).get("status")).isEqualTo(-1);

        // Run the test
        final MockHttpServletResponse response41 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"calorie_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response41.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response51 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 0, \"goalName\": \"calorie_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response51.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response61 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 1, \"goalName\": \"calorie_goal\", \"goalDdl\": \"2020-01-01\",  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response61.getContentAsString()).get("status")).isEqualTo(-1);

        // Run the test
        final MockHttpServletResponse response511 = mockMvc.perform(post("/set_goal")
                        .content("{\"user_id\": 0, \"goalName\": \"calo_goal\", \"goalDdl\": \"2020-01-01\", \"goalKey1\": 1,  \"goalKey2\": \"2020-02-02\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response511.getContentAsString()).get("status")).isEqualTo(-1);


    }

    @Test
    void testGetGoalByName() throws Exception {
        // Setup
        // Configure GoalService.getGoalByGoalName(...).
        final Goal goal = new Goal();
        goal.setUserId(0);
//        goal.setGoalName("goalName");
//        goal.setGoalKey1(0.0);
//        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
//        goal.setGoalKey2("goalKey2");
        when(mockGoalService.getGoalByGoalName(0, "weight_goal")).thenReturn(null);
        when(mockGoalService.getGoalByGoalName(0, "bedtime_goal")).thenReturn(goal);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/goal_name")
                        .content("{\"user_id\":0, \"goalName\":\"weight_goal\"}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        // Run the test
        final MockHttpServletResponse response1 = mockMvc.perform(post("/goal_name")
                        .content("{\"user_id\":0, \"goalName\":\"bedtime_goal\"}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response1.getContentAsString()).get("status")).isEqualTo(1);

        // Run the test
        final MockHttpServletResponse response2 = mockMvc.perform(post("/goal_name")
                        .content("{\"use\":0, \"goalName\":\"weight_goal\"}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response2.getContentAsString()).get("status")).isEqualTo(-1);

        // Run the test
        final MockHttpServletResponse response11 = mockMvc.perform(post("/goal_name")
                        .content("{\"user_id\":0, \"goalName\":\"bedtioal\"}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(JSONObject.fromObject(response11.getContentAsString()).get("status")).isEqualTo(-1);
    }

}
