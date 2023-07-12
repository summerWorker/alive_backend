package com.alive_backend.repository.health_data;
import com.alive_backend.entity.health_data.HeartRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface HeartRateRepository extends JpaRepository<HeartRate,Integer> {
    List<HeartRate>findByUserIdAndTimeStampBetween(int userId, Long timeStamp1, Long timeStamp2);

}
