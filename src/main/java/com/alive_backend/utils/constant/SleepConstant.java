package com.alive_backend.utils.constant;
/*
       *  "awake_count": 0,
                   "sleep_awake_duration": 0,
                   "bedtime": 1687365360,
                   "sleep_deep_duration": 88,
                   "sleep_light_duration": 335,
                   "sleep_rem_duration": 88,
                   "duration": 511,
       * */
public class SleepConstant {
    public static final String AWAKE_COUNT = "awake_count";
    public static final String AWAKE_TIME = "sleep_awake_duration";
    public static final String DEEP_SLEEP_TIME = "sleep_deep_duration";
    public static final String LIGHT_SLEEP_TIME = "sleep_light_duration";
    public static final String REM_SLEEP_TIME = "sleep_rem_duration";
    public static final String BEDTIME = "bedtime";
    public static final String LENGTH = "duration";
    /* 分析相关 */
    public static final String NORMAL_BEDTIME = "入睡时间合理";
    public static final String LATE_BEDTIME = "入睡时间过晚";
    public static final String STAY_UP_LATE = "熬夜";
    public static final String EARLY_BEDTIME = "入睡时间过早";

    //String 数组
    public static final String[] ADVICE_FOR_LATE = {
            "睡前尽量避免刺激性活动和使用电子设备，例如手机、电脑和电视。创建一个安静、舒适、有利于睡眠的环境。",
            "避免在睡前饮用含咖啡因的饮料，如咖啡、茶和碳酸饮料。咖啡因会影响入睡时间和睡眠质量。",
            "23点至次日3点，是肝脏最佳排毒时段，推荐在23点之前入睡，以保证肝脏排毒功能正常运作。",
            "保持规律的作息时间，每天都在相同的时间段睡觉和起床，有助于培养身体的生物钟，提高睡眠质量。",
            "建立放松的睡前习惯，如喝一杯温牛奶、泡个热水澡或进行冥想和呼吸练习，有助于放松身心，促进入眠。",
    };
    public static final String[] ADVICE_FOR_STAY_UP = {
            "在1点后入睡的人，可能会面临熬夜的挑战。确保睡眠环境安静、舒适、黑暗和凉爽。可以使用眼罩、耳塞或调节室温来帮助改善睡眠质量。",
            "晚睡会导致睡眠时相延迟，会导致肠道菌群失调、皮质醇分泌增加、胰岛素抵抗，从而增加罹患肥胖和2型糖尿病的风险。",
            "长期熬夜会导致免疫力下降，容易感冒，还会导致肝脏排毒功能受损，从而引发肝炎、肝硬化等疾病。",
            "控制晚间的兴奋剂摄入，如咖啡因和糖分。避免饮用含咖啡因的饮料和食用过多的甜食，以避免影响睡眠。",
    };


}
