package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.SleepDetail;
import com.alive_backend.service.health_data.SleepDetailService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.SleepConstant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alive_backend.utils.ParseWeek;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
public class SleepController {
    @Autowired
    private SleepDetailService sleepDetailService;

    /*
    * @Brief: 获取一天的睡眠数据(详细)
    * */
    @PostMapping("/day_sleep")
    public Msg getDaySleep(@RequestBody Map<String,Object> data) {
        /* 检验参数合法性 */
        Object id_ = data.get(UserConstant.USER_ID);
        Object date1_ = data.get(Constant.START_DATE);
        Object date2_ = data.get(Constant.END_DATE);
        if (id_ == null || date1_ == null || date2_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1, start_date:2023-04-01, end_date:2023-06-06}", null);
        }

        int id;
        Date date1, date2;
        try {
            id = (int) id_;
            date1 = Date.valueOf((String) date1_);
            date2 = Date.valueOf((String) date2_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
        }
        List<SleepDetail> sleepDetail = sleepDetailService.getDaySleepDetail(id, date1,date2);
        CustomJsonConfig jsonConfig = new CustomJsonConfig();
        JSONArray jsonArray = JSONArray.fromObject(sleepDetail,jsonConfig);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sleep_detail", jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);

    }

}
