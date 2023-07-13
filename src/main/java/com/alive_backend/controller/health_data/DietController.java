package com.alive_backend.controller.health_data;

import com.alive_backend.entity.basic_data.Food;
import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.service.basic_data.FoodService;
import com.alive_backend.service.health_data.DietService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.DietConstant;
import com.alive_backend.utils.constant.FoodConstant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.enumerate.FoodTypeEnum;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import lombok.var;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
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
        // 检验参数合法性
        Object userId_ = data.get(UserConstant.USER_ID);
        Object foodName_ = data.get(FoodConstant.NAME);
        Object date_ = data.get(Constant.DATE);
        Object type_ = data.get(DietConstant.TYPE);
        Object amount_ = data.get(DietConstant.AMOUNT);
        if (userId_ == null || foodName_ == null || date_ == null || type_ == null || amount_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参格式{user_id:1, name:“面包”, date:2020-01-01, type:BREAKFAST, amount:1}", null);
        }

        Diet diet = new Diet();
        Date date;
        try {
            date = Date.valueOf((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "日期格式错误", null);
        }

        diet.setUserId(((Number) userId_).intValue());
        diet.setDate(date);
        diet.setType(FoodTypeEnum.valueOf(((String) type_)).ordinal());
        double amount = ((Number) amount_).doubleValue();
        String foodName = (String) foodName_;

        if(amount <= 0.0){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "数量必须大于等于0", null);
        }
        diet.setAmount(amount);
        Food food = foodService.findFoodByName(foodName);
        if(food == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，食物不存在", null);
        }
        diet.setFoodId(food.getId());
        System.out.println(food.getId());
        System.out.println(diet);
        //TODO: check if user exists

        //查看用户是否已经添加过该食物
        Diet diet1 = dietService.findDietByUserIdAndFoodIdAndDateAndType(diet.getUserId(), diet.getFoodId(), diet.getDate(), diet.getType());
        System.out.println(diet1);
        if(diet1 != null){
            diet.setAmount(diet.getAmount());
            diet.setId(diet1.getId());
        }
        try{
            System.out.println(diet);
            dietService.updateDiet(diet);
        } catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", JSONObject.fromObject(e));
        }
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(diet,new CustomJsonConfig()));

    }

    @GetMapping("/get_diet")
    public Msg getDiet(@RequestParam("user_id") int userId, @RequestParam("date") Date date){
        List<Diet> dietList = dietService.findDietByUserIdAndDate(userId, date);
        JSONArray jsonArray = new JSONArray();

        if(dietList == null || dietList.size() == 0){
            return MsgUtil.makeMsg(MsgUtil.EMPTY_ERROR, "查询失败，该用户当天没有添加食物", null);
        }

        for (Diet diet : dietList) {
            Food food = foodService.getFoodById(diet.getFoodId());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", diet.getId());
            jsonObject.put("user_id", diet.getUserId());
            jsonObject.put("food", food);
            jsonObject.put("date", diet.getDate().toString());
            jsonObject.put("type", FoodTypeEnum.values()[diet.getType()]);
            jsonObject.put("amount", diet.getAmount());

            jsonArray.add(jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("diet_list", jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "查询成功", jsonObject);
    }


//    @PostMapping("/update_diet")


//    @PostMapping("/delete_diet")


}