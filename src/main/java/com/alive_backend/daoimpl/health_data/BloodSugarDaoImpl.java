package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.BloodSugarDao;
import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.repository.health_data.BloodSugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BloodSugarDaoImpl implements BloodSugarDao {
    @Autowired
    private BloodSugarRepository bloodSugarRepository;

    @Override
    public List<BloodSugar> getBloodSugarByDate(int id, Date start, Date end) {
        return bloodSugarRepository.findByUserIdAndDateBetween(id, start, end);
    }

    @Override
    public BloodSugar addBloodSugar(BloodSugar bloodSugar) {
        return bloodSugarRepository.save(bloodSugar);
    }

    @Override
    public void addBloodSugar(int user_id, Date date, Double blood_sugar) {
        BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(user_id);
        bloodSugar.setDate(date);
        bloodSugar.setBloodSugar(blood_sugar);
        bloodSugarRepository.save(bloodSugar);
    }
}
