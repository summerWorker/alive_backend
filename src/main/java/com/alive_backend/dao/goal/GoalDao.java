package com.alive_backend.dao.goal;

import com.alive_backend.entity.goal.Goal;

import java.sql.Date;
import java.util.List;

public interface GoalDao {
    Goal getGoalByGoalName(int userId, String goalName);
    List<Goal> getGoalsByUserId(int userId);
    Goal setGoal(Goal goal);
}
