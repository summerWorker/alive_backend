package com.alive_backend.dao.health_data;


import com.alive_backend.entity.health_data.WorkOut;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface WorkOutDao {
    List<WorkOut> getWorkOutByUserId(int userId);
    WorkOut addWorkOut(WorkOut workOut);
    void deleteWorkOut(WorkOut workOut);

    WorkOut findWorkOutByUserIdAndExerciseIdAndDate(int userId, UUID exerciseId, Date date);

    void updateWorkOut(WorkOut workOut);

    List<WorkOut> findWorkOutByUserIdAndDate(int userId, Date date);
    List<WorkOut> findWorkOutByUserIdAndDateBetween(int userId, Date startDate, Date endDate);
}
