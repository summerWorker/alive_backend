package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.WeightDao;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.repository.health_data.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class WeightDaoImpl implements WeightDao {
    @Autowired
    private WeightRepository weightRepository;
//    @Override
//    public Weight getWeightByYear(int id, int year) {
//        return weightRepository.findByUserIdAndYearId(id,year);
//    }

    @Override
    public Weight addWeight(Weight weight) {
        return weightRepository.save(weight);
    }
    @Override
    public Weight getWeightByDate(int id, Date date) {
        return weightRepository.findByUserIdAndDate(id,date);
    }
    @Override
    public List<Weight> getWeightByUser(int id) {
        return weightRepository.findByUserId(id);
    }
    @Override
    public Weight getLatestWeight(int id) {
        return weightRepository.findTopByUserIdOrderByDateDesc(id);
    }

}
