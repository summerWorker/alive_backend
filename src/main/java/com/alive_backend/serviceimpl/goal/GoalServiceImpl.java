package com.alive_backend.serviceimpl.goal;

import com.alive_backend.dao.goal.GoalDao;
import com.alive_backend.entity.goal.Goal;
import com.alive_backend.service.goal.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {
    @Autowired
    private GoalDao goalDao;

    @Override
    public Goal getGoalByGoalName(int userId, String goalName) {
        return goalDao.getGoalByGoalName(userId, goalName);
    }
    @Override
    public Goal setGoal(Goal goal) {
        return goalDao.setGoal(goal);
    }
    @Override
    public List<Goal> getGoalsByUserId(int userId) {
        return goalDao.getGoalsByUserId(userId);
    }
}
