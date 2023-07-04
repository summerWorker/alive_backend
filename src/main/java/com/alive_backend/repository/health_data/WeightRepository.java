package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight,Integer> {
    Weight findByUserIdAndYearId(int id,int yearId);
}