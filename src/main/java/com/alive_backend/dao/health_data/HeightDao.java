package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.Height;

import java.sql.Date;
import java.util.List;

public interface HeightDao {
    Height getHeightByDate(int id, Date date);
    List<Height> getHeightByUser(int id);
    Height addHeight(Height height);
    Height getLatestHeight(int id);
}
