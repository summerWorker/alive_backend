package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Height;
import com.alive_backend.service.health_data.HeightService;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
public class HeightController {
    @Autowired
    private HeightService heightService;

    @PostMapping("/height")
    public Msg getHeightByDate(@RequestBody Map<String,Object> data) {
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
        Height height = heightService.getHeightByDate(id, date);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(height, new CustomJsonConfig()));
    }
    @PostMapping("/user_height")
    public Msg getHeightByUser(@RequestBody Map<String,Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        if (id_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1}", null);
        }
        int id = (int) id_;

        JSONArray jsonArray = JSONArray.fromObject(heightService.getHeightByUser(id), new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("heights",jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
    @PostMapping("/add_height")
    public Msg addHeight(@RequestBody Map<String, Object> data) {
        // 检验参数合法性
        Object id_ = data.get(UserConstant.USER_ID);
        Object height_ = data.get(Constant.HEIGHT);
        Object date_ = data.get(Constant.DATE);
        if(id_ == null || height_ == null || date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,height:1.0,date:yyyy-MM-dd}", null);
        }

        int id; double height; Date date;
        try {
            id = (int) id_;
            height = ((Number) height_).doubleValue();
            date = Date.valueOf((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误{user_id:1,height:1.0,date:yyyy-MM-dd}", null);
        }

        // 同日覆盖
        Height height0 = heightService.getHeightByDate(id, date);
        if (height0 != null) {
            height0.setHeight(height);
            try {
                Height newHeight = heightService.addHeight(height0);
                return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(newHeight, new CustomJsonConfig()));
            } catch (Exception e) {
                return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
            }
        }


        Height height1 = new Height();
        height1.setUserId(id); height1.setHeight(height); height1.setDate(date);
        try {
            Height newHeight = heightService.addHeight(height1);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "添加成功", JSONObject.fromObject(newHeight, new CustomJsonConfig()));
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "添加失败", null);
        }
    }
    @PostMapping("/period_height")
    public Msg getPeriodHeight(@RequestBody Map<String,Object> data) {
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
        List<Height> heights = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        while (!calendar.getTime().after(end)) {
            Date currentDate = new Date(calendar.getTime().getTime());

            Height height = heightService.getHeightByDate(id, currentDate);
            if (height != null)
                heights.add(height);
            // 将日期增加一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        JSONArray jsonArray = JSONArray.fromObject(heights, new CustomJsonConfig());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("heights",jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }

}
