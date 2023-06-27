package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.MainRecord;

public interface MainRecordDao {
    MainRecord getMainRecordByUserId(int id);
    MainRecord updateMainRecord(MainRecord mainRecord);
}
