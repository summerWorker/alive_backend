package com.alive_backend.controller.health_data;
import com.alive_backend.annotation.UserLoginToken;
import com.alive_backend.entity.health_data.HeartRate;
import com.alive_backend.service.health_data.HeartRateService;
import com.alive_backend.serviceimpl.TokenService;
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

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class HeartRateController {
    @Autowired
    private HeartRateService heartRateService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/heartRate")
    @UserLoginToken
//    @Cacheable(value = "heartRate", key = "#data.get('user_id') + '_' + #data.get('start_date') + '_' + #data.get('end_date')")
    public Msg getHeartRate(@RequestBody Map<String, Object> data, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        int userId = tokenService.getUserIdFromToken(token);
        Object date1_ = data.get(Constant.START_DATE);
        Object date2_ = data.get(Constant.END_DATE);
        if( date1_ == null)
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误", null);
        Date date1, date2;
        if(date2_ != null){
            try{
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
    @PostMapping("/add_heartRate")
    @UserLoginToken
    public Msg addHeartRate(@RequestBody Map<String, Object> data,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        int userId = tokenService.getUserIdFromToken(token);
        Object timeStamp_ = data.get(Constant.TIMESTAMP);
        Object heartRate_ = data.get(Constant.HEART_RATE);
        if( timeStamp_ == null || heartRate_ == null)
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误", null);

        Long timeStamp;
        String heartRate;
        try{
            timeStamp = Long.parseLong(String.valueOf(timeStamp_));
            heartRate = (String) heartRate_;
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
        }
//        try{
            HeartRate newHeartRate = new HeartRate();
            newHeartRate.setUserId(userId);
            newHeartRate.setTimeStamp(timeStamp);
            newHeartRate.setDetailValue(heartRate);
            heartRateService.addHeartRate(newHeartRate);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(newHeartRate, new CustomJsonConfig()));
        }
//        catch (Exception e){
//            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
//        }
//    }
}
