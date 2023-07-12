package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.BloodSugar;

import java.util.Date;
import java.util.List;

public interface BloodSugarDao {
    List<BloodSugar> getBloodSugarByDate(int id, Date start, Date end);

    BloodSugar addBloodSugar(BloodSugar bloodSugar);
}
