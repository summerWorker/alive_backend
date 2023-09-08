package com.alive_backend.controller.basic_data;

import com.alive_backend.entity.basic_data.Exercise;
import com.alive_backend.service.basic_data.ExerciseService;
import com.alive_backend.utils.constant.ExerciseConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/add_exercise")
    public Msg addExercise(@RequestBody Map<String,Object> data){
        Object name_ = data.get(ExerciseConstant.NAME);
        Object picture_ = data.get(ExerciseConstant.PICTURE);
        Object userId_ = data.get(ExerciseConstant.USER_ID);
        Object calorie_ = data.get(ExerciseConstant.CALORIE);

        Exercise exercise = new Exercise();
        try{
            exercise.setName((String) name_);
            exercise.setPicture((String) picture_);
            exercise.setUserId(((Number) userId_).intValue());
            exercise.setCalorie(((Number) calorie_).doubleValue());
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参格式{name:游泳, picture:“”, userId:-1, calorie:254}", null);
        }

        if(exerciseService.findExerciseByName(exercise.getName()) != null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，该项运动已存在", null);
        }

        try{
            exerciseService.addExercise(exercise);
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，传参格式{name:游泳, picture:“”, userId:-1, calorie:254}", JSONObject.fromObject(e));
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(exercise));
    }


    @PostMapping("/get_exercise")
    public Msg getExercise(@RequestBody Map<String, Object> data){
        Object userId_ = data.get(ExerciseConstant.USER_ID);
        int userId = ((Number) userId_).intValue();

        if(userId < 0){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误,userId > 0", null);
        }

        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.addAll(exerciseService.findExerciseByUserId(userId));
        exerciseList.addAll(exerciseService.findExerciseByUserId(-1));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("exercise", exerciseList);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "获取成功", JSONObject.fromObject(jsonObject));
    }

    @PostMapping("/delete_exercise")
    public Msg deleteExercise(@RequestBody Map<String, Object> data){
        Object name_ = data.get(ExerciseConstant.NAME);
        Object user_id_ = data.get(ExerciseConstant.USER_ID);

        if(name_ == null || user_id_ == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误", null);
        }

        Exercise exercise = exerciseService.findExerciseByName((String) name_);
        if(exercise == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败，运动不存在", null);
        }

        if(exercise.getUserId() != (int) user_id_){
            if(exercise.getUserId() == -1){
                return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败，运动为公共运动", null);
            }else {
                return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败，运动不属于该用户", null);
            }
        }

        try{
            exerciseService.deleteExercise(exercise);
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "删除失败", JSONObject.fromObject(e));
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "删除成功", JSONObject.fromObject(exercise));
    }
}
