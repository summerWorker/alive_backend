package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.MainRecordDao;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.repository.health_data.MainRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainRecordDaoImpl implements MainRecordDao {
    @Autowired
    private MainRecordRepository mainRecordRepository;
    @Override
    public MainRecord getMainRecordByUserId(int id) {
        return mainRecordRepository.findByUserId(id);
    }
    @Override
    public MainRecord updateMainRecord(MainRecord mainRecord) {
        return mainRecordRepository.save(mainRecord);
    }
}
