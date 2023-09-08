package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.WorkOutDao;
import com.alive_backend.entity.health_data.WorkOut;
import com.alive_backend.service.health_data.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WorkOutServiceImpl implements WorkOutService {
    @Autowired
    private WorkOutDao workOutDao;

    @Override
    public WorkOut addWorkOut(WorkOut workOut) {
        return workOutDao.addWorkOut(workOut);
    }

    @Override
    public WorkOut findWorkOutByUserIdAndExerciseIdAndDate(int userId, UUID foodId, Date date) {
        return workOutDao.findWorkOutByUserIdAndExerciseIdAndDate(userId, foodId, date);
    }

    @Override
    public void updateWorkOut(WorkOut workOut) {
        workOutDao.updateWorkOut(workOut);
    }

    @Override
    public List<WorkOut> findWorkOutByUserIdAndDate(int userId, Date date) {
        return workOutDao.findWorkOutByUserIdAndDate(userId, date);
    }

    @Override
    public List<WorkOut> findWorkOutByUserIdAndDateBetween(int userId, Date startDate, Date endDate) {
        return workOutDao.findWorkOutByUserIdAndDateBetween(userId, startDate, endDate);
    }
}
