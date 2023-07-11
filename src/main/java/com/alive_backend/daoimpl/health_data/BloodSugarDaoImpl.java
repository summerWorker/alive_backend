package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.BloodSugarDao;
import com.alive_backend.repository.health_data.BloodSugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BloodSugarDaoImpl implements BloodSugarDao {
    @Autowired
    private BloodSugarRepository bloodSugarRepository;
}
