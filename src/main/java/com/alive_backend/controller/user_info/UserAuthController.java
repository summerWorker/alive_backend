package com.alive_backend.controller.user_info;

import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.entity.user_info.UserInfo;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.service.user_info.UserAuthService;
import com.alive_backend.service.user_info.UserInfoService;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private MainRecordService mainRecordService;

    /**
     * 检查用户名是否重复
     * @param data: {username: 'xxx'}
     * @return Msg
     */
    @PostMapping("/register/check_name")
    public Msg checkName(@RequestBody Map<String,Object> data) {
        /* 参数合法性检验 */
        Object name_obj = data.get(UserConstant.USERNAME);
        if (name_obj == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "未传递用户名{username: 'xxx'}", null);
        }

        String name = data.get(UserConstant.USERNAME).toString();
        UserAuth user = userAuthService.getUserAuthByName(name);
        if (user == null || user.getStatus() == UserConstant.STATUS_INACTIVATE) {
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "用户名合法", null);
        } else {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户名已被注册", null);
        }
    }

    /**
     * 注册第一步：发送邮箱验证码
     * @param data: {username:'xxx',password:"xxx",email: 'xxx'}
     * @return Msg
     */
    @PostMapping("/register/send_email_code")
    public Msg register_step1(@RequestBody Map<String,Object> data) {
        /* 参数合法性检验 */
        Object name_obj = data.get(UserConstant.USERNAME);
        Object password_obj = data.get(UserConstant.PASSWORD);
        Object email_obj = data.get(UserConstant.EMAIL);
        if (name_obj == null || password_obj == null || email_obj == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误：{\"username\": 'xxx', \"password\": 'xxx',\"email\": 'xxx'}", null);
        }

        /* 获取参数 */
        String name = data.get(UserConstant.USERNAME).toString();
        String password = data.get(UserConstant.PASSWORD).toString();
        String email = data.get(UserConstant.EMAIL).toString();

        /* 检验邮箱是否被注册 */
        UserAuth user = userAuthService.getUserAuthByEmail(email);
        if (user != null && user.getStatus() != UserConstant.STATUS_INACTIVATE) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "邮箱已被注册", null);
        }

        /* 解决发送过于频繁 */
        if (user != null && user.getStatus() == UserConstant.STATUS_INACTIVATE) {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            Timestamp last_date = user.getCodeUpdateTime();
            if(last_date != null){
                long time = date.getTime() - last_date.getTime();
                if (time < 60000) {
                    return MsgUtil.makeMsg(MsgUtil.ERROR, "发送过于频繁", null);
                }
            }
        }

        /* 保存用户 */
        if(user == null)
            user = new UserAuth();
        user.setUsername(name); user.setPassword(password); user.setEmail(email);
        user.setStatus(UserConstant.STATUS_INACTIVATE); // 未激活
        // TODO: 生成验证码
        user.setCheckCode("123456");
        Timestamp date = new Timestamp(System.currentTimeMillis());
        user.setCodeUpdateTime(date);
        UserAuth update_user = userAuthService.saveUserAuth(user);
        if (update_user == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户保存失败", null);
        }

        /* 发送邮箱验证码 */
        //TODO

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "邮件发送成功", null);
    }

    /**
     * 注册第二步：激活用户
     * @param data: {username:'xxx',check_code: 'xxx'}
     * @return Msg
     */
    @PostMapping("/register")
    public Msg register_step2(@RequestBody Map<String,Object> data) {
        /* 参数合法性检验 */
        Object name_obj = data.get(UserConstant.USERNAME);
        Object check_code_obj = data.get(UserConstant.CHECK_CODE);
        if (name_obj == null || check_code_obj == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误：{\"username\": 'xxx', \"check_code\": 'xxx'}", null);
        }

        /* 获取参数 */
        String name = data.get(UserConstant.USERNAME).toString();
        String check_code = data.get(UserConstant.CHECK_CODE).toString();

        /* 检验邮箱验证码 */
        UserAuth user = userAuthService.getUserAuthByName(name);
        if (user == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户名不存在", null);
        }
        if (!user.getCheckCode().equals(check_code)) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "验证码错误", null);
        }

        /* 检验验证码是否过期: 5 min */
        Timestamp date = new Timestamp(System.currentTimeMillis());
        if (date.getTime() - user.getCodeUpdateTime().getTime() > 1000 * 60 * 5) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "验证码已过期", null);
        }

        /* 激活用户 */
        user.setStatus(UserConstant.STATUS_ACTIVATE);
        user.setCheckCode("");
        int id = user.getUserId();

        /* 初始化用户信息 */
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        userInfo.setNickname(user.getUsername());

        /* 初始化健康档案 */
        MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(id);
        mainRecord.setUpdateTime(date);

        UserAuth update_user = userAuthService.saveUserAuth(user);
        UserInfo update_user_info = userInfoService.saveUserInfo(userInfo);
        MainRecord update_main_record = mainRecordService.updateMainRecord(mainRecord);
        if(update_user_info == null || update_main_record == null || update_user == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户信息初始化失败", null);
        }

        return  MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.RESIGN_SUCCESS_MSG, null);
    }

    @PostMapping("/login")
    public Msg Login(@RequestBody Map<String,Object> data) {
        /* 参数合法性检验 */
        Object name_obj = data.get(UserConstant.USERNAME);
        Object password_obj = data.get(UserConstant.PASSWORD);
        if (name_obj == null || password_obj == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误：{\"username\": 'xxx', \"password\": 'xxx'}", null);
        }

        /* 检验用户名 */
        String name = data.get(UserConstant.USERNAME).toString();
        UserAuth user_auth = userAuthService.getUserAuthByName(name);
        if (user_auth == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户名不存在", null);
        }
        if (user_auth.getStatus() == UserConstant.STATUS_INACTIVATE) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户未激活", null);
        }

        /* 检验密码 */
        String password = data.get(UserConstant.PASSWORD).toString();
        if (!user_auth.getPassword().equals(password)) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "密码错误", null);
        }
        System.out.println(user_auth.getUserInfo());
        UserInfo userInfo = user_auth.getUserInfo();
        JSONObject jsonObject = JSONObject.fromObject(userInfo);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "登录成功",jsonObject);
    }

}
