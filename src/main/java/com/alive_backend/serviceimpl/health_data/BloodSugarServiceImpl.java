package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.BloodSugarDao;
import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.service.health_data.BloodSugarService;
import com.alive_backend.utils.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public void addBloodSugar(int user_id, Date date, Map<String, Object> blood_sugar_data) {
        Double blood_sugar = Double.parseDouble(String.valueOf(blood_sugar_data.get(Constant.BLOOD_SUGAR)));
        bloodSugarDao.addBloodSugar(user_id, date, blood_sugar);
    }
}
