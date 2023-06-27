-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: health
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `main_record`
--

DROP TABLE IF EXISTS `main_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `main_record` (
  `user_id` int NOT NULL COMMENT '用户id，不向用户展示',
  `height` float DEFAULT NULL COMMENT '身高',
  `weight` int DEFAULT NULL COMMENT '体重',
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
  PRIMARY KEY (`record_id`),
  KEY `main_record_user_info_user_id_fk` (`user_id`),
  CONSTRAINT `main_record_user_info_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='健康档案数据，保持最新数据记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_record`
--

LOCK TABLES `main_record` WRITE;
/*!40000 ALTER TABLE `main_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_record` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int NOT NULL  COMMENT '用户id，不向用户展示',
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
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-27 11:44:26
