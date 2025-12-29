-- MySQL dump 10.13  Distrib 9.5.0, for macos26.1 (arm64)
--
-- Host: localhost    Database: admin
-- ------------------------------------------------------
-- Server version	9.5.0

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'd8faec3c-8fd1-11f0-989d-123df829dfe7:1-2422';

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
  `summary` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章摘要',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '文章内容',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `read_count` int DEFAULT '0' COMMENT '阅读量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'SpringBoot3新特性解析','详细介绍SpringBoot3带来的AOT编译等新功能','<p>内容详情...</p>','bob','2023-10-01 10:00:00',102),(2,'Vue3组合式API最佳实践','如何优雅地使用script setup语法糖','<p>内容详情...</p>','bob','2023-10-05 14:20:00',88),(3,'ElementPlus表格组件深度定制','解决表格复杂渲染问题','<p>内容详情...</p>','alice','2023-10-12 09:30:00',350),(4,'MySQL索引优化指南','B+树原理与索引失效场景','<p><span style=\"font-family: 华文仿宋;\">内容详情...</span></p><p><span style=\"font-family: 华文仿宋;\"><strong>sss</strong></span></p><p><br></p><p><span style=\"font-family: 华文仿宋;\"><strong>我是张三</strong></span></p><p><br></p><ul><li><span style=\"font-family: 华文仿宋;\"><u>1sada</u></span></li><li><span style=\"font-family: 华文仿宋;\">212312</span></li><li><span style=\"font-family: 华文仿宋;\">42131231</span></li><li></li></ul><p><span style=\"color: rgb(225, 60, 57); font-family: 华文仿宋;\">TEST</span></p><p><br></p><p><span style=\"color: rgb(225, 60, 57); font-family: 华文仿宋;\">的萨达大厦</span></p>','alice','2023-11-01 16:00:00',500);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `description` varchar(255) DEFAULT NULL COMMENT '部门描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'技术部','负责产品研发、架构设计与维护'),(2,'设计部','负责 UI/UX 设计与用户体验'),(3,'市场部','负责品牌推广与市场活动');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `no` varchar(20) NOT NULL COMMENT '工号',
  `age` int DEFAULT NULL COMMENT '年龄',
  `description` text COMMENT '个人介绍',
  `department_id` int DEFAULT NULL COMMENT '所属部门ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `no` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=1856315405 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (110,'吴娟','女','EMP106',26,'这是第 106 号员工的系统自动生成简介',3,'2025-03-26 23:13:56'),(111,'沈勇','男','EMP107',21,'这是第 107 号员工的系统自动生成简介',2,'2025-12-01 12:44:21'),(112,'阎娜','女','EMP108',40,'这是第 108 号员工的系统自动生成简介',1,'2025-07-20 21:00:37'),(113,'钱洋','女','EMP109',40,'这是第 109 号员工的系统自动生成简介',3,'2025-05-03 13:56:31'),(114,'李军','女','EMP110',50,'这是第 110 号员工的系统自动生成简介',3,'2025-01-11 10:11:25'),(115,'李杰','男','EMP111',23,'这是第 111 号员工的系统自动生成简介',1,'2025-06-03 02:49:01'),(116,'冯超','男','EMP112',23,'这是第 112 号员工的系统自动生成简介',3,'2025-04-29 09:57:42'),(117,'蒋芳','男','EMP113',27,'这是第 113 号员工的系统自动生成简介',2,'2025-12-26 13:04:14'),(118,'魏洋','男','EMP114',48,'这是第 114 号员工的系统自动生成简介',3,'2025-08-24 13:11:17'),(119,'郑明','男','EMP115',29,'这是第 115 号员工的系统自动生成简介',3,'2025-03-26 04:45:14'),(120,'陈勇','女','EMP116',32,'这是第 116 号员工的系统自动生成简介',3,'2025-06-10 11:52:53'),(121,'阎勇','男','EMP117',45,'这是第 117 号员工的系统自动生成简介',2,'2025-04-02 08:32:41'),(122,'陈敏','男','EMP118',38,'这是第 118 号员工的系统自动生成简介',3,'2025-10-17 13:34:46'),(123,'陈明','女','EMP119',35,'这是第 119 号员工的系统自动生成简介',1,'2025-01-07 04:13:49'),(124,'赵超','男','EMP120',24,'这是第 120 号员工的系统自动生成简介',2,'2025-01-17 04:58:56'),(125,'楚军','女','EMP121',43,'这是第 121 号员工的系统自动生成简介',2,'2025-06-15 23:09:13'),(126,'陈超','男','EMP122',41,'这是第 122 号员工的系统自动生成简介',2,'2025-02-21 23:50:12'),(127,'阎军','女','EMP123',29,'这是第 123 号员工的系统自动生成简介',2,'2025-06-30 15:15:19'),(128,'张军','男','EMP124',45,'这是第 124 号员工的系统自动生成简介',1,'2025-09-28 18:28:43'),(129,'郑娟','男','EMP125',39,'这是第 125 号员工的系统自动生成简介',3,'2025-11-29 18:24:06'),(130,'陈军','男','EMP126',48,'这是第 126 号员工的系统自动生成简介',2,'2025-10-14 12:14:38'),(131,'阎洋','男','EMP127',21,'这是第 127 号员工的系统自动生成简介',3,'2025-09-24 15:45:41'),(132,'楚强','男','EMP128',25,'这是第 128 号员工的系统自动生成简介',1,'2025-12-09 17:55:30'),(133,'卫娜','女','EMP129',50,'这是第 129 号员工的系统自动生成简介',1,'2025-07-08 23:53:50'),(134,'魏兰','女','EMP130',27,'这是第 130 号员工的系统自动生成简介',1,'2025-05-20 11:16:58'),(135,'沈英','女','EMP131',36,'这是第 131 号员工的系统自动生成简介',2,'2025-05-25 14:25:58'),(136,'卫杰','男','EMP132',48,'这是第 132 号员工的系统自动生成简介',2,'2025-07-11 14:53:29'),(137,'陈娟','男','EMP133',46,'这是第 133 号员工的系统自动生成简介',2,'2025-10-07 05:26:50'),(138,'钱超','女','EMP134',35,'这是第 134 号员工的系统自动生成简介',2,'2025-01-04 02:06:20'),(139,'吴军','女','EMP135',50,'这是第 135 号员工的系统自动生成简介',2,'2025-12-30 09:07:03'),(150,'沈勇','女','EMP146',28,'这是第 146 号员工的系统自动生成简介',1,'2025-09-08 23:56:48'),(151,'李洋','女','EMP147',33,'这是第 147 号员工的系统自动生成简介',3,'2025-06-11 22:15:24'),(152,'魏强','女','EMP148',45,'这是第 148 号员工的系统自动生成简介',1,'2025-01-04 17:13:08'),(153,'卫娟','女','EMP149',24,'这是第 149 号员工的系统自动生成简介',2,'2025-03-23 01:04:15'),(154,'王明','男','EMP150',32,'这是第 150 号员工的系统自动生成简介',3,'2025-08-23 05:19:33'),(155,'沈强','男','EMP151',31,'这是第 151 号员工的系统自动生成简介',1,'2025-05-21 19:31:56'),(156,'张超','女','EMP152',37,'这是第 152 号员工的系统自动生成简介',2,'2025-06-06 23:50:39'),(157,'沈英','女','EMP153',49,'这是第 153 号员工的系统自动生成简介',3,'2025-04-18 14:22:22'),(158,'杨伟','女','EMP154',45,'这是第 154 号员工的系统自动生成简介',2,'2025-03-11 04:54:11'),(159,'阎静','女','EMP155',21,'这是第 155 号员工的系统自动生成简介',1,'2025-04-21 23:50:27'),(160,'陈敏','男','EMP156',43,'这是第 156 号员工的系统自动生成简介',2,'2025-07-19 02:09:15'),(161,'王杰','女','EMP157',42,'这是第 157 号员工的系统自动生成简介',1,'2025-10-02 12:29:28'),(162,'陈涛','女','EMP158',23,'这是第 158 号员工的系统自动生成简介',3,'2025-04-06 12:51:52'),(163,'李超','男','EMP159',29,'这是第 159 号员工的系统自动生成简介',2,'2025-09-24 06:43:45'),(164,'王英','男','EMP160',27,'这是第 160 号员工的系统自动生成简介',3,'2025-10-27 15:33:14'),(165,'阎强','男','EMP161',30,'这是第 161 号员工的系统自动生成简介',3,'2025-12-10 09:36:43'),(166,'楚娟','男','EMP162',42,'这是第 162 号员工的系统自动生成简介',3,'2025-12-28 05:12:46'),(167,'孙静','女','EMP163',49,'这是第 163 号员工的系统自动生成简介',1,'2025-05-16 10:34:52'),(168,'李磊','男','EMP164',28,'这是第 164 号员工的系统自动生成简介',2,'2025-05-16 13:32:31'),(169,'韩磊','男','EMP165',39,'这是第 165 号员工的系统自动生成简介',1,'2025-06-14 09:16:26'),(170,'孙伟','男','EMP166',28,'这是第 166 号员工的系统自动生成简介',2,'2025-04-25 22:52:11'),(171,'朱丽','女','EMP167',37,'这是第 167 号员工的系统自动生成简介',2,'2025-04-14 12:29:52'),(172,'陈敏','男','EMP168',42,'这是第 168 号员工的系统自动生成简介',2,'2025-04-03 00:42:01'),(173,'蒋敏','男','EMP169',50,'这是第 169 号员工的系统自动生成简介',1,'2025-04-16 15:13:45'),(174,'朱勇','女','EMP170',46,'这是第 170 号员工的系统自动生成简介',3,'2025-10-15 15:20:26'),(175,'魏敏','女','EMP171',34,'这是第 171 号员工的系统自动生成简介',3,'2025-01-01 18:56:50'),(176,'阎兰','男','EMP172',22,'这是第 172 号员工的系统自动生成简介',3,'2025-03-28 01:16:57'),(177,'王勇','男','EMP173',39,'这是第 173 号员工的系统自动生成简介',2,'2025-07-29 17:16:15'),(178,'阎秀','男','EMP174',49,'这是第 174 号员工的系统自动生成简介',2,'2025-05-18 20:42:06'),(179,'杨娟','男','EMP175',29,'这是第 175 号员工的系统自动生成简介',2,'2025-01-21 07:51:21'),(180,'李强','男','EMP176',42,'这是第 176 号员工的系统自动生成简介',1,'2025-01-26 12:43:19'),(181,'郑娟','男','EMP177',47,'这是第 177 号员工的系统自动生成简介',3,'2025-04-11 09:29:53'),(182,'钱静','女','EMP178',43,'这是第 178 号员工的系统自动生成简介',3,'2025-07-10 03:03:18'),(183,'魏伟','女','EMP179',22,'这是第 179 号员工的系统自动生成简介',2,'2025-02-04 08:27:32'),(184,'孙芳','女','EMP180',20,'这是第 180 号员工的系统自动生成简介',3,'2025-07-13 00:13:17'),(185,'沈超','男','EMP181',32,'这是第 181 号员工的系统自动生成简介',1,'2025-02-08 16:07:10'),(186,'楚静','女','EMP182',32,'这是第 182 号员工的系统自动生成简介',2,'2025-11-13 15:40:40'),(187,'孙娟','女','EMP183',32,'这是第 183 号员工的系统自动生成简介',1,'2025-03-11 21:44:42'),(188,'陈超','女','EMP184',21,'这是第 184 号员工的系统自动生成简介',1,'2025-12-20 15:08:56'),(189,'赵兰','女','EMP185',45,'这是第 185 号员工的系统自动生成简介',1,'2025-09-20 04:09:07'),(190,'孙静','女','EMP186',30,'这是第 186 号员工的系统自动生成简介',2,'2025-12-04 18:52:18'),(191,'蒋强','男','EMP187',39,'这是第 187 号员工的系统自动生成简介',2,'2025-03-24 22:24:53'),(192,'沈勇','男','EMP188',50,'这是第 188 号员工的系统自动生成简介',1,'2025-04-08 16:32:38'),(193,'郑芳','男','EMP189',34,'这是第 189 号员工的系统自动生成简介',1,'2025-10-25 18:03:43'),(194,'杨丽','男','EMP190',46,'这是第 190 号员工的系统自动生成简介',1,'2025-09-15 11:20:47'),(195,'卫涛','男','EMP191',29,'这是第 191 号员工的系统自动生成简介',3,'2025-10-23 15:38:12'),(196,'陈杰','男','EMP192',31,'这是第 192 号员工的系统自动生成简介',3,'2025-01-18 00:23:46'),(197,'楚军','男','EMP193',20,'这是第 193 号员工的系统自动生成简介',2,'2025-05-12 21:13:29'),(198,'阎娟','男','EMP194',33,'这是第 194 号员工的系统自动生成简介',3,'2025-12-30 04:43:46'),(199,'杨超','男','EMP195',48,'这是第 195 号员工的系统自动生成简介',3,'2025-03-31 22:29:06'),(200,'朱军','男','EMP196',39,'这是第 196 号员工的系统自动生成简介',2,'2025-04-09 20:51:27'),(201,'孙磊','女','EMP197',49,'这是第 197 号员工的系统自动生成简介',2,'2025-05-05 04:21:45'),(202,'钱伟','男','EMP198',42,'这是第 198 号员工的系统自动生成简介',3,'2025-08-06 01:17:43'),(203,'陈杰','女','EMP199',37,'这是第 199 号员工的系统自动生成简介',3,'2025-09-29 01:05:57'),(204,'张兰','男','EMP200',38,'这是第 200 号员工的系统自动生成简介',3,'2025-12-21 09:16:44'),(224731137,'ALICE','男','EMP1001',NULL,'test',2,'2025-12-28 18:14:22'),(1856315394,'BOB','女','EMP1002',NULL,'test',1,'2025-12-28 18:14:41'),(1856315395,'陈伟','男','EMP136',NULL,'这是第 136 号员工的系统自动生成简介',1,'2025-12-29 18:23:12'),(1856315396,'孙磊','男','EMP137',NULL,'这是第 137 号员工的系统自动生成简介',2,'2025-12-29 18:23:12'),(1856315397,'沈磊','男','EMP138',NULL,'这是第 138 号员工的系统自动生成简介',3,'2025-12-29 18:23:12'),(1856315398,'朱秀','男','EMP139',NULL,'这是第 139 号员工的系统自动生成简介',1,'2025-12-29 18:23:12'),(1856315399,'楚英','女','EMP140',NULL,'这是第 140 号员工的系统自动生成简介',1,'2025-12-29 18:23:12'),(1856315400,'冯敏','女','EMP141',NULL,'这是第 141 号员工的系统自动生成简介',3,'2025-12-29 18:23:12'),(1856315401,'卫英','男','EMP142',NULL,'这是第 142 号员工的系统自动生成简介',3,'2025-12-29 18:23:12'),(1856315402,'卫敏','男','EMP143',NULL,'这是第 143 号员工的系统自动生成简介',1,'2025-12-29 18:23:12'),(1856315403,'陈磊','女','EMP144',NULL,'这是第 144 号员工的系统自动生成简介',3,'2025-12-29 18:23:12'),(1856315404,'吴洋','女','EMP145',NULL,'这是第 145 号员工的系统自动生成简介',3,'2025-12-29 18:23:12');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-正常, 0-锁定',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `admin` tinyint NOT NULL DEFAULT '0' COMMENT '是否是管理员',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '用户头像路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1101295619 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$ZuyHNN3lYWWDLfWY3Tf2AeCGv65XyZD0tKSkCQ194i5RxJ5AhhYKK','admin1@gmail.com',1,'2025-12-28 13:47:10',1,'http://localhost:8080/files/148e230b51d74b58b45e38eefa6b909a.png'),(2,'alice','$2a$10$xRdPZ8ASBp.anGr/XjzZO.uMdeeSv4V6q1ry7N1ueJ99CN2wESZ0G','test@example.com',1,'2025-12-28 13:47:10',0,NULL),(3,'bob','$2a$10$W4CbKPlAPkfxEuitPTpgh.iicay.cwfj6SoiH2efJfFS2R3YX6Q/S','bob12@gmail.com',1,'2025-12-29 10:47:35',0,'http://localhost:8080/files/561369122420469c937b3bb9365b33f8.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-29 19:29:42
