package com.alive_backend.service.basic_data;

import com.alive_backend.entity.basic_data.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseService {
    void addExercise(Exercise exercise);
    Exercise findExerciseByName(String name);

    void deleteExercise(Exercise exercise);

    List<Exercise> findExerciseByUserId(int userId);

    Exercise getExerciseById(UUID exerciseId);
}
