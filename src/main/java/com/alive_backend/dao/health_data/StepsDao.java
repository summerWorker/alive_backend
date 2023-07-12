package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.Steps;

import java.sql.Date;
import java.util.List;

public interface StepsDao {
    Steps addSteps(Steps steps);
    void delete(Steps steps);
    Steps findByUserIdAndDate(int userId, Date date);

    List<Steps> getSteps(int userId, Date startDate, Date endDate);
}
