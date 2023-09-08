package com.alive_backend.utils.analysis;

import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.service.health_data.MainRecordService;
import com.alive_backend.utils.constant.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
public class Age {
    @Autowired
    private MainRecordService mainRecordService;
    public int getAge(int userId) {
        MainRecord mainRecord = mainRecordService.getMainRecordByUserId(userId);
        if (mainRecord == null)
            return -1;
        if (mainRecord.getBirthday() == null)
            return -1;

        Date birthday = mainRecord.getBirthday();
        Date now = new Date(System.currentTimeMillis());
        // 将 java.sql.Date 转换为 java.util.Date
        java.util.Date birthdayUtil = new java.util.Date(birthday.getTime());
        java.util.Date nowUtil = new java.util.Date(now.getTime());

        // 使用 Calendar 计算年龄
        Calendar calBirthday = new GregorianCalendar();
        calBirthday.setTime(birthdayUtil);

        Calendar calNow = new GregorianCalendar();
        calNow.setTime(nowUtil);

        int age = calNow.get(Calendar.YEAR) - calBirthday.get(Calendar.YEAR);

        // 判断是否过生日
        if (calNow.get(Calendar.DAY_OF_YEAR) < calBirthday.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

    public static int getType(int age) {
        if (age == -1)
            return -1;
        if (age == 0)
            return UserConstant.AGE_BABY;
        if (age <= 2)
            return UserConstant.AGE_CHILD_2;
        if (age <= 5)
            return UserConstant.AGE_CHILD_5;
        if (age <= 12)
            return UserConstant.AGE_PRIMARY;
        if (age <= 18)
            return UserConstant.AGE_TEENAGER;
        if (age <= 64)
            return UserConstant.AGE_ADULT;
        return UserConstant.AGE_OLD;
    }
}
