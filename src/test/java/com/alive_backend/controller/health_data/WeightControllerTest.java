package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WeightControllerTest {
    @Mock
    private WeightService weightService;
    @InjectMocks
    private WeightController weightController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

    private Weight fakeWeight1, fakeWeight2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeWeight1 = new Weight(); fakeWeight2 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);
        fakeWeight2.setUserId(1); fakeWeight2.setDate(Date.valueOf("2022-07-11")); fakeWeight2.setWeight(61.0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWeight() throws Exception {
        Msg msg1 = MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{date:yyyy-MM-dd}", null);

        // 准备测试数据和依赖项
        String token = "test_token";
        Date date = Date.valueOf("2022-07-13");
        Map<String, Object> requestData = new HashMap<>();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("token")).thenReturn(token);
        when(weightService.getWeightByDate(1, date)).thenReturn(fakeWeight1);

        // 模拟 tokenService 的行为
        when(tokenService.getUserIdFromToken(token)).thenReturn(1);

        // 没有传参
        Msg result = weightController.getWeight(requestData, request);
        assertMsgEqual(msg1, result);

        // 日期格式错误
        requestData.put(Constant.DATE,"222");
        Msg result2 = weightController.getWeight(requestData, request);
        Msg msg2 =MsgUtil.makeMsg(MsgUtil.ERROR, "日期格式错误{date:yyyy-MM-dd}", null);
        assertMsgEqual(msg2, result2);

        // 日期格式正确
        requestData.put(Constant.DATE, date.toString());
        Msg result3 = weightController.getWeight(requestData, request);
        Msg msg3 = MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(fakeWeight1, new CustomJsonConfig()));
        assertMsgEqual(msg3, result3);

    }

    @Test
    void getWeightByUser() {
        // 准备测试数据
        int userId = 1;
        String token = "test_token";
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("user_id", userId);

        // 模拟 HttpServletRequest 的行为
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("token")).thenReturn(token);
        Mockito.when(request.getHeader("token")).thenReturn(token);

        // 模拟 tokenService 的行为
        Mockito.when(tokenService.getUserIdFromToken(token)).thenReturn(userId);

        // 创建用于测试的假数据
        List<Weight> fakeWeights = Arrays.asList(
               fakeWeight1, fakeWeight2
        );

        // 模拟 weightService 的行为
        Mockito.when(weightService.getWeightByUser(userId)).thenReturn(fakeWeights);

        // 调用被测试的方法
        Msg result = weightController.getWeightByUser(requestData, request);
        JSONArray jsonArray = JSONArray.fromObject(fakeWeights, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weights",jsonArray);
        Msg msg = MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);

        // 验证结果
        assertMsgEqual(result, msg);
    }

    @Test
    void addWeight() {
        // 准备测试数据
        int userId = 1;
        String token = "test_token";
        Map<String, Object> requestData1 = new HashMap<>(), requestData2 = new HashMap<>();
        // 模拟 HttpServletRequest 的行为
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("token")).thenReturn(token);
        // 模拟 tokenService 的行为
        Mockito.when(tokenService.getUserIdFromToken(token)).thenReturn(userId);
        // 模拟 weightService 的行为
        Mockito.when(weightService.getWeightByDate(userId, Date.valueOf("2022-07-10"))).thenReturn(fakeWeight1);
        Mockito.when(weightService.getWeightByDate(userId, Date.valueOf("2022-07-11"))).thenReturn(null);
        Weight modifiedWeight = fakeWeight1;
        modifiedWeight.setWeight(60.0);
        Mockito.when(weightService.addWeight(Mockito.any(Weight.class))).thenReturn(fakeWeight2);
        Mockito.when(weightService.addWeight(modifiedWeight)).thenReturn(modifiedWeight);
        Mockito.when(weightService.addWeight(fakeWeight2)).thenReturn(fakeWeight2);
