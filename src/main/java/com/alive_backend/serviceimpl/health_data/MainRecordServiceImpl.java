package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.MainRecordDao;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.service.health_data.MainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainRecordServiceImpl implements MainRecordService {
    @Autowired
    private MainRecordDao mainRecordDao;
    @Override
    public MainRecord getMainRecordByUserId(int id) {
        return mainRecordDao.getMainRecordByUserId(id);
    }
    @Override
    public MainRecord updateMainRecord(MainRecord mainRecord) {
        return mainRecordDao.updateMainRecord(mainRecord);
    }
}
