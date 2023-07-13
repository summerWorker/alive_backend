package com.alive_backend.utils.JsonConfig;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

/*
*  java.sql.Date.getHours() 方法在 Java 8 中被废弃，在 Java 9 中被移除
*   为了避免版本问题，重新自定义了日期转换方式
* */
public class CustomJsonConfig extends JsonConfig {
    public CustomJsonConfig() {
        // 设置循环引用检测策略
        setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

        // 注册日期值处理器
        registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
            private final String pattern = "yyyy-MM-dd";

            @Override
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {
                return process(value);
            }

            @Override
            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
                return process(value);
            }

            private Object process(Object value) {
                if (value == null) {
                    return ""; // 处理 null 值
                }
                if (value instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    return sdf.format((Date) value);
                }
                return value;
            }
        });
        // 注册时间值处理器
        registerJsonValueProcessor(Time.class, new JsonValueProcessor() {
            private final String pattern = "HH:mm:ss";

            @Override
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {
                return process(value);
            }

            @Override
            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
                return process(value);
            }

            private Object process(Object value) {
                if (value == null) {
                    return "00:00:00"; // 处理空值
                }
                if (value instanceof Time) {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    return sdf.format((Time) value);
                }
                return value;
            }
        });
    }
}
