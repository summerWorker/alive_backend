package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Height;
import com.alive_backend.annotation.UserLoginToken;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.service.health_data.HeightService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.utils.analysis.BMI;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class MainRecordController {
    @Autowired
    private MainRecordService mainRecordService;
    @Autowired
    private WeightService weightService;
    @Autowired
    private HeightService heightService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/main_record")
    @UserLoginToken
    @Cacheable(value = "mainRecordCache", key = "#data.get('user_id')")
    public Msg getMainRecordByUserId(@RequestBody Map<String,Object> data, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        /* 检验参数合法性 */
//        Object id_ = data.get(UserConstant.USER_ID);
//        if (id_ == null) {
//            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误：{user_id: 0}",null);
//        }
//        int id = (int)id_;

        /*  获取main_record */
        MainRecord mainRecord = mainRecordService.getMainRecordByUserId(id);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(mainRecord));
    }

    @PostMapping("/bmi")
//    @Cacheable(value = "bmiCache", key = "#data.get('user_id')")
    public Msg AnalyseBMI(@RequestBody Map<String, Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        if (id_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1}", null);
        }
        int id = (int) id_;
        MainRecord mainRecord = mainRecordService.getMainRecordByUserId(id);
        if (mainRecord == null || mainRecord.getWeight() == null || mainRecord.getHeight() == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "数据不全", null);
        }
        double weight = mainRecord.getWeight();
        double height = mainRecord.getHeight();
        JSONObject result = BMI.analyseBMI(weight, height);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, result);
    }
    @PostMapping("/update_main_record")
    public Msg getLatestRecord(@RequestBody Map<String, Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        if (id_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1}", null);
        }
        int id = (int) id_;
        MainRecord mainRecord = updateMainRecord(id);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(mainRecord));
    }

    public MainRecord updateMainRecord(int userId) {
        MainRecord mainRecord = mainRecordService.getMainRecordByUserId(userId);
        Date latest_date = new Date();
        //寻找最新的体重记录
        Weight weight = weightService.getLatestWeight(userId);
        if (weight != null) {
            mainRecord.setWeight(weight.getWeight());
            if (weight.getDate().after(latest_date)) {
                latest_date = weight.getDate();
            }
        }
        //寻找最新的身高记录
        Height height = heightService.getLatestHeight(userId);
        if (height != null) {
            mainRecord.setHeight(height.getHeight());
            if (height.getDate().after(latest_date)) {
                latest_date = height.getDate();
            }
        }
        mainRecord.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        return mainRecordService.updateMainRecord(mainRecord);
    }
}
