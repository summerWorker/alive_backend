package com.alive_backend.controller.health_data;

import com.alive_backend.annotation.UserLoginToken;
import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.service.health_data.BloodSugarService;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.serviceimpl.TokenService;
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

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class BloodSugarController {
    @Autowired
    private BloodSugarService bloodSugarService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private MainRecordService mainRecordService;

    @PostMapping("/blood_sugar")
    @UserLoginToken
    public Msg getPeriodBloodSugar(@RequestBody Map<String, Object> data, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        Object start_ = data.get(Constant.START_DATE);
        Object end_ = data.get(Constant.END_DATE);
        if ( start_ == null || end_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{start_date:yyyy-MM-dd, end_date:yyyy-mm-dd}", null);
        }
        Date start, end;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            start = dateFormat.parse((String) start_);
            end = dateFormat.parse((String) end_);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(end);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            end = calendar.getTime();
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{start_date:yyyy-MM-dd, end_date:yyyy-mm-dd}", null);
        }
        if(start.after(end)) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "开始日期不能在结束日期之后", null);
        }
        //find all the data between start_Date and end_date
        List<BloodSugar> bloodSugars = bloodSugarService.getBloodSugarByDate(id, start, end);

        JSONArray jsonArray = JSONArray.fromObject(bloodSugars, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("blood_sugar", jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }

    @PostMapping("/add_blood_sugar")
    @UserLoginToken
    public Msg addBloodSugar(@RequestBody Map<String, Object> data,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        Object bloodSugar_ = data.get(Constant.BLOOD_SUGAR);
        Object date_ = data.get(Constant.DATE);
        if (bloodSugar_ == null || date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{blood_sugar:1.1,date:yyyy-MM-dd HH:mm}", null);
        }
        double bloodSugar;
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            bloodSugar = Double.parseDouble(String.valueOf(bloodSugar_));
            date = dateFormat.parse((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{blood_sugar:1.1,date:yyyy-MM-dd HH:mm}", null);
        }
        BloodSugar bloodSugar1 = new BloodSugar();
        bloodSugar1.setUserId(id);
        bloodSugar1.setBloodSugar(bloodSugar);
        bloodSugar1.setDate(date);

        // add to main record
        BloodSugar latestBloodSugar = bloodSugarService.getLatestBloodSugar(id);
        if(latestBloodSugar == null || latestBloodSugar.getDate().before(date)) {
            MainRecord mainRecord = mainRecordService.getMainRecordByUserId(id);
            mainRecord.setBloodSugar(bloodSugar);
            if(mainRecord.getUpdateTime() == null || mainRecord.getUpdateTime().before(date)) {
                mainRecord.setUpdateTime(Timestamp.valueOf(date + " 00:00:00"));
            }
            try {
                mainRecordService.updateMainRecord(mainRecord);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        try{
            BloodSugar ret = bloodSugarService.addBloodSugar(bloodSugar1);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(ret, new CustomJsonConfig()));
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
        }
    }
}
