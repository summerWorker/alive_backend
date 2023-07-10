package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class MainRecordController {
    @Autowired
    private MainRecordService mainRecordService;

    @GetMapping("/main_record")
    @Cacheable(value = "mainRecordCache", key = "#data.get('user_id')")
    public Msg getMainRecordByUserId(@RequestBody Map<String,Object> data) {
        /* 检验参数合法性 */
        Object id_ = data.get(UserConstant.USER_ID);
        if (id_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误：{user_id: 0}",null);
        }
        int id = (int)id_;

        /*  获取main_record */
        MainRecord mainRecord = mainRecordService.getMainRecordByUserId(id);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(mainRecord));
    }
}
