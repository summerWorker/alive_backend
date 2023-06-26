package com.alive_backend.controller.user_info;

import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.entity.user_info.UserInfo;
import com.alive_backend.service.user_info.UserAuthService;
import com.alive_backend.service.user_info.UserInfoService;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;

@RestController
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 检查用户名是否重复
     * @param data: {username: 'xxx'}
     * @return Msg
     */
    @GetMapping("/register/check_name")
    public Msg checkName(@RequestBody Map<String,Object> data) {
        /* 参数合法性检验 */
        Object name_obj = data.get(UserConstant.USERNAME);
        if (name_obj == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "未传递用户名{username: 'xxx'}", null);
        }

        String name = data.get(UserConstant.USERNAME).toString();
        UserAuth user = userAuthService.getUserAuthByName(name);
        System.out.println(user);
        if (user == null) {
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
    @GetMapping("/register/send_email_code")
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
        if (user != null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "邮箱已被注册", null);
        }

        /* 保存用户 */
        user = new UserAuth();
        user.setUsername(name); user.setPassword(password); user.setEmail(email);
        user.setStatus(UserConstant.STATUS_INACTIVATE); // 未激活
        // TODO: 生成验证码
        user.setCheckCode("123456");
        Timestamp date = new Timestamp(System.currentTimeMillis());
        user.setCodeUpdateTime(date);
        userAuthService.saveUserAuth(user);

        /* 发送邮箱验证码 */
        //TODO

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "邮件发送成功", null);
    }

    /**
     * 注册第二步：激活用户
     * @param data: {username:'xxx',check_code: 'xxx'}
     * @return Msg
     */
    @GetMapping("/register")
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
        UserAuth update_user = userAuthService.saveUserAuth(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(update_user.getUserId());
        userInfo.setNickname(update_user.getUsername());
        UserInfo update_user_info = userInfoService.saveUserInfo(userInfo);
        if(update_user_info == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户信息保存失败", null);
        }

        return  MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.RESIGN_SUCCESS_MSG, null);
    }

}
