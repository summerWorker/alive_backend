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
    public List<SleepDetail> getSleepDetailByDate(int userId, Date date1, Date date2) {
        return sleepDetailRepository.findByUserIdAndDateBetween(userId, date1, date2);
    }
    @Override
    public List<SleepDetail> getSleepDetailByUserId(int userId) {
        return sleepDetailRepository.findByUserId(userId);
    }

    @Override
    public SleepDetail saveSleepDetail(SleepDetail sleepDetail) {
        try {
            return sleepDetailRepository.save(sleepDetail);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
