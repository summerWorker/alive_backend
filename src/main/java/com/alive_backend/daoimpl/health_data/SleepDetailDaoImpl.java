package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.SleepDetailDao;
import com.alive_backend.entity.health_data.SleepDetail;
import com.alive_backend.repository.health_data.SleepDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class SleepDetailDaoImpl implements SleepDetailDao {
    @Autowired
    private SleepDetailRepository sleepDetailRepository;

    @Override
    public SleepDetail getSleepDetailByDate(int userId, Date date) {
        return sleepDetailRepository.findByUserIdAndDate(userId, date);
    }
    @Override
    public List<SleepDetail> getSleepDetailByUserId(int userId) {
        return sleepDetailRepository.findByUserId(userId);
    }

}
