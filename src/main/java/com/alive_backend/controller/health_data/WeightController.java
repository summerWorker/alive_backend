package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
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
import java.util.Map;

@RestController
public class WeightController {
    @Autowired
    private WeightService weightService;

    @PostMapping("/weight")
    public Msg getWeightByDate(@RequestBody Map<String,Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        Object date_ = data.get(Constant.DATE);
        if (id_ == null || date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,date:yyyy-MM-dd}", null);
        }
        int id;
        Date date;
        try {
            id = (int) id_;
            date = Date.valueOf((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,date:yyyy-MM-dd}", null);
        }
        Weight weight = weightService.getWeightByDate(id, date);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(weight, new CustomJsonConfig()));
    }
    @PostMapping("/user_weight")
    public Msg getWeightByUser(@RequestBody Map<String,Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        if (id_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1}", null);
        }
        int id = (int) id_;

        JSONArray jsonArray = JSONArray.fromObject(weightService.getWeightByUser(id), new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weights",jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
    @PostMapping("/add_weight")
    public Msg addWeight(@RequestBody Map<String, Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        Object weight_ = data.get(Constant.WEIGHT);
        Object date_ = data.get(Constant.DATE);
        if(id_ == null || weight_ == null || date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,weight:1.0,date:yyyy-MM-dd}", null);
        }

        int id; double weight; Date date;
        try {
            id = (int) id_;
            weight = (double) weight_;
            date = Date.valueOf((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,weight:1.0,date:yyyy-MM-dd}", null);
        }

        // 同日覆盖
        Weight weight0 = weightService.getWeightByDate(id, date);
        if (weight0 != null) {
            weight0.setWeight(weight);
            try {
                Weight newWeight = weightService.addWeight(weight0);
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(newWeight, new CustomJsonConfig()));
            } catch (Exception e) {
                return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
            }
        }


        Weight weight1 = new Weight();
        weight1.setUserId(id); weight1.setWeight(weight); weight1.setDate(date);
        try {
            Weight newWeight = weightService.addWeight(weight1);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(newWeight, new CustomJsonConfig()));
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
        }
    }
}
