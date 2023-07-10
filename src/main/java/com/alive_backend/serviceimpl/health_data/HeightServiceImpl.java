package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.HeightDao;
import com.alive_backend.entity.health_data.Height;
import com.alive_backend.service.health_data.HeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HeightServiceImpl implements HeightService {
    @Autowired
    private HeightDao heightDao;
    @Override
    public Height getHeightByDate(int id, Date date) {
        return heightDao.getHeightByDate(id,date);
    }
    @Override
    public List<Height> getHeightByUser(int id) {
        return heightDao.getHeightByUser(id);
    }

    @Override
    public Height addHeight(Height height) {
        return heightDao.addHeight(height);
    }
}
