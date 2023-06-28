package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.SleepDetail;

import java.sql.Date;
import java.util.List;

public interface SleepDetailDao {
    List<SleepDetail> getSleepDetailByUserId(int userId);
    List<SleepDetail> getSleepDetailByDate(int userId, Date date,Date date2);
    SleepDetail saveSleepDetail(SleepDetail sleepDetail);
}
