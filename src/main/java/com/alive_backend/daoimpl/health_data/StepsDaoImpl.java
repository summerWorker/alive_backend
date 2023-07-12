package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.StepsDao;
import com.alive_backend.entity.health_data.Steps;
import com.alive_backend.repository.health_data.StepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class StepsDaoImpl implements StepsDao {
    @Autowired
    private StepsRepository stepsRepository;

    @Override
    public Steps addSteps(Steps steps) {
        return stepsRepository.save(steps);
    }

    @Override
    public void delete(Steps steps) {
        stepsRepository.delete(steps);
    }

    @Override
    public Steps findByUserIdAndDate(int userId, Date date) {
        return stepsRepository.findByUserIdAndDate(userId, date);
    }


    @Override
    public List<Steps> getSteps(int userId, Date startDate, Date endDate) {
        return stepsRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }
}
