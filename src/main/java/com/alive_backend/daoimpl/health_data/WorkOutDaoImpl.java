package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.WorkOutDao;
import com.alive_backend.entity.health_data.WorkOut;
import com.alive_backend.repository.health_data.WorkOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class WorkOutDaoImpl implements WorkOutDao {
    @Autowired
    private WorkOutRepository workOutRepository;

    @Override
    public List<WorkOut> getWorkOutByUserId(int userId) {
        return workOutRepository.getByUserId(userId);
    }

    @Override
    public WorkOut addWorkOut(WorkOut workOut) {
        return workOutRepository.save(workOut);
    }

    @Override
    public void deleteWorkOut(WorkOut workOut) {
        workOutRepository.delete(workOut);
    }

    @Override
    public WorkOut findWorkOutByUserIdAndExerciseIdAndDate(int userId, UUID exerciseId, Date date) {
        return workOutRepository.getByUserIdAndExerciseIdAndDate(userId, exerciseId, date);
    }

    @Override
    public void updateWorkOut(WorkOut workOut) {
        workOutRepository.save(workOut);
    }

    @Override
    public List<WorkOut> findWorkOutByUserIdAndDate(int userId, Date date) {
        return workOutRepository.getByUserIdAndDate(userId, date);
    }

    @Override
    public List<WorkOut> findWorkOutByUserIdAndDateBetween(int userId, Date startDate, Date endDate) {
        return workOutRepository.getByUserIdAndDateBetween(userId, startDate, endDate);
    }
}
