package com.alive_backend.service.health_data;


import com.alive_backend.entity.health_data.WorkOut;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface WorkOutService {
    WorkOut addWorkOut(WorkOut workOut);

    WorkOut findWorkOutByUserIdAndExerciseIdAndDate(int userId, UUID foodId, Date date);

    void updateWorkOut(WorkOut workOut);

    List<WorkOut> findWorkOutByUserIdAndDate(int userId, Date date);
    List<WorkOut> findWorkOutByUserIdAndDateBetween(int userId, Date startDate, Date endDate);
}
