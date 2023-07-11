package com.alive_backend.daoimpl.goal;

import com.alive_backend.dao.goal.GoalDao;
import com.alive_backend.entity.goal.Goal;
import com.alive_backend.repository.goal.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class GoalDaoImpl implements GoalDao {
    @Autowired
    private GoalRepository goalRepository;

    @Override
    public Goal getGoalByGoalName(int userId, String goalName) {
        return goalRepository.findByUserIdAndGoalName(userId, goalName);
    }
    @Override
    public List<Goal> getGoalsByUserId(int userId) {
        return goalRepository.findByUserId(userId);
    }
    @Override
    public Goal setGoal(Goal goal) {
        return goalRepository.save(goal);
    }
}
