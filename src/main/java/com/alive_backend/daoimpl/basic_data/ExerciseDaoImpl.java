package com.alive_backend.daoimpl.basic_data;

import com.alive_backend.dao.basic_data.ExerciseDao;
import com.alive_backend.entity.basic_data.Exercise;
import com.alive_backend.repository.basic_data.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ExerciseDaoImpl implements ExerciseDao {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public void addExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public Exercise findExerciseByName(String name) {
        return exerciseRepository.findByName(name);
    }

    @Override
    public void deleteExercise(Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    @Override
    public List<Exercise> findExerciseByUserId(int userId) {
        return exerciseRepository.findByUserId(userId);
    }

    @Override
    public Exercise getExerciseById(UUID id) {
        return exerciseRepository.getExerciseById(id);
    }
}
