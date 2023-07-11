package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.BloodPressureDao;
import com.alive_backend.entity.health_data.BloodPressure;
import com.alive_backend.repository.health_data.BloodPressureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class BloodPressureDaoImpl implements BloodPressureDao {
    @Autowired
    private BloodPressureRepository bloodPressureRepository;

    @Override
    public BloodPressure getBloodPressureByDate(int userId, Date date) {
        return bloodPressureRepository.findByUserIdAndDate(userId, date);
    }

    @Override
    public BloodPressure addBloodPressure(BloodPressure bloodPressure) {
        return bloodPressureRepository.save(bloodPressure);
    }
}
