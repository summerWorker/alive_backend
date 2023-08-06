package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.Weight;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WeightService {
//    List<Weight> getWeight(int id, Date date1, Date date2);
    Weight addWeight(Weight weight);
//    Weight getWeightByYear(int id, int year);
    Weight getWeightByDate(int id, Date date);
    List<Weight> getWeightByUser(int id);
    Weight getLatestWeight(int id);
    Weight getWeightBeforeDate(int id, Date date);
    void addWeight(int user_id, Date date, Map<String, Object> data);
}
