package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.HeartRateDao;
import com.alive_backend.entity.health_data.HeartRate;
import com.alive_backend.service.health_data.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class HeartRateServiceImpl implements HeartRateService {
    @Autowired
    private HeartRateDao heartRateDao;
    @Override
    public List<HeartRate> findHeartRateByUserIdAndDate(int userId, Date date) {
        Long timeStamp1 = date.getTime();
        Long timeStamp2 = timeStamp1 + 86400000;
        return heartRateDao.findHeartRateByUserIdAndTimeStampBetween(userId, timeStamp1, timeStamp2);
    }
    @Override
    public List<HeartRate> findHeartRateByUserIdAndDateBetween(int userId, Date date1, Date date2) {
        Long timeStamp1 = date1.getTime();
        Long timeStamp2 = date2.getTime() + 86400000;
        return heartRateDao.findHeartRateByUserIdAndTimeStampBetween(userId, timeStamp1, timeStamp2);
    }
}
