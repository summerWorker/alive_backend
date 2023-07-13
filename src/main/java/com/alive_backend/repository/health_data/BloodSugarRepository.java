package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.BloodSugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BloodSugarRepository extends JpaRepository<BloodSugar, Integer> {
    BloodSugar findByUserIdAndDate(int userId, Date date);

    List<BloodSugar> findByUserIdAndDateBetween(int userId, Date start, Date end);
}
