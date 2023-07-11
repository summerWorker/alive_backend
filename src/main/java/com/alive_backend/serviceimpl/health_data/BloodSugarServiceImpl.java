package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.BloodSugarDao;
import com.alive_backend.service.health_data.BloodSugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodSugarServiceImpl implements BloodSugarService {
    @Autowired
    private BloodSugarDao bloodSugarDao;


}
