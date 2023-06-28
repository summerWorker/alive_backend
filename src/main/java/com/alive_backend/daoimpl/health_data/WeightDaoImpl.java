package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.WeightDao;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.repository.health_data.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeightDaoImpl implements WeightDao {
    @Autowired
    private WeightRepository weightRepository;
    @Override
    public Weight getWeightByYear(int id, int year) {
        return weightRepository.findByUserIdAndYearId(id,year);
    }

}
