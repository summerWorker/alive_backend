package com.alive_backend.repository.health_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alive_backend.entity.health_data.SleepDetail;

import java.sql.Date;
import java.util.List;

@Repository
public interface SleepDetailRepository extends JpaRepository<SleepDetail, Integer> {
    List<SleepDetail> findByUserId(int id);
    SleepDetail findByUserIdAndDate(int userId, Date date);
}
