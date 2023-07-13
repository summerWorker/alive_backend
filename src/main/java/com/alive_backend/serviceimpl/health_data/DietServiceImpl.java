package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.DietDao;
import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.service.health_data.DietService;
import com.alive_backend.utils.enumerate.FoodTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@Service
public class DietServiceImpl implements DietService {
    @Autowired
    private DietDao dietDao;

    @Override
    public Diet addDiet(Diet diet) {
        return dietDao.addDiet(diet);
    }

    @Override
    public Diet findDietByUserIdAndFoodIdAndDateAndType(int userId, UUID foodId, Date date, int type) {
        return dietDao.findDietByUserIdAndFoodIdAndDateAndType(userId, foodId, date, type);
    }

    @Override
    public void updateDiet(Diet diet1) {
        dietDao.updateDiet(diet1);
    }
}
