package com.alive_backend.utils;

import java.util.Calendar;
import java.sql.Date;

public class ParseWeek {
    public static int main(Date date) {

        // 创建 Calendar 实例，并将日期设置为要计算的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取星期几的值，其中 1 表示星期日，2 表示星期一，以此类推
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 打印结果
        return getDayOfWeekString(dayOfWeek);
    }

    private static int getDayOfWeekString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return 7;
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
            default:
               return 0;
        }
    }
}
