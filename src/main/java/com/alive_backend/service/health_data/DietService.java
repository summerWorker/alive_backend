package com.alive_backend.service.health_data;

import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.utils.enumerate.FoodTypeEnum;

import java.sql.Date;
import java.util.UUID;

public interface DietService {
    Diet addDiet(Diet diet);

    Diet findDietByUserIdAndFoodIdAndDateAndType(int userId, UUID foodId, Date date, int type);

    void updateDiet(Diet diet1);
}
