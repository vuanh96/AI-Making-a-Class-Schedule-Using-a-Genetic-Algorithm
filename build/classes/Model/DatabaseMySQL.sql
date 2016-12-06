CREATE DATABASE  IF NOT EXISTS `ai` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ai`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ai
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `class_id` int(11) NOT NULL,
  `professor_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `class_lab` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `professor_id` (`professor_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`),
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,1,1,4,0),(2,5,1,4,0),(3,8,2,4,1),(4,14,2,4,1),(5,2,3,3,1),(6,3,3,3,1),(7,13,4,3,0),(8,15,4,3,0),(9,4,5,3,1),(10,6,5,3,1),(11,19,6,4,1),(12,20,6,4,1),(13,10,7,4,0),(14,11,7,2,0),(15,4,7,2,0),(16,17,8,4,0),(17,13,8,2,0),(18,1,9,2,0),(19,5,9,4,0),(20,3,9,2,0),(21,7,10,2,0),(22,9,10,2,0),(23,9,10,2,0),(24,21,11,2,0),(25,12,11,2,0),(26,22,12,2,0),(27,15,12,2,0),(28,18,13,3,1),(29,16,13,3,1),(30,19,13,3,1),(31,20,13,4,1),(32,5,13,4,1),(33,2,13,4,0),(34,2,14,4,0),(35,3,14,4,0),(36,10,14,4,0),(37,1,15,4,0),(38,12,15,4,0),(39,10,15,3,0),(40,14,15,3,0),(41,1,16,3,0),(42,2,16,3,0),(43,14,17,3,0),(44,13,17,3,1),(45,13,17,2,1),(46,5,18,2,1),(47,22,18,2,1),(48,3,18,2,1),(49,8,18,2,1),(50,20,19,2,1),(51,8,19,2,1),(52,7,20,2,1),(53,16,20,2,0),(54,6,20,4,1),(55,11,1,4,1),(56,11,2,3,1),(57,6,3,3,1),(58,15,13,3,0),(59,7,13,3,0),(60,1,13,3,0),(61,2,13,3,0),(62,3,13,3,0),(63,4,13,3,0),(64,5,13,3,0),(65,6,1,3,0),(66,7,2,3,0),(67,21,3,3,0),(68,9,4,3,0),(69,10,5,3,0),(70,11,6,4,0),(71,13,7,4,0),(72,13,8,2,0),(73,14,9,2,0),(74,15,10,2,0),(75,10,11,2,0),(76,14,6,4,0);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Cấu trúc dữ liệu và giải thuật'),(2,'Kiến trúc máy tính'),(3,'Hệ điều hành'),(4,'Kĩ thuật lập trình'),(5,'Toán rời rạc'),(6,'Mạng máy tính'),(7,'Cơ sở dữ liệu'),(8,'Lập trình hướng đối tượng'),(9,'Linux và phần mềm mã nguồn mở'),(10,'Trí tuệ nhân tạo'),(11,'Nhập môn công nghệ phần mềm'),(12,'Nhập môn Công nghệ thông tin và'),(13,'Tin học đại cương'),(14,'Phân tích và thiết kế hệ thống thông'),(15,'An toàn và bảo mật thông tin'),(16,'Nhập môn lý thuyết tính toán'),(17,'Nhập môn hệ quản trị cơ sở dữ liệu'),(18,'Thiết kế và phân tích thuật toán'),(19,'Cơ sở dữ liệu nâng cao'),(20,'Các hệ phân tán');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `professor_id` int(11) NOT NULL,
  `professor_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`professor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'PGS.TS. Nguyễn Đức Nghĩa'),(2,'TS. Phạm Đăng Hải'),(3,'ThS. Đỗ Tuấn Anh'),(4,'PGS.TS. Huỳnh Thị Thanh Bình'),(5,'ThS. Nguyễn Duy Hiệp'),(6,'TS. Đinh Viết Sang'),(7,'TS Thân Quang Khoát'),(8,'PGS.TS. Lê Thanh Hương'),(9,'TS. Nguyễn Nhật Quang'),(10,'ThS.Ngô Văn Linh'),(11,'TS. Nguyễn Thị Oanh'),(12,'TS. Nguyễn Thanh Hùng'),(13,'TS. Vũ Thị Hương Giang'),(14,'ThS. Lê Đức Trung'),(15,'ThS. Vũ Đức Vượng'),(16,'ThS. Hoàng Anh Việt'),(17,'ThS. Trịnh Thành Trung'),(18,'TS. Nguyễn Thị Thu Hương'),(19,'TS. Phạm Quang Dũng'),(20,'ThS. Nguyễn Hồng Phương'),(21,'TS. Nguyễn Kim Khánh'),(22,'TS. Ngô Lam Trung');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register` (
  `class_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`class_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES (1,1),(2,2),(3,1),(4,2),(5,1),(6,2),(7,1),(8,2),(9,1),(10,2),(11,1),(12,2),(13,1),(14,2),(15,1),(16,3),(17,4),(18,3),(19,4),(20,3),(21,4),(21,8),(22,3),(23,4),(24,3),(25,4),(26,3),(27,4),(28,1),(29,2),(29,4),(30,6),(30,7),(31,3),(32,5),(33,4),(33,6),(34,7),(35,10),(36,11),(37,12),(38,3),(39,10),(40,11),(41,12),(42,11),(43,12),(44,10),(45,11),(46,12),(47,4),(48,10),(49,11),(50,12),(51,1),(52,2),(52,11),(53,3),(54,10),(55,12),(56,8),(57,8),(58,8),(59,8),(59,9),(60,7),(60,8),(61,9),(61,11),(62,10),(63,11),(64,12),(65,6),(65,12),(66,10),(67,11),(68,12),(69,8),(70,8),(71,8),(72,8),(73,8),(74,8),(75,3),(76,4),(76,9),(77,8),(77,9);
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room_name` varchar(10) NOT NULL,
  `lab` tinyint(1) NOT NULL,
  `room_size` int(11) NOT NULL,
  `distance` int(11) NOT NULL,
  PRIMARY KEY (`room_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('T-403',0,200,1),('T-405',1,200,1),('TC-204',0,200,0),('TC-308',1,100,0);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentsgroup`
--

DROP TABLE IF EXISTS `studentsgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentsgroup` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(20) DEFAULT NULL,
  `group_size` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentsgroup`
--

LOCK TABLES `studentsgroup` WRITE;
/*!40000 ALTER TABLE `studentsgroup` DISABLE KEYS */;
INSERT INTO `studentsgroup` VALUES (1,'CNTT K59-1',150),(2,'CNTT K59-2',90),(3,'CNTT K58-1',160),(4,'CNTT K58-2',90),(5,'ĐKTĐH K59-1',130),(6,'ĐKTĐH K59-2',80),(7,'ĐTVT K59-1',120),(8,'CNTT K59-3',40),(9,'CNTT K58-3',50),(10,'CNTT K57-1',140),(11,'CNTT K57 -2',90),(12,'CNTT K57-3',120);
/*!40000 ALTER TABLE `studentsgroup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-01 10:42:36
