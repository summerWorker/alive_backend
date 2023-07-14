package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.goal.GoalDao;
import com.alive_backend.dao.health_data.WeightDao;
import com.alive_backend.entity.goal.Goal;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.utils.constant.GoalConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {
    @Autowired
    private WeightDao weightDao;
    @Autowired
    private GoalDao goalDao;

    @Override
    public Weight addWeight(Weight weight) {
        Goal goal = goalDao.getGoalByGoalName(weight.getUserId(), GoalConstant.WEIGHT_GOAL);
        if (goal == null) {
            weight.setGoal(-1.0);
            return weightDao.addWeight(weight);
        }
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        Date date = weight.getDate();
        if (date.before(today)) {
            Weight lastWeight = getWeightBeforeDate(weight.getUserId(), date);
            if(lastWeight != null && lastWeight.getGoal() > 0.0)
                weight.setGoal(lastWeight.getGoal());
        }
        else if(goal.getGoalKey1() != null) {
            Date ddl = goal.getGoalDdl();
            if(ddl!=null && today.before(ddl))
                weight.setGoal(goal.getGoalKey1());
        }
        return weightDao.addWeight(weight);
    }
    @Override
    public Weight getWeightByDate(int id, Date date) {
        return weightDao.getWeightByDate(id, date);
    }
    @Override
    public List<Weight> getWeightByUser(int id) {
        return weightDao.getWeightByUser(id);
    }
    @Override
    public Weight getLatestWeight(int id) {
        return weightDao.getLatestWeight(id);
    }
    @Override
    public Weight getWeightBeforeDate(int id, Date date) {
        return weightDao.getWeightBeforeDate(id, date);
    }

}
