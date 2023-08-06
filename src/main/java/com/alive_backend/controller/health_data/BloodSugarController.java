package com.alive_backend.controller.health_data;

//import com.alibaba.fastjson2.JSONObject as FastJSONObject;
import net.sf.json.JSONObject;
import com.alive_backend.entity.event.Event;
import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.service.health_data.BloodSugarService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.constant.TopicConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class BloodSugarController {
    @Autowired
    private BloodSugarService bloodSugarService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PostMapping("/blood_sugar")
    public Msg getPeriodBloodSugar(@RequestBody Map<String, Object> data) {
        Object id_ = data.get(UserConstant.USER_ID);
        Object start_ = data.get(Constant.START_DATE);
        Object end_ = data.get(Constant.END_DATE);
        if (id_ == null || start_ == null || end_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,start_date:yyyy-MM-dd, end_date:yyyy-mm-dd}", null);
        }

        int id;
        Date start, end;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            id = (int) id_;
            start = dateFormat.parse((String) start_);
            end = dateFormat.parse((String) end_);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(end);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            end = calendar.getTime();
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,start_date:yyyy-MM-dd, end_date:yyyy-mm-dd}", null);
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
    public Msg addBloodSugar(@RequestBody Map<String, Object> data) throws Exception{
        Object id_ = data.get(UserConstant.USER_ID);
        Object bloodSugar_ = data.get(Constant.BLOOD_SUGAR);
        Object date_ = data.get(Constant.DATE);
        if (id_ == null || bloodSugar_ == null || date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,blood_sugar:1.1,date:yyyy-MM-dd HH:mm}", null);
        }
        int id;
        double bloodSugar;
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            id = (int) id_;
            bloodSugar = Double.parseDouble(String.valueOf(bloodSugar_));
            date = dateFormat.parse((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,blood_sugar:1.1,date:yyyy-MM-dd HH:mm}", null);
        }

        //创建一个UUID
        UUID uuid = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", uuid);
        Event event = new Event();
        event.setUser_id(id);
        event.setDate(date);
        event.setTopic(TopicConstant.BLOOD_SUGAR_TOPIC);
        event.setUuid(uuid);
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.BLOOD_SUGAR, bloodSugar);
        event.setData(map);
        kafkaTemplate.send(TopicConstant.BLOOD_SUGAR_TOPIC, com.alibaba.fastjson2.JSONObject.toJSONString(event));
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
}
