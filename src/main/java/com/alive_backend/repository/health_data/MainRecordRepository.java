package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.MainRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRecordRepository extends JpaRepository<MainRecord, String> {
    MainRecord findByUserId(int id);
}
