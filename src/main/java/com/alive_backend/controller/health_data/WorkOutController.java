package com.alive_backend.controller.health_data;

import com.alive_backend.entity.basic_data.Exercise;
import com.alive_backend.entity.basic_data.Food;
import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.entity.health_data.WorkOut;
import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.service.basic_data.ExerciseService;
import com.alive_backend.service.health_data.WorkOutService;
import com.alive_backend.service.user_info.UserAuthService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.*;
import com.alive_backend.utils.enumerate.FoodTypeEnum;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class WorkOutController {
    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkOutService workOutService;

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/add_workout")
    public Msg addWorkOut(@RequestBody Map<String,Object> data){
        // 检验参数合法性
        Object userId_ = data.get(WorkOutConstant.USER_ID);
        Object exerciseName_ = data.get(ExerciseConstant.NAME);
        Object date_ = data.get(WorkOutConstant.DATE);
        Object amount_ = data.get(WorkOutConstant.AMOUNT);
        if (userId_ == null || exerciseName_ == null || date_ == null || amount_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参格式{user_id:1, name:“跑步”, date:2020-01-01, amount:1}", null);
        }

        WorkOut workOut = new WorkOut();
        Date date;
        try {
            date = Date.valueOf((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "日期格式错误", null);
        }

        workOut.setUserId(((Number) userId_).intValue());
        workOut.setDate(date);
        double amount = ((Number) amount_).doubleValue();
        String exerciseName = (String) exerciseName_;

        if(amount <= 0.0){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "数量必须大于等于0", null);
        }
        workOut.setAmount(amount);
        Exercise exercise = exerciseService.findExerciseByName(exerciseName);
        if(exercise == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，运动不存在", null);
        }
        workOut.setExerciseId(exercise.getId());

        UserAuth userAuth = userAuthService.findUserById(workOut.getUserId());
        if(userAuth == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败，用户不存在", null);
        }

        //查看用户是否已经添加过该运动
        WorkOut workOut1 = workOutService.findWorkOutByUserIdAndExerciseIdAndDate(workOut.getUserId(), workOut.getExerciseId(), workOut.getDate());
        if(workOut1 != null){
            workOut.setId(workOut1.getId());
        }
        try{
            System.out.println(workOut);
            workOutService.updateWorkOut(workOut);
        } catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", JSONObject.fromObject(e));
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(workOut,new CustomJsonConfig()));
    }
    @PostMapping("/get_workout")
    public Msg getWorkOut(@RequestBody Map<String,Object> data){
        Object userId_ = data.get(WorkOutConstant.USER_ID);
        Object startDate_ = data.get(WorkOutConstant.START_DATE);
        Object endDate_ = data.get(WorkOutConstant.END_DATE);
        if (userId_ == null || startDate_ == null || endDate_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参格式{user_id:1, start_date:2020-01-01, end_date:2020-01-02}", null);
        }
        int userId = ((Number) userId_).intValue();
        Date startDate = Date.valueOf((String) startDate_);
        Date endDate = Date.valueOf((String) endDate_);

        List<WorkOut> workOutList = workOutService.findWorkOutByUserIdAndDateBetween(userId, startDate,endDate);
        JSONArray jsonArray = new JSONArray();

        if(workOutList == null || workOutList.size() == 0){
            return MsgUtil.makeMsg(MsgUtil.EMPTY_ERROR, "查询失败，该用户当天没有添加运动", null);
        }

        for (WorkOut workOut : workOutList) {
            Exercise exercise = exerciseService.getExerciseById(workOut.getExerciseId());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", workOut.getId());
            jsonObject.put("user_id", workOut.getUserId());
            jsonObject.put("exercise", exercise);
            jsonObject.put("date", workOut.getDate().toString());
            jsonObject.put("amount", workOut.getAmount());

            jsonArray.add(jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("diet_list", jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "查询成功", jsonObject);
    }
}
