package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.MainRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRecordRepository extends JpaRepository<MainRecord, Integer> {
    MainRecord findByUserId(int id);
}
