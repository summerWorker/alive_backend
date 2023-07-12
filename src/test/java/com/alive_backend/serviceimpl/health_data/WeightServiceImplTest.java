package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.dao.health_data.WeightDao;
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

class WeightServiceImplTest {
    @Mock
    private WeightDao weightDao;

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
        Weight fakeWeight1 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);

        // 指定Mock对象的行为
        when(weightDao.addWeight(eq(fakeWeight1))).thenReturn(fakeWeight1);

        // 执行被测试的方法
        Weight weight = weightService.addWeight(fakeWeight1);
        assertEquals(weight, fakeWeight1);

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
}
