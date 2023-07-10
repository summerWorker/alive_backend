package com.alive_backend.controller.user_info;

import com.alive_backend.service.user_info.UserInfoService;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/user_by_id")
    public Msg getUserById(@RequestBody Map<String,Object> data) {
        // 检验参数合法性
        Object id = data.get(UserConstant.USER_ID);
        if (id == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误。id：1");
        }
        int userId = Integer.parseInt(id.toString());
        if (userId <= 0) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误。id > 0");
        }
        // 调用service层
        JSONObject result = JSONObject.fromObject(userInfoService.getUserById(userId));
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, result);
    }
}
