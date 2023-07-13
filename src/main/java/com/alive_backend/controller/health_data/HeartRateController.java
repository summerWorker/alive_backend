package com.alive_backend.controller.health_data;
import com.alive_backend.entity.health_data.HeartRate;
import com.alive_backend.service.health_data.HeartRateService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class HeartRateController {
    @Autowired
    private HeartRateService heartRateService;
    @PostMapping("/heartRate")
//    @Cacheable(value = "heartRate", key = "#data.get('user_id') + '_' + #data.get('start_date') + '_' + #data.get('end_date')")
    public Msg getHeartRate(@RequestBody Map<String, Object> data){
        Object userId_ = data.get(UserConstant.USER_ID);
        Object date1_ = data.get(Constant.START_DATE);
        Object date2_ = data.get(Constant.END_DATE);
        if(userId_ == null || date1_ == null)
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误", null);
        int userId;
        Date date1, date2;
        if(date2_ != null){
            try{
                userId = (int) userId_;
                date1 = Date.valueOf((String) date1_);
                date2 = Date.valueOf((String) date2_);
            }catch (Exception e){
                return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
            }
            List<HeartRate> heartRates = heartRateService.findHeartRateByUserIdAndDateBetween(userId, date1, date2);
            CustomJsonConfig jsonConfig = new CustomJsonConfig();
            JSONArray jsonArray = JSONArray.fromObject(heartRates,jsonConfig);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("heartRates", jsonArray);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
        }
        else {
            try{
                userId = (int) userId_;
                date1 = Date.valueOf((String)date1_);
            }catch (Exception e){
                return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
            }
            List<HeartRate> heartRates = heartRateService.findHeartRateByUserIdAndDate(userId, date1);
            CustomJsonConfig jsonConfig = new CustomJsonConfig();
            JSONArray jsonArray = JSONArray.fromObject(heartRates,jsonConfig);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("heartRates", jsonArray);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
        }
    }
}
