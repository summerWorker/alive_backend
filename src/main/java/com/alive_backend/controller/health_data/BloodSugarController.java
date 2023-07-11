package com.alive_backend.controller.health_data;

import com.alive_backend.service.health_data.BloodSugarService;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class BloodSugarController {
    @Autowired
    private BloodSugarService bloodSugarService;

    @PostMapping("/blood_sugar")
    public Msg addBloodSugar(@RequestBody Map<String, Object> data) {
        Object id_ = data.get(UserConstant.USER_ID);
        Object blood_sugar_ = data.get(Constant.BLOOD_SUGAR);
        Object time_ = data.get(Constant.DATE);
        if (id_ == null || blood_sugar_ == null || time_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,blood_sugar:1.1,date:yyyy-MM-dd}", null);
        }

        int id;
        Date start, end;
        try{
            id = (int) id_;
            start = Date.valueOf((String) time_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,blood_sugar:1.1,date:yyyy-MM-dd}", null);
        }


        return null;
    }
}
