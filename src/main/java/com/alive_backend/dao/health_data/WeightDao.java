package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.Weight;

import java.util.Date;
import java.util.List;

public interface WeightDao {
//    Weight getWeightByYear(int id, int year);
    Weight addWeight(Weight weight);
    Weight getWeightByDate(int id, Date date);
    List<Weight> getWeightByUser(int id);
    Weight getLatestWeight(int id);
    Weight getWeightBeforeDate(int id, Date date);

    void addWeight(int user_id, Date date, Double weight);
}
