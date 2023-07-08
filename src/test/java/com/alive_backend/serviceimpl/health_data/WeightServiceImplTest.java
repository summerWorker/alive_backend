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
    void getWeight() {
        // 创建用于测试的假数据
        Weight fakeWeight_2022 =new Weight(1, 2022, "{\"items\":[{\"date\":\"2022-07-10\",\"weight\":60.0}]}");
        Weight fakeWeight_2023 =new Weight(1, 2023, "{\"items\":[{\"date\":\"2023-06-11\",\"weight\":61.0}]}");
        List<Weight> fakeWeights = Arrays.asList(
                fakeWeight_2022,
                fakeWeight_2023
        );

        // 指定Mock对象的行为
        when(weightDao.getWeightByYear(anyInt(), anyInt())).thenReturn(null);
        when(weightDao.getWeightByYear(1, 2022)).thenReturn(fakeWeight_2022);
        when(weightDao.getWeightByYear(1, 2023)).thenReturn(fakeWeight_2023);

        // 执行被测试的方法
        List<Weight> weights = weightService.getWeight(1, null, null);
        assertNull(weights);

        List<Weight> weights1 = weightService.getWeight(1, Date.valueOf("2022-07-10"), Date.valueOf("2023-07-10"));
        assertEquals(weights1, fakeWeights);

        // 验证Mock对象的方法的调用次数
        verify(weightDao, times(2)).getWeightByYear(anyInt(), anyInt());
    }

    @Test
    void addWeight() {
        // 创建用于测试的假数据
        Weight addWeight = new Weight();
        addWeight.setUserId(1); addWeight.setYearId(1900); addWeight.setDetailValue("{\"items\":[{\"date\":\"2022-06-10\",\"weight\":59.1}]}");

        // 指定Mock对象的行为
        when(weightDao.addWeight(eq(addWeight))).thenReturn(addWeight);

        // 执行被测试的方法
        Weight weight = weightService.addWeight(addWeight);
        assertEquals(weight, addWeight);

        // 验证Mock对象的方法是否被调用
        verify(weightDao, times(1)).addWeight(eq(addWeight));
    }

    @Test
    void getWeightByYear() {
        // 创建用于测试的假数据
        Weight weight2022 = new Weight(1, 2022, "{\"items\":[{\"date\":\"2022-07-10\",\"weight\":60.0}]}");

        // 指定Mock对象的行为
        when(weightDao.getWeightByYear(1, 2022)).thenReturn(weight2022);
        when(weightDao.getWeightByYear(3, 2023)).thenReturn(null);
        when(weightDao.getWeightByYear(1, 1999)).thenReturn(null);

        // 执行被测试的方法
        Weight weight = weightService.getWeightByYear(1, 2022);
        assertEquals(2022, weight.getYearId());

        Weight weight1 = weightService.getWeightByYear(3, 2023);
        assertNull(weight1);

        Weight weight2 = weightService.getWeightByYear(1, 1999);
        assertNull(weight2);

        // 验证Mock对象的方法是否被调用
        verify(weightDao, times(1)).getWeightByYear(1, 2022);
        verify(weightDao, times(1)).getWeightByYear(3, 2023);
        verify(weightDao, times(1)).getWeightByYear(1, 1999);
    }
}
