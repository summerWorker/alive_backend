package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.Steps;

import java.sql.Date;
import java.util.List;

public interface StepsService {
    Steps addSteps(Steps steps);

    Steps findByUserIdAndDate(int userId, Date date);

    void delete(Steps steps1);

    List<Steps> getSteps(int userId, Date startDate, Date endDate);
}
