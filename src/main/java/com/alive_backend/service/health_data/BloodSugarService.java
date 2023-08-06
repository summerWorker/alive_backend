package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.BloodPressure;
import com.alive_backend.entity.health_data.BloodSugar;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BloodSugarService {
    List<BloodSugar> getBloodSugarByDate(int id, Date start, Date end);

    BloodSugar addBloodSugar(BloodSugar bloodSugar);

    void addBloodSugar(int user_id, Date date, Map<String, Object> blood_sugar_data);
}
