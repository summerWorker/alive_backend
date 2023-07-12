package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.StepsDao;
import com.alive_backend.entity.health_data.Steps;
import com.alive_backend.service.health_data.StepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class StepsServiceImpl implements StepsService {
    @Autowired
    private StepsDao stepsDao;

    @Override
    public Steps addSteps(Steps steps) {
        return stepsDao.addSteps(steps);
    }

    @Override
    public Steps findByUserIdAndDate(int userId, Date date) {
        return stepsDao.findByUserIdAndDate(userId, date);
    }

    @Override
    public void delete(Steps steps1) {
        stepsDao.delete(steps1);
    }

    @Override
    public List<Steps> getSteps(int userId, Date startDate, Date endDate) {
        return stepsDao.getSteps(userId, startDate, endDate);
    }
}
