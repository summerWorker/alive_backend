package com.alive_backend.utils.analysis;

import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.SleepConstant;
import net.sf.json.JSONObject;
import com.alive_backend.utils.constant.UserConstant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

public class SleepQuality {
    public static JSONObject analyseDaySleep(JSONObject sleepData,int age) {
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
        jsonObject.put(SleepConstant.LENGTH, analyseDuration(length, Age.getType(age)));

        double score = jsonObject.getJSONObject(SleepConstant.BEDTIME).getDouble(Constant.SCORE) * 0.3 +
                       jsonObject.getJSONObject(SleepConstant.LENGTH).getDouble(Constant.SCORE) * 0.7;
        jsonObject.put(Constant.SCORE, score);
        return jsonObject;
    }

    // 分析入睡时间
    public static JSONObject analyseBedtime(int hour) {
        JSONObject jsonObject = new JSONObject();
        Random random = new Random();
        double score = 100.0;
        if (hour <= 22 && hour > 8) {
            jsonObject.put(Constant.ANALYSIS, SleepConstant.NORMAL_BEDTIME);
            jsonObject.put(Constant.ADVICE, "人体生物钟建议晚上23点前入睡，你做的很好！");
            jsonObject.put(Constant.SCORE, score);
        } else if (hour > 22 || hour <= 2) {
            jsonObject.put(Constant.ANALYSIS, SleepConstant.LATE_BEDTIME);
            jsonObject.put(Constant.ADVICE,SleepConstant.ADVICE_FOR_LATE[random.nextInt(SleepConstant.ADVICE_FOR_LATE.length)]);
            if (hour > 23)
                score -= 10;
            else
                score -= 10 * (hour + 2);

            jsonObject.put(Constant.SCORE, score);
        } else {
            jsonObject.put(Constant.ANALYSIS, SleepConstant.STAY_UP_LATE);
            jsonObject.put(Constant.ADVICE, SleepConstant.ADVICE_FOR_STAY_UP[random.nextInt(SleepConstant.ADVICE_FOR_STAY_UP.length)]);
            jsonObject.put(Constant.SCORE, 60 - 10 * (hour - 2));
        }
        return jsonObject;
    }

    // 分析睡眠时长
    public static JSONObject analyseDuration(double length, int age_type) {
        JSONObject jsonObject = new JSONObject();
        if (age_type < 0) { // 无年龄，普遍分析
            if (length >= 7 && length <= 9) {
                jsonObject.put(Constant.ANALYSIS, SleepConstant.NORMAL_DURATION);
                jsonObject.put(Constant.ADVICE, "成年人建议晚上7-9小时睡眠，你做的很好！");
                jsonObject.put(Constant.SCORE, 100.0);
            } else if (length < 7) {
                jsonObject.put(Constant.ANALYSIS, SleepConstant.SHORT_DURATION);
                jsonObject.put(Constant.ADVICE,"睡眠时间较短，成年人建议晚上7-9小时睡眠。");
                jsonObject.put(Constant.SCORE, length / 7 * 100);
            } else {
                jsonObject.put(Constant.ANALYSIS, SleepConstant.LONG_DURATION);
                jsonObject.put(Constant.ADVICE, "睡眠时间较长，成年人建议晚上7-9小时睡眠。");
                jsonObject.put(Constant.SCORE, 80.0);
            }
        } else { // 有年龄，根据年龄分析
            int least, most;
            switch (age_type) {
                case UserConstant.AGE_BABY:
                    least = 11; most = 14;
                    break;
                case UserConstant.AGE_CHILD_2:
                    least = 10; most = 13;
                    break;
                case UserConstant.AGE_CHILD_5:
                    least = 9; most = 11;
                    break;
                case UserConstant.AGE_TEENAGER:
                    least = 8; most = 10;
                    break;
                case UserConstant.AGE_ADULT:
                    least = 7; most = 9;
                    break;
                default:
                    least = 7; most = 8;
                    break;
            }
            if (length >= least && length <= most) {
                jsonObject.put(Constant.ANALYSIS, SleepConstant.NORMAL_DURATION);
                jsonObject.put(Constant.ADVICE, "睡眠时长合理。");
                jsonObject.put(Constant.SCORE, 100.0);
            } else if (length < least) {
                jsonObject.put(Constant.ANALYSIS, SleepConstant.SHORT_DURATION);
                jsonObject.put(Constant.ADVICE,"睡眠时间较短，你的年龄段建议晚上" + least + "-" + most + "小时睡眠。");
                jsonObject.put(Constant.SCORE, length / least * 100);
            } else {
                jsonObject.put(Constant.ANALYSIS, SleepConstant.LONG_DURATION);
                jsonObject.put(Constant.ADVICE, "睡眠时间较长，你的年龄段建议晚上" + least + "-" + most + "小时睡眠。");
                jsonObject.put(Constant.SCORE, 80.0);
            }
        }
        return jsonObject;

    }
}
