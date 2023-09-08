package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.WorkOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface WorkOutRepository extends JpaRepository<WorkOut, UUID> {
    List<WorkOut> getByUserId(int userId);

    WorkOut getByUserIdAndExerciseIdAndDate(int userId, UUID exerciseId, Date date);

    List<WorkOut> getByUserIdAndDate(int userId, Date date);
    List<WorkOut> getByUserIdAndDateBetween(int userId, Date startDate, Date endDate);
}
