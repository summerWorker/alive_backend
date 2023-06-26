package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.repository.health_data.MainRecordRepository;
import com.alive_backend.service.health_data.MainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MainRecordServiceImpl implements MainRecordService {
    @Autowired
    private MainRecordRepository mainRecordRepository;
    @Override
    public MainRecord getMainRecordByUserId(int id) {
        return mainRecordRepository.findByUserId(id);
    }
}
