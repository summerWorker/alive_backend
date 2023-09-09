package com.alive_backend.controller.user_info;

import com.alive_backend.annotation.UserLoginToken;
import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.entity.user_info.UserInfo;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.service.user_info.UserAuthService;
import com.alive_backend.service.user_info.UserInfoService;
import com.alive_backend.serviceimpl.TokenService;
import com.alive_backend.serviceimpl.mail.MailService;
import com.alive_backend.utils.AESUtil;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private MainRecordService mainRecordService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MailService mailService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/getCheckCode")
//    @ResponseBody
    public Msg getCheckCode(@RequestBody Map<String, Object> data){
        String email = data.get(UserConstant.EMAIL).toString();
        UserAuth userAuth = userAuthService.getUserAuthByEmail(email);
        if (userAuth != null){
            return MsgUtil.makeMsg(MsgUtil.ERROR,"该邮箱已被注册");
        }
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return MsgUtil.makeMsg(MsgUtil.ERROR,"发送失败");
        }
        redisTemplate.opsForValue().set(email, checkCode, 1, TimeUnit.MINUTES);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS,"发送成功");
    }


    /*
    * register
    */

    @PostMapping("/register")
    public Msg register(@RequestBody Map<String, Object> data){
        Object name_obj = data.get(UserConstant.USERNAME);
        Object password_obj = data.get(UserConstant.PASSWORD);
        Object email_obj = data.get(UserConstant.EMAIL);
        Object checkCode_obj = data.get(UserConstant.CHECK_CODE);

        if (name_obj == null || password_obj == null || email_obj == null || checkCode_obj == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "未传递用户名、密码、邮箱、验证码", null);
        }

        /*check username exists or not*/
        String name = name_obj.toString();
        UserAuth user = userAuthService.getUserAuthByName(name);
        if(user != null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户名已被注册", null);
        }

        /*check email exists or not*/
        String email = email_obj.toString();
        user = userAuthService.getUserAuthByEmail(email);
        if(user != null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "邮箱已被注册", null);
        }

        /*check checkCode*/
//        String checkCode = checkCode_obj.toString();
//        String checkCodeInRedis = (String) redisTemplate.opsForValue().get(email);
//        redisTemplate.delete(email);
//        if(!checkCode.equals(checkCodeInRedis)){
//            return MsgUtil.makeMsg(MsgUtil.ERROR, "验证码错误", null);
//        }

        /*add userAuth*/
        String password = password_obj.toString();
        user = new UserAuth();
        user.setUsername(name);
        user.setPassword(password);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setStatus(UserConstant.STATUS_ACTIVATE);
        userAuthService.saveUserAuth(user);


        int id = user.getUserId();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        userInfo.setNickname(name);
        userInfoService.saveUserInfo(userInfo);

        MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(id);
        Timestamp date = new Timestamp(System.currentTimeMillis());
        mainRecord.setUpdateTime(date);
        mainRecordService.updateMainRecord(mainRecord);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "注册成功", null);
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

        String token = tokenService.getToken(user_auth);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        System.out.println(user_auth.getUserInfo());
        UserInfo userInfo = user_auth.getUserInfo();
        jsonObject.put("userInfo", userInfo);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "登录成功",jsonObject);
    }
    @PostMapping("/login_email")
    public Msg LoginEmail(@RequestBody Map<String,Object> data) {
        /* 参数合法性检验 */
        Object email_ = data.get(UserConstant.EMAIL);
        Object password_obj = data.get(UserConstant.PASSWORD);
        if (email_ == null || password_obj == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "传参错误：{\"email\": 'xxx', \"password\": 'xxx'}", null);
        }

        /* 检验用户名 */
        String email = email_.toString();
        UserAuth user_auth = userAuthService.getUserAuthByEmail(email);
        if (user_auth == null) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "邮箱未注册", null);
        }
        if (user_auth.getStatus() == UserConstant.STATUS_INACTIVATE) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "用户未激活", null);
        }

        /* 检验密码 */
        String password = data.get(UserConstant.PASSWORD).toString();
        try {
            password = AESUtil.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean passwordMatches = passwordEncoder.matches(password, user_auth.getPassword());
        if (!passwordMatches) {
            return MsgUtil.makeMsg(MsgUtil.ERROR, "密码错误", null);
        }

        String token = tokenService.getToken(user_auth);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        System.out.println(user_auth.getUserInfo());
        UserInfo userInfo = user_auth.getUserInfo();
        jsonObject.put("userInfo", userInfo);

        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "登录成功",jsonObject);
    }


    @DeleteMapping("/logout")
    @UserLoginToken
    public Msg Logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        UserAuth userAuth = userAuthService.findUserById(id);
        //add token to blacklist and store in redis
        long leftTime = tokenService.getExpireTime(token) - System.currentTimeMillis();
        redisTemplate.opsForValue().set("jwt_" + token, id, leftTime, TimeUnit.SECONDS);
        System.out.println("success");


        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "登出成功", null);
    }

}
