package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.BloodPressure;

import java.sql.Date;

public interface BloodPressureService {
    BloodPressure getBloodPressureByDate(int userId, Date date);

    BloodPressure addBloodPressure(BloodPressure bloodPressure);
}
