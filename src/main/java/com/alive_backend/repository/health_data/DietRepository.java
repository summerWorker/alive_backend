package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.utils.enumerate.FoodTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface DietRepository extends JpaRepository<Diet, UUID> {
    List<Diet> getByUserId(int userId);

    Diet getByUserIdAndFoodIdAndDateAndType(int userId, UUID foodId, Date date, int type);

    List<Diet> getByUserIdAndDate(int userId, Date date);
    List<Diet> getByUserIdAndDateBetween(int userId, Date startDate, Date endDate);
}
