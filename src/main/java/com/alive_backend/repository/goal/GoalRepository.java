package com.alive_backend.repository.goal;

import com.alive_backend.entity.goal.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal,Integer> {
    List<Goal> findByUserId(int id);
    Goal findByUserIdAndGoalName(int id, String type);
//    Goal findTopByUserIdAndGoalNameAndGoalDdlAfterOrderByGoalDdlAsc(int id, String type, Date date); // 根据userId和goalName，找在date之后最近的goal

}
