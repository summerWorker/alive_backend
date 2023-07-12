package com.alive_backend;

import com.alive_backend.entity.health_data.Steps;
import com.alive_backend.repository.health_data.StepsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AliveBackendApplicationTests {

    @Autowired
    private StepsRepository stepsRepository;

    @Test
    void contextLoads() {
        Steps steps = new Steps();
        steps.setDate(java.sql.Date.valueOf("2021-01-01"));
        steps.setUserId(1);
        steps.setValue(1000);
        stepsRepository.save(steps);
    }
}
