package com.alive_backend.utils.analysis;

import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.SleepConstant;
import net.sf.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class SleepQuality {
    public static JSONObject analyseDaySleep(JSONObject sleepData) {
        /*
        *  "awake_count": 0,
                    "sleep_awake_duration": 0,
                    "bedtime": 1687365360,
                    "sleep_deep_duration": 88,
                    "sleep_light_duration": 335,
                    "sleep_rem_duration": 88,
                    "duration": 511,
        * */
        int awakeCount = sleepData.getInt(SleepConstant.AWAKE_COUNT),
            awakeTime = sleepData.getInt(SleepConstant.AWAKE_TIME),
            deepSleepTime = sleepData.getInt(SleepConstant.DEEP_SLEEP_TIME),
            lightSleepTime = sleepData.getInt(SleepConstant.LIGHT_SLEEP_TIME),
            remSleepTime = sleepData.getInt(SleepConstant.REM_SLEEP_TIME),
            length = sleepData.getInt(SleepConstant.LENGTH);
        long bedtime = sleepData.getLong(SleepConstant.BEDTIME);

        double awakeRate = (double)awakeTime / length,
               deepSleepRate = (double)deepSleepTime / length,
               lightSleepRate = (double)lightSleepTime / length,
               remSleepRate = (double)remSleepTime / length;

        Instant instant = Instant.ofEpochSecond(bedtime);
        ZoneId zoneId = ZoneId.systemDefault(); // 获取当地的ZoneId
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);  // 将Instant对象转换为当地时间的LocalDateTime对象
        int hour = localDateTime.getHour();


        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SleepConstant.BEDTIME, analyseBedtime(hour));
        return jsonObject;
    }

    // 分析入睡时间
    public static JSONObject analyseBedtime(int hour) {
        JSONObject jsonObject = new JSONObject();
        Random random = new Random();
        if (hour <= 22 && hour > 8) {
            jsonObject.put(Constant.ANALYSIS, SleepConstant.NORMAL_BEDTIME);
            jsonObject.put(Constant.ADVICE, "人体生物钟建议晚上23点前入睡，你做的很好！");
        } else if (hour > 22 || hour <= 2) {
            jsonObject.put(Constant.ANALYSIS, SleepConstant.LATE_BEDTIME);
            jsonObject.put(Constant.ADVICE,SleepConstant.ADVICE_FOR_LATE[random.nextInt(SleepConstant.ADVICE_FOR_LATE.length)]);
        } else {
            jsonObject.put(Constant.ANALYSIS, SleepConstant.STAY_UP_LATE);
            jsonObject.put(Constant.ADVICE, SleepConstant.ADVICE_FOR_STAY_UP[random.nextInt(SleepConstant.ADVICE_FOR_STAY_UP.length)]);
        }
        return jsonObject;
    }
}
