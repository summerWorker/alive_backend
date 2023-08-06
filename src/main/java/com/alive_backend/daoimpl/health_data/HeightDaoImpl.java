package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.HeightDao;
import com.alive_backend.entity.health_data.Height;
import com.alive_backend.repository.health_data.HeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class HeightDaoImpl implements HeightDao {
    @Autowired
    private HeightRepository heightRepository;
    @Override
    public Height getHeightByDate(int id, Date date) {
        return heightRepository.findByUserIdAndDate(id,date);
    }
    @Override
    public List<Height> getHeightByUser(int id) {
        return heightRepository.findByUserId(id);
    }
    @Override
    public Height addHeight(Height height) {
        return heightRepository.save(height);
    }
    @Override
    public Height getLatestHeight(int id) {
        return heightRepository.findTopByUserIdOrderByDateDesc(id);
    }
    @Override
    public void addHeight(int user_id, Date date, Double height) {
        Height height0 = new Height();
        height0.setUserId(user_id);
        height0.setDate(date);
        height0.setHeight(height);
        heightRepository.save(height0);
    }

}
