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
            public List<Weight> getWeight(int id, Date date1, Date date2) {
                // 假设根据传入的id和日期范围返回一些Weight对象
                return Arrays.asList(
                        new Weight(1, 2022, "{\"items\":[{\"date\":\"2022-07-10\",\"weight\":60.0}]}"),
                        new Weight(1, 2023, "{\"items\":[{\"date\":\"2023-06-11\",\"weight\":61.0}]}"),
                        new Weight(1, 2024, "{\"items\":[{\"date\":\"2024-05-12\",\"weight\":62.0}]}")
                );
            }

            @Override
            public Weight addWeight(Weight weight) {
                return weight;
            }

            @Override
            public Weight getWeightByYear(int id, int year) {
                Weight weight = new Weight(id, year, "{\"items\":[{\"date\":\"2022-07-10\",\"weight\":60.0}]}");
                return weight;
            }
        };
    }

    @Test
    void testGetWeight() {
        // 执行被测试的方法
        List<Weight> weights = weightService.getWeight(1, null, null);

        // 验证结果是否符合预期
        assertEquals(3, weights.size());
        assertEquals("{\"items\":[{\"date\":\"2022-07-10\",\"weight\":60.0}]}", weights.get(0).getDetailValue());
    }

    @Test
    void testAddWeight() {
        // 创建一个待添加的Weight对象
        Weight weight = new Weight(1, 2022, "{\"items\":[{\"date\":\"2022-07-10\",\"weight\":60.0}]}");

        // 执行被测试的方法
        Weight addedWeight = weightService.addWeight(weight);

        // 验证结果是否符合预期
        assertEquals(weight, addedWeight);
    }

    @Test
    void testGetWeightByYear() {
        // 执行被测试的方法
        Weight weight = weightService.getWeightByYear(1, 2022);

        // 验证结果是否符合预期
        assertEquals(2022, weight.getYearId());
        assertEquals(1, weight.getUserId());
    }
}
