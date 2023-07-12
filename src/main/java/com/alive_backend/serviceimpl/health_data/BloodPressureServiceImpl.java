package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.BloodPressureDao;
import com.alive_backend.entity.health_data.BloodPressure;
import com.alive_backend.service.health_data.BloodPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class BloodPressureServiceImpl implements BloodPressureService {
    @Autowired
    private BloodPressureDao bloodPressureDao;

    @Override
    public BloodPressure getBloodPressureByDate(int userId, Date date) {
        return bloodPressureDao.getBloodPressureByDate(userId, date);
    }

    @Override
    public BloodPressure addBloodPressure(BloodPressure bloodPressure) {
        return bloodPressureDao.addBloodPressure(bloodPressure);
    }
}
