package com.alive_backend.controller.basic_data;

import com.alive_backend.annotation.UserLoginToken;
import com.alive_backend.entity.basic_data.Food;
import com.alive_backend.service.basic_data.FoodService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.utils.constant.FoodConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class FoodController{
    @Autowired
    private FoodService foodService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add_food")
    @UserLoginToken
    public Msg addFood(@RequestBody Map<String,Object> data, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        int userId = tokenService.getUserIdFromToken(token);

        Object name_ = data.get(FoodConstant.NAME);
        Object picture_ = data.get(FoodConstant.PICTURE);
        Object calorie_ = data.get(FoodConstant.CALORIE);
        Object carbohydrate_ = data.get(FoodConstant.CARBOHYDRATE);
        Object protein_ = data.get(FoodConstant.PROTEIN);
        Object fat_ = data.get(FoodConstant.FAT);
        Object dietaryFiber_ = data.get(FoodConstant.DIETARY_FIBER);
        Object sodium_ = data.get(FoodConstant.SODIUM);

        Food food = new Food();
        try{
            food.setName((String) name_);
            food.setPicture((String) picture_);
            food.setUserId(userId);
            food.setCalorie(((Number) calorie_).doubleValue());
            food.setCarbohydrate(((Number) carbohydrate_).doubleValue());
            food.setProtein(((Number) protein_).doubleValue());
            food.setFat(((Number) fat_).doubleValue());
            food.setDietaryFiber(((Number) dietaryFiber_).doubleValue());
            food.setSodium(((Number) sodium_).doubleValue());
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参格式{name:面包, picture:https://img.zcool.cn/community/01e6315d6e20b0a801211f9ef9fe34.jpg@3000w_1l_2o_100sh, userId:-1, calorie:254, carbohydrate:43.1, protein:12.3, fat:3.5, dietaryFiber:6.0, sodium:449.0}", null);
        }

        if(foodService.findFoodByName(food.getName()) != null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，食物已存在", null);
        }

        try{
            foodService.addFood(food);
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，传参格式{name:面包, picture:https://img.zcool.cn/community/01e6315d6e20b0a801211f9ef9fe34.jpg@3000w_1l_2o_100sh.jpg, userId:-1, calorie:254, carbohydrate:43.1, protein:12.3, fat:3.5, dietaryFiber:6.0, sodium:449.0}", JSONObject.fromObject(e));
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(food));
    }

    @PostMapping("/delete_food")
    @UserLoginToken
    public Msg deleteFood(@RequestBody Map<String, Object> data, HttpServletRequest httpServletRequest){
        Object name_ = data.get(FoodConstant.NAME);
        String token = httpServletRequest.getHeader("token");
        int userId = tokenService.getUserIdFromToken(token);

        if(name_ == null || token == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误", null);
        }

        Food food = foodService.findFoodByName((String) name_);
        if(food == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败，食物不存在", null);
        }

        if(food.getUserId() != userId){
            if(food.getUserId() == -1){
                return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败，食物为公共食物", null);
            }else {
                return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败，食物不属于该用户", null);
            }
        }

        try{
            foodService.deleteFood(food);
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败", JSONObject.fromObject(e));
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "删除成功", JSONObject.fromObject(food));
    }

    @PostMapping("/get_food")
    @UserLoginToken
    public Msg getFood(@RequestBody Map<String, Object> data, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        int userId = tokenService.getUserIdFromToken(token);

        if(userId < 0){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误,userId > 0", null);
        }

        List<Food> foodList = new ArrayList<>();
        foodList.addAll(foodService.findFoodByUserId(userId));
        foodList.addAll(foodService.findFoodByUserId(-1));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("food", foodList);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "获取成功", JSONObject.fromObject(jsonObject));
    }
}
