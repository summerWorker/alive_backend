package com.alive_backend.daoimpl.health_data;

import com.alive_backend.dao.health_data.DietDao;
import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.repository.health_data.DietRepository;
import com.alive_backend.utils.enumerate.FoodTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class DietDaoImpl implements DietDao {
    @Autowired
    private DietRepository dietRepository;
    @Override
    public List<Diet> getDietByUserId(int userId) {
        return dietRepository.getByUserId(userId);
    }

    @Override
    public Diet addDiet(Diet diet) {
        return dietRepository.save(diet);
    }

    @Override
    public void deleteDiet(Diet diet) {
        dietRepository.delete(diet);
    }

    @Override
    public Diet findDietByUserIdAndFoodIdAndDateAndType(int userId, UUID foodId, Date date, int type) {
        return dietRepository.getByUserIdAndFoodIdAndDateAndType(userId, foodId, date, type);
    }

    @Override
    public void updateDiet(Diet diet1) {
        dietRepository.save(diet1);
    }

}
