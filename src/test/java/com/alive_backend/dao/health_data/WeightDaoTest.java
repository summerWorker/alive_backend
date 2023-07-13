package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeightDaoTest {
    private WeightDao weightDao;
    private Weight fakeWeight1, fakeWeight2;
    @BeforeEach
    void setUp() {
        fakeWeight1 = new Weight(); fakeWeight2 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);
        fakeWeight2.setUserId(1); fakeWeight2.setDate(Date.valueOf("2022-07-11")); fakeWeight2.setWeight(61.0);

        // 创建一个匿名的子类来测试抽象类行为
        weightDao = new WeightDao() {
            @Override
            public Weight addWeight(Weight weight) {
                return fakeWeight1;
            }

            @Override
            public Weight getWeightByDate(int id, Date date) {
                return fakeWeight1;
            }

            @Override
            public List<Weight> getWeightByUser(int id) {
                return Arrays.asList(fakeWeight1, fakeWeight2);
            }

            @Override
            public Weight getLatestWeight(int id) {
                return fakeWeight1;
            }

            @Override
            public Weight getWeightBeforeDate(int id, Date date) {
                return fakeWeight1;
            }
        };
    }


    @Test
    void addWeight() {
        Weight weight = weightDao.addWeight(fakeWeight1);
        assertEquals(weight, fakeWeight1);
    }

    @Test
    void getWeightByDate() {
        Weight weight = weightDao.getWeightByDate(1, Date.valueOf("2022-07-10"));
        assertEquals(weight, fakeWeight1);
    }

    @Test
    void getWeightByUser() {
        List<Weight> weights = weightDao.getWeightByUser(1);
        assertEquals(weights, Arrays.asList(fakeWeight1, fakeWeight2));
    }

    @Test
    void getLatestWeight() {
        Weight weight = weightDao.getLatestWeight(1);
        assertEquals(weight, fakeWeight1);
    }

    @Test
    void getWeightBeforeDate() {
        Weight weight = weightDao.getWeightBeforeDate(1, Date.valueOf("2022-07-10"));
        assertEquals(weight, fakeWeight1);
    }
}