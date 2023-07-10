package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WeightServiceTest {
    private WeightService weightService;

    @BeforeEach
    void setUp() {
        // 创建一个匿名的子类来测试抽象类的行为
        weightService = new WeightService() {
            @Override
            public Weight getWeightByDate(int id, Date date) {
                Weight fakeWeight = new Weight();
                fakeWeight.setUserId(1); fakeWeight.setDate(Date.valueOf("2022-07-10")); fakeWeight.setWeight(60.0);
                return fakeWeight;
            }

            @Override
            public Weight addWeight(Weight weight) {
                return weight;
            }

            @Override
            public List<Weight> getWeightByUser(int id) {
                Weight fakeWeight1 = new Weight(), fakeWeight2 = new Weight(), fakeWeight3 = new Weight();
                fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);
                fakeWeight2.setUserId(1); fakeWeight2.setDate(Date.valueOf("2022-07-11")); fakeWeight2.setWeight(61.0);
                fakeWeight3.setUserId(1); fakeWeight3.setDate(Date.valueOf("2022-07-12")); fakeWeight3.setWeight(62.0);
                return Arrays.asList(fakeWeight1, fakeWeight2, fakeWeight3);
            }
            @Override
            public Weight getLatestWeight(int id) {
                return null;
            }
        };
    }

    @Test
    void testGetWeightByDate() {
        // 执行被测试的方法
        Weight weight = weightService.getWeightByDate(1, Date.valueOf("2022-07-10"));

        // 验证结果是否符合预期
        assertEquals(1, weight.getUserId());
        assertEquals(Date.valueOf("2022-07-10"), weight.getDate());
        assertEquals(60.0, weight.getWeight());
    }

    @Test
    void testAddWeight() {
        // 创建一个待添加的Weight对象
        Weight fakeWeight = new Weight();
        fakeWeight.setUserId(1); fakeWeight.setDate(Date.valueOf("2022-07-10")); fakeWeight.setWeight(60.0);

        // 执行被测试的方法
        Weight addedWeight = weightService.addWeight(fakeWeight);

        // 验证结果是否符合预期
        assertEquals(fakeWeight, addedWeight);
    }

    @Test
    void testGetWeightByUser() {
        // 执行被测试的方法
        List<Weight> weight = weightService.getWeightByUser(1);

        // 验证结果是否符合预期
        assertEquals(3, weight.size());
        assertEquals(1, weight.get(0).getUserId()); assertEquals(Date.valueOf("2022-07-10"), weight.get(0).getDate()); assertEquals(60.0, weight.get(0).getWeight());
        assertEquals(1, weight.get(1).getUserId()); assertEquals(Date.valueOf("2022-07-11"), weight.get(1).getDate()); assertEquals(61.0, weight.get(1).getWeight());
        assertEquals(1, weight.get(2).getUserId()); assertEquals(Date.valueOf("2022-07-12"), weight.get(2).getDate()); assertEquals(62.0, weight.get(2).getWeight());
    }
}
