package com.alive_backend.service.goal;

import com.alive_backend.entity.goal.Goal;

import java.sql.Date;
import java.util.List;

public interface GoalService {
    Goal getGoalByGoalName(int userId, String goalName);
    Goal setGoal(Goal goal);
    List<Goal> getGoalsByUserId(int userId);
}
