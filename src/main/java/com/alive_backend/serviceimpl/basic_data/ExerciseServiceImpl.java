package com.alive_backend.serviceimpl.basic_data;

import com.alive_backend.dao.basic_data.ExerciseDao;
import com.alive_backend.entity.basic_data.Exercise;
import com.alive_backend.service.basic_data.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseDao exerciseDao;


    @Override
    public void addExercise(Exercise exercise) {
        exerciseDao.addExercise(exercise);
    }

    @Override
    public Exercise findExerciseByName(String name) {
        return exerciseDao.findExerciseByName(name);
    }

    @Override
    public void deleteExercise(Exercise exercise) {
        exerciseDao.deleteExercise(exercise);
    }

    @Override
    public List<Exercise> findExerciseByUserId(int userId) {
        return exerciseDao.findExerciseByUserId(userId);
    }

    @Override
    public Exercise getExerciseById(UUID exerciseId) {
        return exerciseDao.getExerciseById(exerciseId);
    }
}
