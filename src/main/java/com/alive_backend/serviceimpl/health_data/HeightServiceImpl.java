package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.HeightDao;
import com.alive_backend.entity.health_data.Height;
import com.alive_backend.service.health_data.HeightService;
import com.alive_backend.utils.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Override
    public Height getLatestHeight(int id) {
        return heightDao.getLatestHeight(id);
    }

    @Override
    public void addHeight(int user_id, Date date, Map<String, Object> data) {
        Double height = Double.parseDouble(String.valueOf(data.get(Constant.HEIGHT)));
        //同日覆盖
        Height height0 = getHeightByDate(user_id, date);
        if(height0 != null){
            height0.setHeight(height);
            try {
                addHeight(height0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try{
                heightDao.addHeight(user_id, date, height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
