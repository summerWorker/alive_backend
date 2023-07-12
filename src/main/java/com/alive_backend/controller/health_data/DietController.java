package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.service.basic_data.FoodService;
import com.alive_backend.service.health_data.DietService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.enumerate.FoodTypeEnum;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:3000")
public class DietController{
    @Autowired
    private DietService dietService;

    @Autowired
    private FoodService foodService;

    @PostMapping("/add_diet")
    public Msg addDiet(@RequestBody Map<String,Object> data){
        if(data.get("userId") == null || data.get("foodId") == null || data.get("date") == null || data.get("type") == null || data.get("amount") == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误", null);
        }

        Diet diet = new Diet();
        diet.setUserId(UUID.fromString((String) data.get("userId")));
        diet.setFoodId(UUID.fromString((String) data.get("foodId")));
        diet.setDate(Date.valueOf((String) data.get("date")));
        diet.setType(FoodTypeEnum.valueOf((String) data.get("type")));
        Object amount_ = data.get("amount");
        double amount = ((Number)amount_).doubleValue();
        diet.setAmount(amount);

        if(diet.getAmount() <= 0){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "数量必须大于等于0", null);
        }

        if(foodService.findFoodById(diet.getFoodId()) == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，食物不存在", null);
        }

        //TODO: check if user exists

        //查看用户是否已经添加过该食物
        Diet diet1 = dietService.findDietByUserIdAndFoodIdAndDateAndType(diet.getUserId(), diet.getFoodId(), diet.getDate(), diet.getType());
        System.out.println(diet1);
        if(diet1 != null){
            diet1.setAmount(diet1.getAmount() + diet.getAmount());
            try{
                System.out.println(diet1);

                dietService.updateDiet(diet1);

            }catch (Exception e){
                return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", JSONObject.fromObject(e));
            }
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(diet1,new CustomJsonConfig()));
        }

        try{
            dietService.addDiet(diet);
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，传参格式{userId:1, foodId:1, date:2020-01-01, type:早餐, amount:1}", JSONObject.fromObject(e));
        }

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(diet,new CustomJsonConfig()));
    }


//    @PostMapping("/update_diet")


//    @PostMapping("/delete_diet")

//    @GetMapping("/get_diet")
}