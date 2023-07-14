package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.HeartRate;

import java.sql.Date;
import java.util.List;

public interface HeartRateService {
    List<HeartRate> findHeartRateByUserIdAndDate(int userId, Date date);
    List<HeartRate> findHeartRateByUserIdAndDateBetween(int userId, Date date1, Date date2);
    void addHeartRate(HeartRate newHeartRate);
}
