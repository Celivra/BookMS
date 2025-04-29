-- MySQL dump 10.13  Distrib 9.2.0, for macos15 (arm64)
--
-- Host: localhost    Database: bookMS
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bookname` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `booktype` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `booknumber` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `publishedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'C++从入门到入坟','捏','🐔算机','电子工业出版社',9,'我大C++天下无敌啊！','1231231123123','1112-02-03'),(2,'JavaEE从精通到忘记','从先生','🐔算机','103出版社',17,'JavaEE就tm不是给人学的啊tmd','0987654321321','2004-04-08'),(5,'能力额','捏','数学','捏出版社',1145,'123123123','8976712418927','1999-01-09'),(6,'你妈2222','22你妈','你妈','你妈出版社',222123,'123213123123','7834657831111','3333-12-11'),(10,'23123','123213','1231234','455',5,'10rifjf','1231452365465','2025-04-11');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `userid` bigint DEFAULT NULL,
  `bookid` bigint DEFAULT NULL,
  `borrowdate` date DEFAULT NULL,
  `returndate` date DEFAULT NULL,
  KEY `userid` (`userid`),
  KEY `bookid` (`bookid`),
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`bookid`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (2,2,'2025-04-17','2025-04-18'),(2,2,'2025-04-17','2025-04-17'),(2,2,'2025-04-17','2025-04-17'),(2,2,'2025-04-17','2025-04-17'),(2,1,'2025-04-17','2025-04-17'),(2,1,'2025-04-17','2025-04-17'),(2,1,'2025-04-17','2025-04-18'),(2,2,'2025-04-17','2025-04-17'),(3,1,'2025-04-19',NULL),(2,1,'2025-04-23','2025-04-23'),(2,1,'2025-04-23','2025-04-23'),(2,2,'2025-04-23','2025-04-23'),(2,6,'2025-04-23','2025-04-23'),(2,1,'2025-04-24','2025-04-24'),(2,1,'2025-04-25',NULL);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ticketname` varchar(255) DEFAULT NULL,
  `ticketrank` varchar(2) DEFAULT NULL,
  `content` longtext,
  `reply` varchar(255) DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `replyDate` date DEFAULT NULL,
  `isclosed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `workorder___fk` (`userid`),
  CONSTRAINT `workorder___fk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'hello','高','test',NULL,2,1,'2025-04-29',NULL,0),(2,'world','中','5yuhgf',NULL,2,1,'2025-04-29',NULL,0),(3,'bgfd','高','erfgbnbf',NULL,2,1,'2025-04-29',NULL,0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `power` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123','1876697104522','52xhai@gmail.com',10),(2,'test','123','13573113123','celivra@gmail.co',0),(3,'hahha','123','10086','wuji@kun.com',1),(6,'yuanyu','123','12344','a@qq.com',10),(10,'nie','nienie','10086','1165936315@qq.com',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-29 12:00:10
