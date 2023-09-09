package com.alive_backend.controller.health_data;


import com.alive_backend.annotation.UserLoginToken;
import com.alive_backend.dao.health_data.BloodPressureDao;
import com.alive_backend.entity.health_data.BloodPressure;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.service.health_data.BloodPressureService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import com.alive_backend.utils.redis.RedisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.sql.Date;

@RestController
@CrossOrigin("http://localhost:3000")
public class BloodPressureController {
    @Autowired
    private BloodPressureService bloodPressureService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MainRecordService mainRecordService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/blood_pressure")
    @UserLoginToken
    public Msg getPeriodBloodPressure(@RequestBody Map<String, Object> data, HttpServletRequest httpServletRequest) {
        // 检验参数合法性
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        Object start_ = data.get(Constant.START_DATE);
        Object end_ = data.get(Constant.END_DATE);
        if ( start_ == null || end_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{start_date:yyyy-MM-dd,end_date:yyyy-MM-dd}", null);
        }
        Date start, end;
        try{
            start = Date.valueOf((String) start_);
            end = Date.valueOf((String) end_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,start:yyyy-MM-dd,end:yyyy-MM-dd}", null);
        }
        if(start.after(end)) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "开始日期不能在结束日期之后", null);
        }
        List<BloodPressure> bloodPressures = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        while(!calendar.getTime().after(end)){
            Date currentDate = new Date(calendar.getTime().getTime());

            BloodPressure bloodPressure = bloodPressureService.getBloodPressureByDate(id, currentDate);
            if(bloodPressure != null){
                bloodPressures.add(bloodPressure);
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        JSONArray jsonArray = JSONArray.fromObject(bloodPressures, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("blood_pressure", jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }

    @PostMapping("/add_blood_pressure")
    @UserLoginToken
    public Msg addBloodPressure(@RequestBody Map<String, Object> data, HttpServletRequest httpServletRequest) {
        // 检验参数合法性
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        Object date_ = data.get(Constant.DATE);
        Object high_ = data.get(Constant.SYSTOLIC);
        Object low_ = data.get(Constant.DIASTOLIC);
        if (date_ == null || high_ == null || low_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,date:yyyy-MM-dd,systolic:120,diastolic:80}", null);
        }
        int  high, low;
        Date date;
        try{
            date = Date.valueOf((String) date_);
            high = (int) high_;
            low = (int) low_;
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{date:yyyy-MM-dd,systolic:120,diastolic:80}", null);
        }
        if(high < 0 || low < 0 || high > 300 || low > 300) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "血压值不合法", null);
        }
        // 检查是否是最新的血压记录
        BloodPressure LatestbloodPressure = bloodPressureService.getLatestBloodPressure(id);
        if(LatestbloodPressure == null || LatestbloodPressure.getDate().before(date)) {
            // 写入mainRecord
//            try{
                MainRecord mainRecord = mainRecordService.getMainRecordByUserId(id);
                mainRecord.setDiastolicPressure((double)low);
                mainRecord.setSystolicPressure((double)high);
                if (mainRecord.getUpdateTime() == null || mainRecord.getUpdateTime().before(date)) {
                    mainRecord.setUpdateTime(Timestamp.valueOf(date + " 00:00:00"));
                }
                mainRecordService.updateMainRecord(mainRecord);
            redisUtil.del("MainRecord_" + String.valueOf(id));
            redisUtil.set("MainRecord_" + String.valueOf(id), mainRecord, 60 * 60 * 24);
//            }catch (Exception e){
//               System.out.println(e);
//            }
        }
        // 查看当天是否有血压记录
        BloodPressure bloodPressure1 = bloodPressureService.getBloodPressureByDate(id, date);
        // 如果有，更新
        if(bloodPressure1 != null){
           bloodPressure1.setSystolic(high);
           bloodPressure1.setDiastolic(low);
//            try{
                BloodPressure ret = bloodPressureService.addBloodPressure(bloodPressure1);
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(ret, new CustomJsonConfig()));
//            }catch(Exception e){
//                return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
//            }
        } else {
            BloodPressure bloodPressure = new BloodPressure();
            bloodPressure.setUserId(id);
            bloodPressure.setDate(date);
            bloodPressure.setSystolic(high);
            bloodPressure.setDiastolic(low);
//            try{
                BloodPressure ret = bloodPressureService.addBloodPressure(bloodPressure);
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(ret, new CustomJsonConfig()));
//            }catch(Exception e){
//                return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
//            }
        }
    }
}
