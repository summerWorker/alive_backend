package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.HeartRateDao;
import com.alive_backend.entity.health_data.HeartRate;
import com.alive_backend.repository.health_data.HeartRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HeartRateDaoImpl implements HeartRateDao {
    @Autowired
    private HeartRateRepository heartRateRepository;
    @Override
    public List<HeartRate> findHeartRateByUserIdAndTimeStampBetween(int userId, Long timeStamp1, Long timeStamp2){
        return heartRateRepository.findByUserIdAndTimeStampBetween(userId, timeStamp1, timeStamp2);
    }
}
