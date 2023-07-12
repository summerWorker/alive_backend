package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.BloodPressure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressure, Integer> {
    BloodPressure findByUserIdAndDate(int id, Date date);
}
