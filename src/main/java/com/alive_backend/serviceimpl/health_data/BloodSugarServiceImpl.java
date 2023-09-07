package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.BloodSugarDao;
import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.service.health_data.BloodSugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BloodSugarServiceImpl implements BloodSugarService {
    @Autowired
    private BloodSugarDao bloodSugarDao;

    @Override
    public List<BloodSugar> getBloodSugarByDate(int id, Date start, Date end) {
        return bloodSugarDao.getBloodSugarByDate(id, start, end);
    }

    @Override
    public BloodSugar addBloodSugar(BloodSugar bloodSugar) {
        return bloodSugarDao.addBloodSugar(bloodSugar);
    }

    @Override
    public BloodSugar getLatestBloodSugar(int id) {
        return bloodSugarDao.getLatestBloodSugar(id);
    }
}
