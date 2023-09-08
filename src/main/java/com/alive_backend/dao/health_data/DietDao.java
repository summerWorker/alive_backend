package com.alive_backend.dao.health_data;

import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.utils.enumerate.FoodTypeEnum;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface DietDao {
    List<Diet> getDietByUserId(int userId);
    Diet addDiet(Diet diet);
    void deleteDiet(Diet diet);

    Diet findDietByUserIdAndFoodIdAndDateAndType(int userId, UUID foodId, Date date, int type);

    void updateDiet(Diet diet1);

    List<Diet> findDietByUserIdAndDate(int userId, Date date);
    List<Diet> findDietByUserIdAndDateBetween(int userId, Date startDate, Date endDate);
}
