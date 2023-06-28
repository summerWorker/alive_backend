package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.SleepDetail;

import java.sql.Date;
import java.util.List;

public interface SleepDetailService {
    List<SleepDetail> getWeekSleepDetail(int userId);
    List<SleepDetail> getDaySleepDetail(int userId, Date date1, Date date2);
    SleepDetail saveSleepDetail(SleepDetail sleepDetail);
}
