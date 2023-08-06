package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.BloodPressure;

import java.util.Date;
import java.util.Map;

public interface BloodPressureService {
    BloodPressure getBloodPressureByDate(int userId, Date date);

    BloodPressure addBloodPressure(BloodPressure bloodPressure);

    void addBloodPressure(int userId, Date date, Map<String, Object> data);
}
