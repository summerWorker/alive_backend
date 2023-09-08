package com.alive_backend.repository.basic_data;

import com.alive_backend.entity.basic_data.Exercise;
import com.alive_backend.entity.basic_data.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
    Exercise findByName(String name);
    List<Exercise> findByUserId(int userId);
    Exercise getExerciseById(UUID id);
}
