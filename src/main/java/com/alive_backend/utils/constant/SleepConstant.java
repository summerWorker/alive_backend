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
    public static final String QUALITY = "quality";
    public static final String AWAKE = "awake";
    /* 分析相关 */
    public static final String NORMAL_BEDTIME = "入睡时间合理";
    public static final String LATE_BEDTIME = "入睡时间过晚";
    public static final String STAY_UP_LATE = "熬夜";
    public static final String EARLY_BEDTIME = "入睡时间过早";

    // 睡眠时长
    public static final String NORMAL_DURATION = "睡眠时长合理";
    public static final String SHORT_DURATION = "睡眠时长过短";
    public static final String LONG_DURATION = "睡眠时长过长";
    public static final String NORMAL_RATE = "睡眠分布合理";
    public static final String LOW_REM_RATE = "REM睡眠过少";
    public static final String LOW_DEEP_RATE = "深睡眠过少";
    public static final String HIGH_LIGHT_RATE = "浅睡眠过多";
    public static final String HIGH_REM_RATE = "REM睡眠过多";

    public static final String NORMAL_AWAKE = "夜间苏醒次数合理";
    public static final String HIGH_AWAKE = "夜间苏醒次数过多";

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

    public static final String[] ADVICE_FOR_HIGH_LIGHT_SLEEP = {
            "创造良好的睡眠环境：保持安静、舒适和黑暗的睡眠环境，远离噪音和刺激性光线，有助于提升深度睡眠的质量。",
            "避免在睡前饮用含咖啡因的饮料，如咖啡、茶和碳酸饮料。咖啡因会影响入睡时间和睡眠质量。",
            "学会一些放松技巧，如深呼吸、冥想、瑜伽等，有助于降低身心压力，促进深度睡眠。",
            "睡前避免使用手机、平板电脑等带有蓝光的设备，建立良好的睡前习惯有助于调整睡眠结构。",
            "晚餐要避免过饱和过度油腻的食物，可以适量摄入含有色氨酸的食物，如牛奶、坚果等，有助于促进睡眠。"
    };
    public static final String[] ADVICE_FOR_HIGH_REM_SLEEP = {
            "睡前保持心情平稳，避免过度兴奋，如看恐怖片、玩刺激性游戏等，有助于提升深度睡眠的质量。",
            "避免在睡前饮用含咖啡因的饮料，如咖啡、茶和碳酸饮料，有助于提高睡眠质量。",
            "避免在睡前进行剧烈运动，如快跑、游泳等，有助于提升深度睡眠的质量。",
    };


}
