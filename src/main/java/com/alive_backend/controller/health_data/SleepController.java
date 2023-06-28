package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.SleepDetail;
import com.alive_backend.service.health_data.SleepDetailService;
import com.alive_backend.utils.JsonConfig.CustomJsonConfig;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
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
        Object date_ = data.get(Constant.DATE);
        if (id_ == null || date_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误：{user_id: 0, date: 2020-01-01}", null);
        }

        int id;
        Date date;
        try {
            id = (int) id_;
            date = Date.valueOf((String) date_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
        }
        SleepDetail sleepDetail = sleepDetailService.getDaySleepDetail(id, date);
        System.out.println(sleepDetail);
        System.out.println(date);
        CustomJsonConfig jsonConfig = new CustomJsonConfig();
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(sleepDetail,jsonConfig));

    }
}
