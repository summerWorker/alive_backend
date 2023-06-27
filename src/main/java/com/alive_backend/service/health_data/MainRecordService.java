package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.MainRecord;

public interface MainRecordService {
    MainRecord getMainRecordByUserId(int id);
    MainRecord updateMainRecord(MainRecord mainRecord);
}
