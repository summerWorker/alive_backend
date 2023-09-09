package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.goal.GoalDao;
import com.alive_backend.entity.goal.Goal;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.dao.health_data.WeightDao;
import com.alive_backend.utils.constant.GoalConstant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeightServiceImplTest {
    @Mock
    private WeightDao weightDao;
    @Mock
    private GoalDao goalDao;

    @InjectMocks
    private WeightServiceImpl weightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWeightByDate() {
        // 创建用于测试的假数据
        Weight fakeWeight1 = new Weight(), fakeWeight2 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);
        fakeWeight2.setUserId(1); fakeWeight2.setDate(Date.valueOf("2022-07-11")); fakeWeight2.setWeight(61.0);

        // 指定Mock对象的行为
        when(weightDao.getWeightByDate(1, Date.valueOf("2022-07-10"))).thenReturn(fakeWeight1);
        when(weightDao.getWeightByDate(1,Date.valueOf("2022-07-11"))).thenReturn(fakeWeight2);

        // 执行被测试的方法
        // 非法日期
        Weight weight = weightService.getWeightByDate(1, null);
        assertNull(weight);

        // 非法用户ID
        Weight weight0 = weightService.getWeightByDate(-1, Date.valueOf("2022-07-10"));
        assertNull(weight0);

        // 合法日期和用户ID
        Weight weight1 = weightService.getWeightByDate(1, Date.valueOf("2022-07-10"));
        assertEquals(weight1, fakeWeight1);

        Weight weight2 = weightService.getWeightByDate(1, Date.valueOf("2022-07-11"));
        assertEquals(weight2, fakeWeight2);

        // 验证Mock对象的方法的调用次数
        verify(weightDao, times(3)).getWeightByDate(anyInt(), any(Date.class));
    }

    @Test
    void addWeight() {
        // 创建用于测试的假数据
        Weight fakeWeight1 = new Weight(), fakeWeight2 = new Weight(), fakeWeight3 = new Weight(), fakeWeight_before = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2023-08-10")); fakeWeight1.setWeight(60.0);
        fakeWeight2.setUserId(2); fakeWeight2.setDate(Date.valueOf("2023-08-10")); fakeWeight2.setWeight(60.0);
        fakeWeight3.setUserId(3); fakeWeight3.setDate(Date.valueOf("2023-06-10")); fakeWeight3.setWeight(60.0);
        fakeWeight_before.setUserId(3); fakeWeight_before.setDate(Date.valueOf("2022-07-10")); fakeWeight_before.setWeight(60.0);fakeWeight_before.setGoal(40.0);
        Goal goal = new Goal(), goal3 = new Goal();
        goal.setUserId(1); goal.setGoalName(GoalConstant.WEIGHT_GOAL); goal.setGoalKey1(60.0);goal.setGoalDdl(Date.valueOf("2024-07-10"));
        goal3.setUserId(3); goal3.setGoalName(GoalConstant.WEIGHT_GOAL); goal3.setGoalKey1(40.0);goal3.setGoalDdl(Date.valueOf("2022-07-10"));

        // 指定Mock对象的行为
        when(weightDao.addWeight(eq(fakeWeight1))).thenReturn(fakeWeight1);
        when(weightDao.addWeight(eq(fakeWeight2))).thenReturn(fakeWeight2);
        when(weightDao.addWeight(eq(fakeWeight3))).thenReturn(fakeWeight3);
        when(weightDao.getWeightBeforeDate(eq(3),eq(Date.valueOf("2023-06-10")))).thenReturn(fakeWeight_before);
        when(goalDao.getGoalByGoalName(eq(1), eq(GoalConstant.WEIGHT_GOAL))).thenReturn(goal);
        when(goalDao.getGoalByGoalName(eq(2), eq(GoalConstant.WEIGHT_GOAL))).thenReturn(null);
        when(goalDao.getGoalByGoalName(eq(3), eq(GoalConstant.WEIGHT_GOAL))).thenReturn(goal3);

        // 1. 正常添加
        Weight weight = weightService.addWeight(fakeWeight1);
        assertEquals(weight, fakeWeight1);

        // 2. 没有goal
        Weight weight2 = weightService.addWeight(fakeWeight2);
        assertEquals(weight2, fakeWeight2);

        // 3. 添加过往体重
        Weight weight3 = weightService.addWeight(fakeWeight3);
        assertEquals(weight3, fakeWeight3);

        // 测试goalkey1 ！= null分支
        Weight fakeWeight = new Weight();
        fakeWeight.setUserId(1);
        fakeWeight.setDate(Date.valueOf("2024-08-10"));
        fakeWeight.setWeight(60.0);

        Goal goal2 = new Goal();
        goal2.setUserId(1);
        goal2.setGoalName(GoalConstant.WEIGHT_GOAL);
        goal2.setGoalKey1(70.0); // 设置一个非空的 goalKey1
        goal2.setGoalDdl(Date.valueOf("2025-07-10"));

        // 指定Mock对象的行为
        when(weightDao.addWeight(eq(fakeWeight))).thenReturn(fakeWeight);
        when(goalDao.getGoalByGoalName(eq(1), eq(GoalConstant.WEIGHT_GOAL))).thenReturn(goal);

        // 调用服务方法
        Weight weight0 = weightService.addWeight(fakeWeight);

        // 验证返回的Weight对象是否符合预期
        assertEquals(weight0, fakeWeight);



        // 验证Mock对象的方法是否被调用
        verify(weightDao, times(1)).addWeight(eq(fakeWeight1));
    }

    @Test
    void getWeightByUser() {
        // 创建用于测试的假数据
        Weight fakeWeight1 = new Weight(), fakeWeight2 = new Weight(), fakeWeight3 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);
        fakeWeight2.setUserId(1); fakeWeight2.setDate(Date.valueOf("2022-07-11")); fakeWeight2.setWeight(61.0);
        fakeWeight3.setUserId(1); fakeWeight3.setDate(Date.valueOf("2022-07-12")); fakeWeight3.setWeight(62.0);
        List<Weight> fakeWeights = Arrays.asList(
                fakeWeight1, fakeWeight2, fakeWeight3
        );
        // 指定Mock对象的行为
        when(weightDao.getWeightByUser(1)).thenReturn(fakeWeights);

        // 执行被测试的方法
        // 非法用户ID
        List<Weight> weights0 = weightService.getWeightByUser(-1);
        assertTrue(weights0.isEmpty());

        // 合法用户ID
        List<Weight> weights = weightService.getWeightByUser(1);
        assertEquals(weights, fakeWeights);

        // 验证Mock对象的方法是否被调用
        verify(weightDao, times(2)).getWeightByUser(anyInt());
    }

    @Test
    void getLatestWeight() {
        // 创建用于测试的假数据
        Weight fakeWeight1 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);

        // 指定Mock对象的行为
        when(weightDao.getLatestWeight(eq(1))).thenReturn(fakeWeight1);


        Weight weight = weightService.getLatestWeight(1);
        assertEquals(weight, fakeWeight1);

        // 验证Mock对象的方法是否被调用
        verify(weightDao, times(1)).getLatestWeight(anyInt());
    }

    @Test
    void getWeightBeforeDate() {
        // 创建用于测试的假数据
        Weight fakeWeight1 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);

        // 指定Mock对象的行为
        when(weightDao.getWeightBeforeDate(eq(1),eq(Date.valueOf("2022-07-10")))).thenReturn(fakeWeight1);

        Weight weight = weightService.getWeightBeforeDate(1, Date.valueOf("2022-07-10"));
        assertEquals(weight, fakeWeight1);

        verify(weightDao, times(1)).getWeightBeforeDate(anyInt(), any(Date.class));
    }
}
