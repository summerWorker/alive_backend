package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Steps;
import com.alive_backend.repository.health_data.StepsRepository;
import com.alive_backend.service.health_data.StepsService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.StepsConstant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class StepsController {
    @Autowired
    private StepsService stepsService;

    @GetMapping("/steps")
    public Msg Test(){
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, null);
    }

    @PostMapping("/add_steps")
    public Msg AddSteps(@RequestBody Map<String,Object> data) {
        if(data == null) return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1, steps:1000, date:2023-04-01,goal:10000/null,distance:5/null,calories:153/null,heart_rate:100,cadence:50}", null);

        Object user_id_ = data.get(UserConstant.USER_ID);
        Object step_ = data.get(StepsConstant.STEP);
        Object date_ = data.get(StepsConstant.DATE);
        Object goal_ = data.get(StepsConstant.GOAL);
        Object distance_ = data.get(StepsConstant.DISTANCE);
        Object calories_ = data.get(StepsConstant.CALORIES);
        if (user_id_ == null || step_ == null || date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1, steps:1000, date:2023-04-01,goal:10000/null,distance:5/null,calories:153/null,heart_rate:100,cadence:50}", null);
        }

        Steps steps = new Steps();
        try {
            steps.setUserId((int) user_id_);
            steps.setStep((int) step_);
            steps.setDate(Date.valueOf((String) date_));
            if(goal_ != null)
                steps.setGoal((int) goal_);
            if(distance_ != null)
                steps.setDistance((int) distance_);
            if(calories_ != null)
                steps.setCalories((int) calories_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
        }


        //查看当前数据是否已经存在
        Steps steps1 = stepsService.findByUserIdAndDate(steps.getUserId(), steps.getDate());
        if(steps1 != null) {
            stepsService.delete(steps1);
        }

        try {
            stepsService.addSteps(steps);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(steps, new CustomJsonConfig()));
    }

    @PostMapping("/get_steps")
    public Msg getSteps(@RequestBody Map<String,Object> data){
        Object user_id_ = data.get(UserConstant.USER_ID);
        Object start_date_ = data.get(StepsConstant.START_DATE);
        Object end_date_ = data.get(StepsConstant.END_DATE);
        if (user_id_ == null || start_date_ == null || end_date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1, start_date:2023-04-01, end_date:2023-04-01}", null);
        }

        int user_id = (int) user_id_;
        Date start_date = Date.valueOf((String) start_date_);
        Date end_date = Date.valueOf((String) end_date_);
        if (start_date.after(end_date)) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "开始日期不能在结束日期之后", null);
        }
        List<Steps> stepsList = stepsService.getSteps(user_id, start_date, end_date);

        JSONArray jsonArray = JSONArray.fromObject(stepsList, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("steps", jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
}
