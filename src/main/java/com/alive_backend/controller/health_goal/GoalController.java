package com.alive_backend.controller.health_goal;

import com.alive_backend.entity.goal.Goal;
import com.alive_backend.service.goal.GoalService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.GoalConstant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class GoalController {
    @Autowired
    private GoalService goalService;

    @PostMapping("/goals")
    public Msg getGoalsByUserId(@RequestBody Map<String ,Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        if (id_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id: 1}", null);
        }
        int id = (int) id_;
        List<Goal> goals = goalService.getGoalsByUserId(id);
        JSONArray jsonArray = JSONArray.fromObject(goals, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.GOAL, jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
    @PostMapping("/set_goal")
    public Msg setGoals(@RequestBody Map<String, Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        Object goalName_ = data.get(GoalConstant.GOAL_NAME);
        Object goalDdl_ = data.get(GoalConstant.GOAL_DDL);
        Object goalKey1_ = data.get(GoalConstant.GOAL_NUM);
        Object goalKey2_ = data.get(GoalConstant.GOAL_DESC);
        if (id_ == null || goalName_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id: 1, goal_name: 'goal_name'}", null);
        }
        int id = (int) id_;
        String goalName = (String) goalName_;
        switch (goalName) {
            case GoalConstant.WEIGHT_GOAL: {
                Date goalDdl = null;
                if (goalDdl_ != null) {
                    try {
                        goalDdl = Date.valueOf((String) goalDdl_);
                    } catch (Exception e) {
                        return MsgUtil.makeMsg(MsgUtil.ERROR, "日期格式错误{goal_ddl: '2019-01-01'}", null);
                    }
                }
                if (goalKey1_ == null) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "体重目标需要一个具体的值{goal_num: 60}", null);
                }
                double goalKey1 = ((Number) goalKey1_).doubleValue();
                Goal old_goal = goalService.getGoalByGoalName(id, GoalConstant.WEIGHT_GOAL);
                if (old_goal == null) {
                    old_goal = new Goal();
                    old_goal.setUserId(id);
                    old_goal.setGoalName(GoalConstant.WEIGHT_GOAL);
                    old_goal.setGoalKey1(goalKey1);
                    old_goal.setGoalDdl(goalDdl);
                    goalService.setGoal(old_goal);
                } else {
                    old_goal.setGoalKey1(goalKey1);
                    old_goal.setGoalDdl(goalDdl);
                    goalService.setGoal(old_goal);
                }

                return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(old_goal, new CustomJsonConfig()));
            }

            case GoalConstant.STEP_GOAL: {
                if (goalKey1_ == null) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "步数目标需要一个具体的值{goal_num: 10000}", null);
                }
                double goalKey1 = ((Number) goalKey1_).doubleValue();
                // 步数不设置ddl
                Goal old_goal = goalService.getGoalByGoalName(id, GoalConstant.STEP_GOAL);
                if (old_goal == null) {
                    old_goal = new Goal();
                    old_goal.setUserId(id);
                    old_goal.setGoalName(GoalConstant.STEP_GOAL);
                    old_goal.setGoalKey1(goalKey1);
                    goalService.setGoal(old_goal);
                } else {
                    old_goal.setGoalKey1(goalKey1);
                    goalService.setGoal(old_goal);
                }
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(old_goal, new CustomJsonConfig()));
            }

            case GoalConstant.SLEEP_LENGTH_GOAL: {
                if (goalKey1_ == null) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "睡眠长度目标需要一个具体的值{goal_num: 8.5}", null);
                }
                double goalKey1 = ((Number) goalKey1_).doubleValue();
                // 睡眠长度不设置ddl
                Goal old_goal = goalService.getGoalByGoalName(id, GoalConstant.SLEEP_LENGTH_GOAL);
                if (old_goal == null) {
                    old_goal = new Goal();
                    old_goal.setUserId(id);
                    old_goal.setGoalName(GoalConstant.SLEEP_LENGTH_GOAL);
                    old_goal.setGoalKey1(goalKey1);
                    goalService.setGoal(old_goal);
                } else {
                    old_goal.setGoalKey1(goalKey1);
                    goalService.setGoal(old_goal);
                }
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(old_goal, new CustomJsonConfig()));
            }

            case GoalConstant.BEDTIME_GOAL: {
                if (goalKey2_ == null) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "入睡时间目标需要一个具体的时间{goal_desc: “21:00”}", null);
                }
                String goalKey2 = (String) goalKey2_;
                goalKey2 = timeValid(goalKey2);
                if (goalKey2 == null) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "入睡时间格式错误,至少需要时分，英文冒号，“21:00”}", null);
                }

                // 入睡时间不设置ddl
                Goal old_goal = goalService.getGoalByGoalName(id, GoalConstant.BEDTIME_GOAL);
                if (old_goal == null) {
                    old_goal = new Goal();
                    old_goal.setUserId(id);
                    old_goal.setGoalName(GoalConstant.BEDTIME_GOAL);
                    old_goal.setGoalKey2(goalKey2);
                    goalService.setGoal(old_goal);
                } else {
                    old_goal.setGoalKey2(goalKey2);
                    goalService.setGoal(old_goal);
                }
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(old_goal, new CustomJsonConfig()));
            }

            case GoalConstant.CALORIE_GOAL: {
                if (goalKey1_ == null) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "卡路里目标需要一个具体的值{goal_num: 2000}", null);
                }
                double goalKey1 = ((Number) goalKey1_).doubleValue();
                // 卡路里不设置ddl
                Goal old_goal = goalService.getGoalByGoalName(id, GoalConstant.CALORIE_GOAL);
                if (old_goal == null) {
                    old_goal = new Goal();
                    old_goal.setUserId(id);
                    old_goal.setGoalName(GoalConstant.CALORIE_GOAL);
                    old_goal.setGoalKey1(goalKey1);
                    goalService.setGoal(old_goal);
                } else {
                    old_goal.setGoalKey1(goalKey1);
                    goalService.setGoal(old_goal);
                }
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(old_goal, new CustomJsonConfig()));
            }

            default:
                return MsgUtil.makeMsg(MsgUtil.ERROR, "目标类型错误", null);

        }
    }

    /* 检验时间格式，自动补上秒位:00 */
    private String timeValid(String time) {
        // 定义时间格式的正则表达式，匹配 hh:mm 或 hh:mm:ss 格式
        String pattern = "^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(time);
        if (matcher.matches()) {
            // 时间格式匹配，不需要进行补充秒
            return time;
        } else {
            // 时间格式不匹配，尝试进行补充秒并再次检验
            String correctedTime = time + ":00";
            matcher = regex.matcher(correctedTime);
            if(matcher.matches())
                return correctedTime;
            else
                return null;
        }
    }

    @PostMapping("/goal_name")
    public Msg getGoalByName(@RequestBody Map<String,Object> data) {
        // 检验参数合法性
        Object goalName_ = data.get(GoalConstant.GOAL_NAME);
        Object id_ = data.get(UserConstant.USER_ID);
        if (goalName_ == null || id_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,goal_name:\"height\"}", null);
        }
        String goalName = (String) goalName_;
        for(String name : GoalConstant.GOAL_TYPES) {
            if(name.equals(goalName)) {
                int id = ((Number) id_).intValue();
                Goal goal = goalService.getGoalByGoalName(id, goalName);
                if(goal == null) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "没有找到", null);
                } else {
                    return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(goal, new CustomJsonConfig()));
                }
            }
        }
        return MsgUtil.makeMsg(MsgUtil.ERROR, "目标类型错误", null);
    }
}
