package com.alive_backend.dao.health_data;


import com.alive_backend.entity.health_data.HeartRate;

import java.util.List;

public interface HeartRateDao {
    List<HeartRate> findHeartRateByUserIdAndTimeStampBetween(int userId, Long timeStamp1, Long timeStamp2);
}
