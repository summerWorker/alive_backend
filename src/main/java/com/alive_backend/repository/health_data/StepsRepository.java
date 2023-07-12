package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.Steps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface StepsRepository extends JpaRepository<Steps, Integer> {
    Steps findByUserIdAndDate(int userId, Date date);
    List<Steps> findByUserIdAndDateBetween(int userId, Date startDate, Date endDate);
}
