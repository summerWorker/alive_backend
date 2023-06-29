package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.WeightDao;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.service.health_data.WeightService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {
    @Autowired
    private WeightDao weightDao;

    @Override
    public List<Weight> getWeight(int id, Date date1, Date date2) {
        List<Weight> weightList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        int year1 = c.get(Calendar.YEAR);
        c.setTime(date2);
        int year2 = c.get(Calendar.YEAR);

        for (int i = year1; i <= year2; i++) {
            Weight weight = weightDao.getWeightByYear(id, i);
            weightList.add(weight);
        }
        for(Weight weight:weightList){
            if(weight==null)
                continue;
            JSONObject jsonObject = JSONObject.fromObject(weight.getDetailValue());
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("item"));
            JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("item"));
            JSONArray updatedJsonArray = new JSONArray();
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String date = jsonObject1.getString("date");
                Date date_ = Date.valueOf(date);
                if(date_.compareTo(date1)>=0 && date_.compareTo(date2)<=0)
                    updatedJsonArray.add(jsonObject1);
            }
            jsonObject.put("item",updatedJsonArray);
            weight.setDetailValue(jsonObject.toString());
        }
        return weightList;
    }

}
