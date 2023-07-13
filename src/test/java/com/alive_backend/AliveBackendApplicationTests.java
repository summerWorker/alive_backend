package com.alive_backend;

import com.alive_backend.controller.health_data.WeightController;
import com.alive_backend.dao.health_data.WeightDaoTest;
import com.alive_backend.daoimpl.health_data.WeightDaoImplTest;
import com.alive_backend.repository.health_data.WeightRepositoryTest;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.serviceimpl.health_data.WeightServiceImplTest;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WeightServiceImplTest.class,
        WeightDaoTest.class,
        WeightDaoImplTest.class,
        WeightRepositoryTest.class})
@SpringBootTest
class AliveBackendApplicationTests {
    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    @Test
    void contextLoads() {

    }
}
// //集成测试，发送http请求
//package com.alive_backend.controller.health_data;
//
//        import com.alive_backend.service.health_data.WeightService;
//        import com.alive_backend.serviceimpl.TokenService;
//        import com.alive_backend.utils.constant.Constant;
//        import com.alive_backend.utils.constant.UserConstant;
//        import com.alive_backend.utils.msg.Msg;
//        import com.alive_backend.utils.msg.MsgUtil;
//        import com.fasterxml.jackson.core.JsonProcessingException;
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import org.junit.jupiter.api.AfterEach;
//        import org.junit.jupiter.api.BeforeEach;
//        import org.junit.jupiter.api.Test;
//        import org.junit.jupiter.api.extension.ExtendWith;
//        import org.junit.runner.RunWith;
//        import org.mockito.InjectMocks;
//        import org.mockito.Mock;
//        import org.mockito.Mockito;
//        import org.mockito.MockitoAnnotations;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//        import org.springframework.boot.test.context.SpringBootTest;
//        import org.springframework.boot.test.mock.mockito.MockBean;
//        import org.springframework.test.context.junit.jupiter.SpringExtension;
//        import org.springframework.test.context.junit4.SpringRunner;
//        import org.springframework.test.web.servlet.MockMvc;
//        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//        import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//        import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//
//        import javax.servlet.http.HttpServletRequest;
//        import javax.ws.rs.core.MediaType;
//        import java.sql.Date;
//        import java.util.HashMap;
//        import java.util.Map;
//
//        import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
////@ExtendWith(SpringExtension.class)
////@WebMvcTest(WeightController.class)
//public class WeightControllerTest {
//    @Mock
//    private WeightService weightService;
//    @InjectMocks
//    private WeightController weightController;
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private TokenService tokenService;
//    @MockBean
//    private ServerEndpointExporter serverEndpointExporter;
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getWeight() throws Exception {
//        Msg msg1 = MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,date:yyyy-MM-dd}", null);
//
//        // 准备测试数据和依赖项
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwidXNlcl9pZCI6MSwiZXhwIjoxNjg5MjM3NDY0LCJ1c2VybmFtZSI6InRlc3QifQ.qGz-qiuzn4-qyioDb00v7iloapmeQ8b5DkNL924PDG8";
//        Date date = Date.valueOf("2023-07-13");
//        Map<String, Object> requestData = new HashMap<>();
//        requestData.put(UserConstant.USER_ID, 1);
//        requestData.put(Constant.DATE, date.toString());
//
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        Mockito.when(request.getHeader("token")).thenReturn(token);
//
//        // 模拟 tokenService 的行为
//        Mockito.when(tokenService.getUserIdFromToken(token)).thenReturn(1);
//
//        // 执行控制器方法
//        String responseJson = mockMvc.perform(MockMvcRequestBuilders.post("/weight")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", token)
//                        .content(new ObjectMapper().writeValueAsString(requestData)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        // 验证返回的数据
//
//        System.out.println(responseJson);
//
//    }
//
//    @Test
//    void getWeightByUser() {
//    }
//
//    @Test
//    void addWeight() {
//    }
//
//    @Test
//    void getPeriodWeight() {
//    }
//}