//        Mockito.when(weightService.addWeight(Mockito.any(Weight.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 1. 传参错误
        requestData1.put(Constant.WEIGHT, 60.0);
        Msg result1 = weightController.AddWeight(requestData1, request);
        Msg msg1 = MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{date:2023-04-01, weight:60}", null);
        assertMsgEqual(result1, msg1);

        //2. 日期格式错误
        requestData1.put(Constant.DATE, "2020-");
        Msg result2 = weightController.AddWeight(requestData1, request);
        Msg msg2 = MsgUtil.makeMsg(MsgUtil.ERROR, "日期格式错误{weight:1.0,date:yyyy-MM-dd}", null);
        assertMsgEqual(result2, msg2);

        //3. 同日覆盖
        requestData1.put(Constant.DATE, "2022-07-10");
        Msg result3 = weightController.AddWeight(requestData1, request);
        Msg msg3 = MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(modifiedWeight, new CustomJsonConfig()));
        assertMsgEqual(result3, msg3);

        // 4. 新增
        requestData2.put(Constant.DATE, "2022-07-11");
        requestData2.put(Constant.WEIGHT, 61.0);
        Msg result4 = weightController.AddWeight(requestData2, request);
        Msg msg4 = MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(fakeWeight2, new CustomJsonConfig()));
        assertMsgEqual(result4, msg4);

        verify(weightService, times(1)).addWeight(modifiedWeight);

    }

    @Test
    void getPeriodWeight() {
        // 准备测试数据
        int userId = 1;
        String token = "test_token";
        Map<String, Object> requestData1 = new HashMap<>(), requestData2 = new HashMap<>();
        // 模拟 HttpServletRequest 的行为
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("token")).thenReturn(token);
        // 模拟 tokenService 的行为
        Mockito.when(tokenService.getUserIdFromToken(token)).thenReturn(userId);

        // 模拟 weightService 的行为
        Mockito.when(weightService.getWeightByDate(userId, Date.valueOf("2022-07-10"))).thenReturn(fakeWeight1);
        Mockito.when(weightService.getWeightByDate(userId, Date.valueOf("2022-07-11"))).thenReturn(fakeWeight2);

        // 1. 传参错误
        Msg result1 = weightController.getPeriodWeight(requestData1, request);
        Msg msg1 = MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{start:yyyy-MM-dd,end:yyyy-MM-dd}", null);
        assertMsgEqual(result1, msg1);

        // 2. 日期格式错误
        requestData1.put(Constant.START_DATE, "2022-07-");
        requestData1.put(Constant.END_DATE, "2022-07-13");
        Msg result2 = weightController.getPeriodWeight(requestData1, request);
        Msg msg2 = MsgUtil.makeMsg(MsgUtil.ERROR, "日期格式错误{start:yyyy-MM-dd,end:yyyy-MM-dd}", null);
        assertMsgEqual(result2, msg2);

        // 3. 日期范围错误
        requestData1.put(Constant.START_DATE, "2023-07-13");
        Msg result3 = weightController.getPeriodWeight(requestData1, request);
        Msg msg3 = MsgUtil.makeMsg(MsgUtil.ERROR, "开始日期不能在结束日期之后", null);
        assertMsgEqual(result3, msg3);

        // 4. 日期范围正确
        requestData1.put(Constant.START_DATE, "2022-07-10");
        Msg result4 = weightController.getPeriodWeight(requestData1, request);
        JSONArray jsonArray = JSONArray.fromObject(Arrays.asList(fakeWeight1,fakeWeight2), new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weights",jsonArray);
        Msg msg4 = MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
        assertMsgEqual(result4, msg4);
    }

    private void assertMsgEqual(Msg expected, Msg actual) {
        // 自定义比较器
        boolean areEqual = Objects.equals(expected.getStatus(), actual.getStatus())
                && Objects.equals(expected.getMsg(), actual.getMsg())
                && Objects.equals(expected.getData(), actual.getData());

        // 断言比较结果
        assertTrue(areEqual, "Msg对象的内容不相等");
    }
}