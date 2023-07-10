package com.alive_backend.utils.analysis;

import com.alive_backend.utils.constant.WeightConstant;
import net.sf.json.JSONObject;

public class BMI {
    public static JSONObject analyseBMI(double weight, double height) {
        double bmi = calculateBMI(weight, height);
        String analysis = analyzeBMI(bmi);
        String advice = generateHealthAdvice(analysis);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(WeightConstant.BMI, bmi);
        jsonObject.put(WeightConstant.ANALYSIS, analysis);
        jsonObject.put(WeightConstant.ADVICE, advice);
        return jsonObject;
    }

    // 计算BMI值
    public static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    // 根据BMI值进行健康分析
    public static String analyzeBMI(double bmi) {
        if (bmi < 18.5) {
            return WeightConstant.UNDER_WEIGHT;
        } else if (bmi < 24.9) {
            return WeightConstant.NORMAL_WEIGHT;
        } else if (bmi < 29.9) {
            return WeightConstant.OVER_WEIGHT;
        } else {
            return WeightConstant.FAT_WEIGHT;
        }
    }

    // 根据BMI值的健康分析生成健康建议
    public static String generateHealthAdvice(String analysis) {
        switch (analysis) {
            case WeightConstant.UNDER_WEIGHT:
                return WeightConstant.UNDER_WEIGHT_ADVICE;
            case WeightConstant.NORMAL_WEIGHT:
                return WeightConstant.NORMAL_WEIGHT_ADVICE;
            case WeightConstant.OVER_WEIGHT:
                return WeightConstant.OVER_WEIGHT_ADVICE;
            case WeightConstant.FAT_WEIGHT:
                return WeightConstant.FAT_WEIGHT_ADVICE;
            default:
                return "Hello!";
        }
    }
}
