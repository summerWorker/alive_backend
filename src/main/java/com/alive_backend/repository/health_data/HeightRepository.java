package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.Height;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HeightRepository extends JpaRepository<Height,Integer> {
    Height findByUserIdAndDate(int id, Date date);
    List<Height> findByUserId(int id);
    Height findTopByUserIdOrderByDateDesc(int id);
}
