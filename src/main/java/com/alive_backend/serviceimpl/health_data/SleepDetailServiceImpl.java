package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.SleepDetailDao;
import com.alive_backend.entity.health_data.SleepDetail;
import com.alive_backend.service.health_data.SleepDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SleepDetailServiceImpl implements SleepDetailService {
    @Autowired
    private SleepDetailDao sleepDetailDao;
    @Override
    public List<SleepDetail> getWeekSleepDetail(int userId) {
        return sleepDetailDao.getSleepDetailByUserId(userId);
    }

    @Override
    public SleepDetail getDaySleepDetail(int userId, Date date) {
        return sleepDetailDao.getSleepDetailByDate(userId, date);
    }
}
