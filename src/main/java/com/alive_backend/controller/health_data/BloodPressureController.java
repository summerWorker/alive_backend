package com.alive_backend.controller.health_data;


import com.alive_backend.dao.health_data.BloodPressureDao;
import com.alive_backend.entity.health_data.BloodPressure;
import com.alive_backend.service.health_data.BloodPressureService;
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

    @PostMapping("/blood_pressure")
    public Msg getPeriodBloodPressure(@RequestBody Map<String, Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        Object start_ = data.get(Constant.START_DATE);
        Object end_ = data.get(Constant.END_DATE);
        if (id_ == null || start_ == null || end_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,start_date:yyyy-MM-dd,end_date:yyyy-MM-dd}", null);
        }
        int id;
        Date start, end;
        try{
            id = (int) id_;
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
    public Msg addBloodPressure(@RequestBody Map<String, Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        Object date_ = data.get(Constant.DATE);
        Object high_ = data.get(Constant.SYSTOLIC);
        Object low_ = data.get(Constant.DIASTOLIC);
        if (id_ == null || date_ == null || high_ == null || low_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,date:yyyy-MM-dd,systolic:120,diastolic:80}", null);
        }
        int id, high, low;
        Date date;
        try{
            id = (int) id_;
            date = Date.valueOf((String) date_);
            high = (int) high_;
            low = (int) low_;
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,date:yyyy-MM-dd,systolic:120,diastolic:80}", null);
        }
        if(high < 0 || low < 0 || high > 300 || low > 300) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "血压值不合法", null);
        }
        BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(id);
        bloodPressure.setDate(date);
        bloodPressure.setSystolic(high);
        bloodPressure.setDiastolic(low);
        try{
            BloodPressure ret = bloodPressureService.addBloodPressure(bloodPressure);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(ret, new CustomJsonConfig()));
        }catch(Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
        }
    }
}
