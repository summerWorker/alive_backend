# README

数据库名：health

在src\main\resources中的`application.properties`修改数据库信息





## 数据库具体字段

<img src="./health.png">

---

## 返回值

* Msg
  * Msg.status：状态值（int）
  * Msg.msg：信息（string）
  * Msg.data：传递的json数据

**以下没有特殊说明，data为null**

## 服务拆分

### 1. 用户管理服务⭐ 

table：user，userAuth

#### 1.1 **登录、注册**

- [x] **"/register/check_name"：**

  检查用户名是否重复 

  ```json
  @RequestBody：  {username: 'xxx'}
  ```

- [x] **"/register/send_email_code"**

  注册第一步：发送邮箱验证码

  ```json
  @RequestBody: 
  { 
    	"username": "test",
      	"password":"test",
  	"email":"test@sjtu.edu.cn"
  }
  ```



​	**TODO**：邮箱发送信息（**目前默认验证码都是123456**）



- [x] **"/register"**

  注册第二步：验证邮箱验证码
  
  ```json
  @RequestBody：
    {
      "username": "test",
        "check_code":"123456"
  }
  ```
  
  

#### 1.2 用户资料的增删改

### 2. 健康数据服务⭐⭐⭐

1. > table：latest_record、weight、pressure、sleep.....

2. **健康档案**服务：
  
   1. 个人健康档案的**创建、更新、查询**
   
   - [ ] **"/update_main_record"**
   
     获取最新的个人档案：仅完成了体重身高的更新
   
     ```java
     @RequestBody
     {
         "user_id": 1
     }
     @Return
     {
         "status": 1,
         "msg": "成功！",
         "data": {
             "calorieConsume": 0.0,
             "calorieIn": 0.0,
             "exerciseTime": 0.0,
             "healthAdvice": "",
             "healthScore": 0,
             "heartRate": 0.0,
             "height": 1.79,
             "pressure": 0.0,
             "recordId": 1,
             "sleepTime": 0.0,
             "updateTime": {
                 "date": 10,
                 "day": 1,
                 "hours": 16,
                 "minutes": 43,
                 "month": 6,
                 "nanos": 31000000,
                 "seconds": 52,
                 "time": 1688978632031,
                 "timezoneOffset": -480,
                 "year": 123
             },
             "userId": 1,
             "weight": 57.2
         }
     }
     ```
   
     
   
2.   接口：`ModifyRecord` , `GetRecord`, `GetRecordByDate`
  
   3. **档案健康分析**
      1.    接口：`GetHealthCondition`,`GetHealthAdvice`
   
3. **体重**记录
  
   **同身高**，把height改成weight

   
   
