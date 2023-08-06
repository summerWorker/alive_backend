package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.BloodPressure;

import java.util.Date;

public interface BloodPressureDao {
    BloodPressure getBloodPressureByDate(int userId, Date date);

    BloodPressure addBloodPressure(BloodPressure bloodPressure);

    void addBloodPressure(int userId, Date date, int systolic, int diastolic);
}
