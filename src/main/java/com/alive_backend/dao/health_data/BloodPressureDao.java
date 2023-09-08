package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.BloodPressure;

import java.sql.Date;

public interface BloodPressureDao {
    BloodPressure getBloodPressureByDate(int userId, Date date);

    BloodPressure addBloodPressure(BloodPressure bloodPressure);

    BloodPressure getLatestBloodPressure(int userId);
}
