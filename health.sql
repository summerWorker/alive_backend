-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: health
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blood_pressure`
--

DROP TABLE IF EXISTS `blood_pressure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blood_pressure` (
  `id` binary(16) NOT NULL,
  `date` date DEFAULT NULL,
  `diastolic` int DEFAULT NULL,
  `systolic` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blood_pressure`
--

LOCK TABLES `blood_pressure` WRITE;
/*!40000 ALTER TABLE `blood_pressure` DISABLE KEYS */;
INSERT INTO `blood_pressure` VALUES (_binary 'v��E��6JP\��','2023-09-07',80,110,1),(_binary '\\\�74GǕ|\rr�J','2023-09-01',78,123,1),(_binary '�(�rA+B��S�c<L�)','2023-09-09',91,111,1),(_binary 'Ϝ\�\�K��\�\�.׮\�','2023-09-08',71,111,1);
/*!40000 ALTER TABLE `blood_pressure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blood_sugar`
--

DROP TABLE IF EXISTS `blood_sugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blood_sugar` (
  `id` binary(16) NOT NULL,
  `blood_sugar` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blood_sugar`
--

LOCK TABLES `blood_sugar` WRITE;
/*!40000 ALTER TABLE `blood_sugar` DISABLE KEYS */;
INSERT INTO `blood_sugar` VALUES (_binary 'Hi�m\�H���\�\�\0�ɒ',5,'2023-09-07 12:24:00.000000',1),(_binary 'Ro<��HF\'�֒jd\�\�?',111,'2023-09-07 16:14:00.000000',1),(_binary 'a\�C陕]\��',100,'2023-09-06 14:18:00.000000',1),(_binary '�8\�+rD򠧽2�	̏',90,'2023-09-07 13:05:00.000000',1),(_binary '��g�OvB\'�\Z�m��o',130,'2023-09-07 14:18:00.000000',1);
/*!40000 ALTER TABLE `blood_sugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet`
--

DROP TABLE IF EXISTS `diet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diet` (
  `id` binary(16) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `food_id` binary(16) NOT NULL,
  `type` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `diet_food_id_fk` (`food_id`),
  KEY `diet_user_info_user_id_fk` (`user_id`),
  CONSTRAINT `diet_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diet`
--

LOCK TABLES `diet` WRITE;
/*!40000 ALTER TABLE `diet` DISABLE KEYS */;
INSERT INTO `diet` VALUES (_binary '\�\�w�I\�`p\�8',9,'2023-07-01',_binary '&86�`�J�\�',0,1);
/*!40000 ALTER TABLE `diet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id` binary(16) NOT NULL,
  `calorie` double DEFAULT NULL,
  `carbohydrate` double DEFAULT NULL,
  `dietary_fiber` double DEFAULT NULL,
  `fat` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `protein` double DEFAULT NULL,
  `sodium` double DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (_binary '&86�`�J�\�',254,43.1,6,3.5,'面包','https://img.zcool.cn/community/01e6315d6e20b0a801211f9ef9fe34.jpg@3000w_1l_2o_100sh.jpg',12.3,449,-1),(_binary '�~)�[Gb�}',143,0.1,0,10.5,'煮鸡蛋','https://tse3-mm.cn.bing.net/th/id/OIP-C.z0l0UuFCFclxZ20CnVS0OgHaE8?pid=ImgDet&rs=1',12.1,130,-1);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal`
--

DROP TABLE IF EXISTS `goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goal` (
  `user_id` int NOT NULL,
  `uuid` binary(16) NOT NULL COMMENT '标识符号',
  `goal_name` varchar(36) NOT NULL COMMENT '目标类型：weight、sleep',
  `goal_key1` float DEFAULT NULL COMMENT '浮点数，记录数值',
  `goal_ddl` date DEFAULT NULL COMMENT '目标到期时间，长期目标为空',
  `goal_key2` varchar(255) DEFAULT NULL COMMENT '目标内容，字符串（入睡时间等）',
  PRIMARY KEY (`uuid`),
  KEY `goal_user_info_user_id_fk` (`user_id`),
  CONSTRAINT `goal_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='最新目标记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal`
--

LOCK TABLES `goal` WRITE;
/*!40000 ALTER TABLE `goal` DISABLE KEYS */;
INSERT INTO `goal` VALUES (1,_binary '>�K(\"�M�\�','step_goal',20000,NULL,NULL),(1,_binary 'M(���Ou\�','bedtime_goal',NULL,NULL,'23:59'),(1,_binary 'b�86G}�F\�','sleep_length_goal',482,NULL,NULL),(1,_binary 'hLs�iMm�0\�','weight_goal',50,'2023-09-20',NULL),(1,_binary '�mT�hEL�\�','calorie_goal',20000,NULL,NULL);
/*!40000 ALTER TABLE `goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heart_rate`
--

DROP TABLE IF EXISTS `heart_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heart_rate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detail_value` varchar(255) DEFAULT NULL,
  `time_stamp` bigint DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heart_rate`
--

LOCK TABLES `heart_rate` WRITE;
/*!40000 ALTER TABLE `heart_rate` DISABLE KEYS */;
INSERT INTO `heart_rate` VALUES (1,'120',1694089360987,1);
/*!40000 ALTER TABLE `heart_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `height`
--

DROP TABLE IF EXISTS `height`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `height` (
  `user_id` int NOT NULL COMMENT '用户id',
  `id` binary(16) NOT NULL COMMENT '唯一标识',
  `height` float NOT NULL COMMENT '身高单位米',
  `date` date NOT NULL COMMENT '时间，精确到天',
  PRIMARY KEY (`id`),
  KEY `height___fk` (`user_id`),
  CONSTRAINT `height___fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `height`
--

LOCK TABLES `height` WRITE;
/*!40000 ALTER TABLE `height` DISABLE KEYS */;
INSERT INTO `height` VALUES (1,_binary 'x|��hH��O�=q�',1.78,'2023-09-08'),(1,_binary 'W��d��',1.78,'2023-07-08'),(1,_binary 'W����\�',1.72,'2022-07-08'),(1,_binary '���)�L\�',1.79,'2023-07-10');
/*!40000 ALTER TABLE `height` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_record`
--

DROP TABLE IF EXISTS `main_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `main_record` (
  `user_id` int NOT NULL COMMENT '用户id，不向用户展示',
  `height` float DEFAULT NULL COMMENT '身高，单位米',
  `weight` float DEFAULT NULL COMMENT '体重',
  `exercise_time` float DEFAULT NULL COMMENT '每天平均运动时长',
  `calorie_in` float DEFAULT NULL COMMENT '日平均卡路里摄入量',
  `calorie_consume` float DEFAULT NULL COMMENT '日平均卡路里消耗量',
  `sleep_time` float DEFAULT NULL COMMENT '日平均睡眠时长',
  `pressure` float DEFAULT NULL COMMENT '最近一次血压测量',
  `heart_rate` float DEFAULT NULL COMMENT '最近一次心率测量',
  `health_score` int DEFAULT NULL COMMENT '健康评估得分，百分制，整数',
  `health_advice` varchar(255) DEFAULT NULL COMMENT '健康建议',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最近一次更新时间',
  `record_id` int NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `blood_sugar` double DEFAULT NULL,
  `diastolic_pressure` double DEFAULT NULL,
  `steps` int DEFAULT NULL,
  `systolic_pressure` double DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `main_record_user_info_user_id_fk` (`user_id`),
  CONSTRAINT `main_record_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='健康档案数据，保持最新数据记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_record`
--

LOCK TABLES `main_record` WRITE;
/*!40000 ALTER TABLE `main_record` DISABLE KEYS */;
INSERT INTO `main_record` VALUES (1,1.78,55,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-09-08 08:00:00',1,'2002-11-02',111,91,NULL,111),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-07-13 09:49:09',2,'2023-07-27',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `main_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sleep_detail`
--

DROP TABLE IF EXISTS `sleep_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sleep_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户',
  `date` date NOT NULL,
  `detail_value` text COMMENT 'json对象，具体说明在readme',
  `length` int NOT NULL COMMENT '深浅+做梦总时长，不算清醒，单位：分钟',
  `goal_length` int NOT NULL DEFAULT '-1' COMMENT '目标睡眠时长单位分钟',
  `bedtime_goal` time DEFAULT NULL COMMENT '入睡时间目标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='睡眠的具体数据：深浅睡眠时间段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sleep_detail`
--

LOCK TABLES `sleep_detail` WRITE;
/*!40000 ALTER TABLE `sleep_detail` DISABLE KEYS */;
INSERT INTO `sleep_detail` VALUES (1,1,'2023-06-22','{\"awake_count\":0,\"sleep_awake_duration\":0,\"bedtime\":1687365360,\"sleep_deep_duration\":88,\"sleep_light_duration\":335,\"sleep_rem_duration\":88,\"duration\":511,\"items\":[{\"end_time\":1687366140,\"state\":3,\"start_time\":1687365360},{\"end_time\":1687367220,\"state\":2,\"start_time\":1687366140},{\"end_time\":1687367520,\"state\":3,\"start_time\":1687367220},{\"end_time\":1687367820,\"state\":4,\"start_time\":1687367520},{\"end_time\":1687368480,\"state\":3,\"start_time\":1687367820},{\"end_time\":1687369920,\"state\":2,\"start_time\":1687368480},{\"end_time\":1687370220,\"state\":3,\"start_time\":1687369920},{\"end_time\":1687370580,\"state\":4,\"start_time\":1687370220},{\"end_time\":1687370640,\"state\":3,\"start_time\":1687370580},{\"end_time\":1687370940,\"state\":4,\"start_time\":1687370640},{\"end_time\":1687372440,\"state\":3,\"start_time\":1687370940},{\"end_time\":1687373520,\"state\":2,\"start_time\":1687372440},{\"end_time\":1687373760,\"state\":3,\"start_time\":1687373520},{\"end_time\":1687374780,\"state\":2,\"start_time\":1687373760},{\"end_time\":1687375020,\"state\":3,\"start_time\":1687374780},{\"end_time\":1687375440,\"state\":4,\"start_time\":1687375020},{\"end_time\":1687376280,\"state\":3,\"start_time\":1687375440},{\"end_time\":1687376580,\"state\":4,\"start_time\":1687376280},{\"end_time\":1687381620,\"state\":3,\"start_time\":1687376580},{\"end_time\":1687382280,\"state\":4,\"start_time\":1687381620},{\"end_time\":1687382700,\"state\":3,\"start_time\":1687382280},{\"end_time\":1687383060,\"state\":4,\"start_time\":1687382700},{\"end_time\":1687384800,\"state\":3,\"start_time\":1687383060},{\"end_time\":1687385460,\"state\":2,\"start_time\":1687384800},{\"end_time\":1687387080,\"state\":3,\"start_time\":1687385460},{\"end_time\":1687387380,\"state\":4,\"start_time\":1687387080},{\"end_time\":1687387440,\"state\":3,\"start_time\":1687387380},{\"end_time\":1687387860,\"state\":4,\"start_time\":1687387440},{\"end_time\":1687388460,\"state\":3,\"start_time\":1687387860},{\"end_time\":1687388820,\"state\":4,\"start_time\":1687388460},{\"end_time\":1687389420,\"state\":3,\"start_time\":1687388820},{\"end_time\":1687389780,\"state\":4,\"start_time\":1687389420},{\"end_time\":1687392660,\"state\":3,\"start_time\":1687389780},{\"end_time\":1687393140,\"state\":4,\"start_time\":1687392660},{\"end_time\":1687393380,\"state\":3,\"start_time\":1687393140},{\"end_time\":1687393740,\"state\":4,\"start_time\":1687393380},{\"end_time\":1687393800,\"state\":3,\"start_time\":1687393740},{\"end_time\":1687394100,\"state\":4,\"start_time\":1687393800},{\"end_time\":1687396020,\"state\":3,\"start_time\":1687394100}],\"date_time\":1687392000,\"timezone\":32,\"wake_up_time\":1687396020}',712,-1,NULL),(2,1,'2023-06-23','{\"awake_count\":0,\"sleep_awake_duration\":0,\"bedtime\":1687454880,\"sleep_deep_duration\":108,\"sleep_light_duration\":304,\"sleep_rem_duration\":81,\"duration\":493,\"items\":[{\"end_time\":1687455660,\"state\":3,\"start_time\":1687454880},{\"end_time\":1687458420,\"state\":2,\"start_time\":1687455660},{\"end_time\":1687459920,\"state\":3,\"start_time\":1687458420},{\"end_time\":1687460220,\"state\":4,\"start_time\":1687459920},{\"end_time\":1687460640,\"state\":3,\"start_time\":1687460220},{\"end_time\":1687461240,\"state\":4,\"start_time\":1687460640},{\"end_time\":1687461420,\"state\":3,\"start_time\":1687461240},{\"end_time\":1687461720,\"state\":4,\"start_time\":1687461420},{\"end_time\":1687461900,\"state\":3,\"start_time\":1687461720},{\"end_time\":1687462680,\"state\":2,\"start_time\":1687461900},{\"end_time\":1687462920,\"state\":3,\"start_time\":1687462680},{\"end_time\":1687463820,\"state\":2,\"start_time\":1687462920},{\"end_time\":1687464300,\"state\":3,\"start_time\":1687463820},{\"end_time\":1687465560,\"state\":2,\"start_time\":1687464300},{\"end_time\":1687468320,\"state\":3,\"start_time\":1687465560},{\"end_time\":1687468740,\"state\":4,\"start_time\":1687468320},{\"end_time\":1687471620,\"state\":3,\"start_time\":1687468740},{\"end_time\":1687472400,\"state\":2,\"start_time\":1687471620},{\"end_time\":1687474380,\"state\":3,\"start_time\":1687472400},{\"end_time\":1687474920,\"state\":4,\"start_time\":1687474380},{\"end_time\":1687475040,\"state\":3,\"start_time\":1687474920},{\"end_time\":1687475760,\"state\":4,\"start_time\":1687475040},{\"end_time\":1687480260,\"state\":3,\"start_time\":1687475760},{\"end_time\":1687481340,\"state\":4,\"start_time\":1687480260},{\"end_time\":1687482360,\"state\":3,\"start_time\":1687481340},{\"end_time\":1687482960,\"state\":4,\"start_time\":1687482360},{\"end_time\":1687483020,\"state\":3,\"start_time\":1687482960},{\"end_time\":1687483320,\"state\":4,\"start_time\":1687483020},{\"end_time\":1687484460,\"state\":3,\"start_time\":1687483320}],\"date_time\":1687478400,\"timezone\":32,\"wake_up_time\":1687484460}',496,-1,NULL);
/*!40000 ALTER TABLE `sleep_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps`
--

DROP TABLE IF EXISTS `steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `steps` (
  `steps_id` binary(16) NOT NULL,
  `calories` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `distance` int DEFAULT NULL,
  `goal` int DEFAULT NULL,
  `step` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`steps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps`
--

LOCK TABLES `steps` WRITE;
/*!40000 ALTER TABLE `steps` DISABLE KEYS */;
INSERT INTO `steps` VALUES (_binary 't��_H9�\�',153,'2023-09-01',4,0,15955,1),(_binary 'w#��bALC�\�',153,'2023-09-07',4,2000,15955,1),(_binary 'z��� M�',153,'2023-07-09',4,2000,15955,1),(_binary '�F�rI�\�',153,'2023-07-11',4,10000,2530,1),(_binary '��Ł�Mv\�',153,'2023-04-01',4,10000,2530,1);
/*!40000 ALTER TABLE `steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_auth` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id，不向用户展示',
  `username` varchar(255) NOT NULL COMMENT '唯一不重复',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '唯一不重复',
  `check_code` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '0：未用邮箱激活；1:正常',
  `code_update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
INSERT INTO `user_auth` VALUES (1,'test2','$2a$10$H4EgzQqCyYwQnHYJTY8rbOsJKCBEcMFccTnonuOIDxOU5.0A2KxPK','test2@sjtu.edu.cn',NULL,1,NULL),(2,'test_save','test_save','test_save@',NULL,NULL,NULL),(4,'test','test','test@sjtu.edu.cn','',1,'2023-06-26 14:12:58');
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int NOT NULL COMMENT '用户id，不向用户展示',
  `nickname` varchar(255) NOT NULL COMMENT '用户昵称，可重复',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `gender` int DEFAULT NULL COMMENT '性别，可不填',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_info_user_auth_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_auth` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'test',NULL,NULL),(4,'test2',NULL,NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weight`
--

DROP TABLE IF EXISTS `weight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weight` (
  `user_id` int NOT NULL COMMENT '用户id',
  `weight` float NOT NULL COMMENT '体重单位千克',
  `date` date NOT NULL COMMENT '日期',
  `id` binary(16) NOT NULL COMMENT 'uuid',
  `goal` float NOT NULL DEFAULT '-1' COMMENT '目标单位千克',
  PRIMARY KEY (`id`),
  KEY `weight_user_info_user_id_fk` (`user_id`),
  CONSTRAINT `weight_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='体重记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight`
--

LOCK TABLES `weight` WRITE;
/*!40000 ALTER TABLE `weight` DISABLE KEYS */;
INSERT INTO `weight` VALUES (1,57.2,'2023-02-22',_binary '\04I$�&F�e \�',0),(1,57.2,'2023-07-12',_binary '%�|�&�Fj\�',50),(1,57.2,'2023-04-22',_binary 'q�y��BIT\�',0),(1,62,'2023-07-29',_binary '�~�ɩcB��OHR\0-',50),(1,55,'2023-09-08',_binary '\�\�\�oSC}�=�\0ws',50),(1,57.2,'2023-07-22',_binary '�0�7�}Iī\�',50),(1,57.2,'2023-06-22',_binary '�h^�0 B�',0),(1,62,'2023-08-10',_binary '���RE�',-1);
/*!40000 ALTER TABLE `weight` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-08  0:43:20
