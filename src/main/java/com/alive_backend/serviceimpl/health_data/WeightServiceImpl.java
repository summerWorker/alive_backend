package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.WeightDao;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.service.health_data.WeightService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {
    @Autowired
    private WeightDao weightDao;

    @Override
    public List<Weight> getWeight(int id, Date date1, Date date2) {
        List<Weight> weightList = new ArrayList<>();
        int year1, year2;
        try {
            year1 = date1.getYear();
            year2 = date2.getYear();
        } catch (Exception e) {
            throw new RuntimeException("日期格式错误");
        }
        for (int i = year1; i <= year2; i++) {
            Weight weight = weightDao.getWeightByYear(id, i);
            weightList.add(weight);
        }
        for(Weight weight:weightList){
            if(weight==null)
                continue;
            JSONObject jsonObject = JSONObject.fromObject(weight.getDetailValue());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            JSONArray updatedJsonArray = new JSONArray();
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String date = jsonObject1.getString("date");
                Date date_ = Date.valueOf(date);
                if(date_.compareTo(date1)>=0 && date_.compareTo(date2)<=0)
                    updatedJsonArray.add(jsonObject1);
            }
            jsonObject.put("items",updatedJsonArray);
            weight.setDetailValue(jsonObject.toString());
        }
        return weightList;
    }

}
