package com.alive_backend.controller.health_data;

import antlr.Token;
import com.alive_backend.annotation.UserLoginToken;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class WeightController {
    @Autowired
    private WeightService weightService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/weight")
    @UserLoginToken
    @Cacheable(value = "weightCache", key = "#data.get('user_id')+ '_' + #data.get('start_date') + '_' +#data.get('end_date')")
    public Msg getWeight(@RequestBody Map<String, Object> data, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);

        /* 检验参数合法性 */
//        Object id_ = data.get(UserConstant.USER_ID);
        Object date_ = data.get(Constant.DATE);
        if ( date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,date:yyyy-MM-dd}", null);
        }
//        int id;
        Date date;
        try {
//            id = (int) id_;
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
    @UserLoginToken
    @CacheEvict(value = "weightCache", allEntries = true)
    public Msg AddWeight(@RequestBody Map<String,Object> data, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        /* 检验参数合法性 */
//        Object id_ = data.get(UserConstant.USER_ID);
        Object date_ = data.get(Constant.DATE);
        Object weight_ = data.get(Constant.WEIGHT);
        if (date_ == null || weight_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1, date:2023-04-01, weight:60}", null);
        }

        double weight; Date date;
        try {
//            id = (int) id_;
            weight = ((Number) weight_).doubleValue();
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
    @PostMapping("/period_weight")
    public Msg getPeriodWeight(@RequestBody Map<String,Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        Object start_ = data.get(Constant.START_DATE);
        Object end_ = data.get(Constant.END_DATE);
        if (id_ == null || start_ == null || end_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,start:yyyy-MM-dd,end:yyyy-MM-dd}", null);
        }
        int id; Date start; Date end;
        try {
            id = (int) id_;
            start = Date.valueOf((String) start_);
            end = Date.valueOf((String) end_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,start:yyyy-MM-dd,end:yyyy-MM-dd}", null);
        }
        if(start.after(end)) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "开始日期不能在结束日期之后", null);
        }
        List<Weight> weights = new ArrayList<>();
        double lastWeight = 0.0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        while (!calendar.getTime().after(end)) {
            Date currentDate = new Date(calendar.getTime().getTime());

            Weight weight = weightService.getWeightByDate(id, currentDate);
            if (weight != null)
                weights.add(weight);
            // 将日期增加一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        JSONArray jsonArray = JSONArray.fromObject(weights, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weights",jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }

}