5. 身高记录：

   - [x] **"/height"**

     获取某人某天身高

     ```java
     @RequestBody
     {
         "user_id": 1,
         "date":"2023-07-10",
     }
     @Return
     {
         "status": 1,
         "msg": "成功！",
         "data": {
             "date": "2023-07-10",
             "height": 1.78,
             "userId": 1
         }
     }

   - [x] **"/user_height"**

     获取某人所有身高记录

     ```java
     @RequestBody
     {
         "user_id": 1,
     }
     @Return
     {
         "status": 1,
         "msg": "成功！",
         "data": {
             "heights": [
                 {
                     "date": "2022-07-08",
                     "height": 1.72,
                     "userId": 1
                 },
                 {
                     "date": "2023-07-08",
                     "height": 1.78,
                     "userId": 1
                 },
                 {
                     "date": "2023-07-10",
                     "height": 1.78,
                     "userId": 1
                 }
             ]
         }
     }
     ```

   - [x] **"/add_height"**

     增加体重：同日覆盖

     ```java
     @RequestBody
     {
         "user_id": 1,
         "date":"2023-07-10",
         "height":1.79
     }
     @Return
     {
         "status": 1,
         "msg": "添加成功",
         "data": {
             "date": "2023-07-10",
             "height": 1.79,
             "userId": 1
         }
     }
     ```


   - [x] **"/period_height"**

     ```java
     @RequestBody
     {
         "user_id": 1,
         "start_date":"2022-07-10",
         "end_date":"2023-07-10"
     }
     @Return
     {
         "status": 1,
         "msg": "成功！",
         "data": {
             "heights": [
                 {
                     "date": "2023-07-08",
                     "height": 1.78,
                     "userId": 1
                 },
                 {
                     "date": "2023-07-10",
                     "height": 1.79,
                     "userId": 1
                 }
             ]
         }
     }
     ```

     

4. **饮食记录**服务：

   1. **记录饮食**情况（输入饮食种类），后端计算卡路里摄入量
   2.   接口：`AddMeal`

   3. **饮食情况分析**，用户查询饮食、卡路里摄入，健康建议，推荐食谱
      1.    接口：`GetMeal`,`GetCalorieIn`,`GetRecipe`,`GetRecipeAdvice`

5. **运动记录**服务：
   1. **记录运动**情况（输入类型），后端计算卡路里消耗量
      1.    接口：`AddSport`s
   2. **运动情况分析**，用户查询运动、卡路里消耗，健康建议
      1.    接口：`GetSports`,`GetCalorieOut`,`GetSportsAdvice`

6. **睡眠记录**服务：

   - [x] **"/day_sleep"**

     ​	当天睡眠详细数据

     ​	bedtime位于：20：00 - 8：00的算作当天（20：00那天）
     
     ```json
     @RequestBody： 
     {
         "user_id": 1,
         "start_date":"2023-05-28",
         "end_date":"2023-06-29"
     }
         
     @Return
     {
       "status": 1,
       "msg": "成功！",
       "data": {
         "sleep_detail": [
           {
             "date": "2023-06-22",
             "detailValue": {
               "awake_count": 0,
               "sleep_awake_duration": 0,
               "bedtime": 1687365360,
               "sleep_deep_duration": 88,
               "sleep_light_duration": 335,
               "sleep_rem_duration": 88,
               "duration": 511,
               "items": [
                 {
                   "end_time": 1687366140,
                   "state": 3,
                   "start_time": 1687365360
                 },
                 /* ... */
               ],
               "date_time": 1687392000,
               "timezone": 32,
               "wake_up_time": 1687396020
             },
             "id": 1,
             "length": 712,
             "userId": 1
           },
           {
             "date": "2023-06-22",
             /* ... */
           }
         ]
       }
     }
     ```

   

   1. **记录睡眠**情况：从手环、手机获取，手动输入
   2. 接口：`AddSleep`
   3. **睡眠分析** ：查询睡眠、分析睡眠，健康建议
   4. 接口：`GetSleepByDate`,`GetSleepAdvice`

7. **数据分析**服务：所有的分析计算工作放在这里，别处的分析服务只是把数据发个这个接口
   1. **特定类别数据**分析：供其他微服务调用，获取数据+数据类型，返回分析建议。推荐行为。
   2.   接口：`GetAdvice`,`GetRecommend`
   
   - [x] **"/bmi"**
   
     分析BMI
   
     ```java
     @RequestBody： 
     {
         "user_id": 1,
     }
     @Return
     {
         "status": 1,
         "msg": "成功！",
         "data": {
             "bmi": 32.488628979857054,
             "analysis": "肥胖",
             "advice": "您的体重属于肥胖范畴，建议您采取积极的饮食和运动计划，减少高热量食物的摄入，增加有氧运动和力量训练。此外，定期监测血压、血脂和血糖等指标，以降低肥胖相关的健康风险。"
         }
     }
     ```
   
     

### 3. 目标管理服务⭐⭐

1. **设置健康目标**

2. 接口：**“/set_goal”**

   **目前支持的目标类型：**

   ```java
   public static final String WEIGHT_GOAL = "weight_goal";
   public static final String CALORIE_GOAL = "calorie_goal";
   public static final String STEP_GOAL = "step_goal";
   public static final String SLEEP_LENGTH_GOAL = "sleep_length_goal";
   public static final String BEDTIME_GOAL = "bedtime_goal";
   ```

   ```java
   //-----------------------体重目标---------------------------
   @RequestBody
   {
       "user_id": 1,
       "goalName":"weight_goal",
       "goalDdl":"2023-07-20",		//可以没有
       "goalKey1":50							//可以没有（仅仅修改ddl）
   }
   @Return
   {
       "status": 1,
       "msg": "成功！",
       "data": {
           "goalDdl": "2023-07-20",
           "goalKey1": 50.0,
           "goalKey2": "",
           "goalName": "weight_goal",
           "userId": 1,
           "uuid": {
               "leastSignificantBits": -7264058575103256904,
               "mostSignificantBits": 7515402167304932717
           }
       }
   }
   //-----------------------入睡时间---------------------------
   @RequestBody
   {
       "user_id": 1,
       "goalName":"bedtime_goal",
       "goalKey2":"22:00"				// 时间可以不精确到秒
   }
   @Return
   {
       "status": 1,
       "msg": "成功！",
       "data": {
           "goalDdl": "",
           "goalKey1": 0.0,
           "goalKey2": "22:00",
           "goalName": "bedtime_goal",
           "userId": 1,
           "uuid": {
               "leastSignificantBits": -8289062499044848900,
               "mostSignificantBits": 5559718899068063605
           }
       }
   }
   
   //-----------------------睡眠时长---------------------------
   @RequestBody
   {
       "user_id": 1,
       "goalName":"sleep_length_goal",
       "goalKey1":482 			//单位是分钟
   }
   @Return
   {
       "status": 1,
       "msg": "成功！",
       "data": {
           "goalDdl": "",
           "goalKey1": 482.0,
           "goalKey2": "",
           "goalName": "sleep_length_goal",
           "userId": 1,
           "uuid": {
               "leastSignificantBits": -6105039736368870774,
               "mostSignificantBits": 7119916374390884221
           }
       }
   }
   //-----------------------步数目标---------------------------
   @RequestBody
   {
       "user_id": 1,
       "goalName":"step_goal",
       "goalKey1":20000
   }
   //-----------------------卡路里目标---------------------------
   @RequestBody
   {
       "user_id": 1,
       "goalName":"calorie_goal",
       "goalKey1":20000
   }
   ```

   **"/goals"**：获取一个人的所有目标

   ```java
   @RequestBody
   {
       "user_id": 1
   }
   @Return
   {
       "status": 1,
       "msg": "成功！",
       "data": {
           "goal": [
               {
                   "goalDdl": "",
                   "goalKey1": 20000.0,
                   "goalKey2": "",
                   "goalName": "step_goal",
                   "userId": 1,
                   "uuid": {
                       "leastSignificantBits": -6293041641716376402,
                       "mostSignificantBits": 4516348637078179217
                   }
               },
               {
                   "goalDdl": "",
                   "goalKey1": 0.0,
                   "goalKey2": "22:00",
                   "goalName": "bedtime_goal",
                   "userId": 1,
                   "uuid": {
                       "leastSignificantBits": -8289062499044848900,
                       "mostSignificantBits": 5559718899068063605
                   }
               },
               {
                   "goalDdl": "",
                   "goalKey1": 482.0,
                   "goalKey2": "",
                   "goalName": "sleep_length_goal",
                   "userId": 1,
                   "uuid": {
                       "leastSignificantBits": -6105039736368870774,
                       "mostSignificantBits": 7119916374390884221
                   }
               },
               {
                   "goalDdl": "2023-07-20",
                   "goalKey1": 50.0,
                   "goalKey2": "",
                   "goalName": "weight_goal",
                   "userId": 1,
                   "uuid": {
                       "leastSignificantBits": -7264058575103256904,
                       "mostSignificantBits": 7515402167304932717
                   }
               },
               {
                   "goalDdl": "",
                   "goalKey1": 20000.0,
                   "goalKey2": "",
                   "goalName": "calorie_goal",
                   "userId": 1,
                   "uuid": {
                       "leastSignificantBits": -8949422151435618306,
                       "mostSignificantBits": -8616090509369653940
                   }
               }
           ]
       }
   }
   ```

   **“/goal_name”**：获取特定目标

   ```java
   @RequestBody
   {
       "user_id": 1,
       "goalName":"calorie_goal"
   }
   @Return
   {
       "status": 1,
       "msg": "成功！",
       "data": {
           "goalDdl": "",
           "goalKey1": 20000.0,
           "goalKey2": "",
           "goalName": "calorie_goal",
           "userId": 1,
           "uuid": {
               "leastSignificantBits": -8949422151435618306,
               "mostSignificantBits": -8616090509369653940
           }
       }
   }
   ```

   

3. **跟踪健康目标的服务：修改完成情况、查询完成情况、统计分析**

4.  接口：`AddImplement` , `GetImplement` ,`AnalyseGoals` 

5. **目标提醒**服务：

6.  TODO：这里是后端主动通知前端吗？用websocket

## 数据库设计

1. 用户管理服务：
   1. 表名：user
      - 字段：user_id, nickname, phone, gender, ....
   2. 表名：user_auth
      - 字段：user_id, username, password, email 【username，email不重复？】....
2. 健康档案服务：每个类别只保留最新记录时间
   1. 表名：health_records
      - 字段：id, user_id, update_date, pressure, height, weight, ave_sleep ....
3. 饮食记录服务：
   1. 表名：diet_records
      - 字段：id, user_id, meal_type【数组？】, meal_time, calories, record_date
4. 运动记录服务：
   1. 表名：exercise_records
      - 字段：id, user_id, exercise_type, duration, calories_burned, record_date
5. 睡眠记录服务：
   1. 表名：sleep_records
      - 字段：id, user_id, sleep_time, deep_sleep, shallow_sleep, dream, record_date
6. 目标管理服务：
   1. 表名：goals
      - 字段：id, user_id, goal_type, goal_description, target_value, start_date, end_date, implement_degree

## 技术

1. MySql关系型数据库
2. WebSocket双向通信
3. AI推荐
4. 登录邮箱验证（可选）
