-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: schoolmarket
-- ------------------------------------------------------
-- Server version	5.7.35-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `datetime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'还有吗？',2,'21世纪新小屁孩',1,'2021-09-30 21:22:09'),(2,'什么味道的？',1,'Prrr',1,'2021-09-30 21:30:23'),(3,'是一对吗',4,'Bamoon',5,'2021-10-02 12:21:07'),(4,'有配套ppt吗？',12,'成才',3,'2021-09-07 08:12:38'),(5,'几成新？',9,'肉夹馍',6,'2021-10-03 20:01:56'),(8,'可以可以',1,'Prrrr',4,'2021-10-09 19:37:26'),(9,'有编译原理去年考试题吗？',1,'Prrrr',4,'2021-10-09 19:38:02'),(10,'带袋子吗？',1,'Prrrr',6,'2021-10-11 19:45:09'),(11,'什么牌子的？',13,'甜心玉子烧',6,'2021-10-14 09:23:45'),(12,'有电信的吗？',13,'甜心玉子烧',4,'2021-10-14 10:07:20'),(13,'包括羽毛球吗',5,'亦',5,'2021-10-07 12:09:29'),(14,'还在吗',14,'牛油果',6,'2021-10-20 11:38:34'),(15,'还有几个？',14,'牛油果',2,'2021-10-22 12:02:56'),(16,'工学部能面交吗？',14,'牛油果',2,'2021-10-22 12:04:59'),(17,'还在吗？',14,'牛油果',1,'2021-10-26 16:27:01'),(18,'大英有快班的吗',8,'lililala',4,'2021-10-26 17:11:49'),(19,'排',8,'lililala',7,'2021-10-26 17:20:22'),(20,'金秋舞蹈的小姐姐吗',8,'lililala',8,'2021-10-27 09:40:55'),(21,'急急急',1,'Prrrr',8,'2021-11-16 10:32:40');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderlist`
--

DROP TABLE IF EXISTS `orderlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderlist` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` varchar(45) DEFAULT NULL,
  `buyerid` int(11) DEFAULT NULL,
  `buyername` varchar(45) DEFAULT NULL,
  `sellerid` int(11) DEFAULT NULL,
  `sellername` varchar(45) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  `productname` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `img_url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderlist`
--

LOCK TABLES `orderlist` WRITE;
/*!40000 ALTER TABLE `orderlist` DISABLE KEYS */;
INSERT INTO `orderlist` VALUES (1,'2021-10-28 12:00:02',17,'无',1,'Prrrr',10,'openGL计算机图形学课本','10','../../static/store/opengl.JPG'),(2,'2021-10-30 14:23:21',1,'Prrrr',15,'酱汁牛肉味蚕豆',11,'小电驴','1000','../../static/store/dianlv.jpg'),(3,'2021-09-11 12:09:11',14,'牛油果',16,'wwwfb',13,'小米巨能写','9.9','../../static/store/pen.JPG'),(4,'2021-10-23 11:13:49',14,'牛油果',7,'喜刷刷',2,'晾衣架','1','../../static/store/yijia.jpg'),(5,'2021-10-03 08:55:51',1,'Prrrr',4,'bamoon',4,'计院期末考试资料','5','../../static/store/exam.JPG'),(6,'2021-10-04 10:12:52',5,'亦',4,'bamoon',4,'计院期末考试资料','5','../../static/store/exam.JPG');
/*!40000 ALTER TABLE `orderlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productname` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `img_url` varchar(45) DEFAULT NULL,
  `detail` varchar(45) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `owner_name` varchar(45) DEFAULT NULL,
  `datetime` varchar(45) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'洗发水','25.0','生活百货','../../static/store/shampoo.jpg','欧莱雅赠品',3,'Wqq','2021-09-29 21:29',0),(2,'晾衣架','1','生活百货','../../static/store/yijia.jpg','买多了',7,'喜刷刷','2021-10-19 19:02',0),(3,'算法设计与分析课本','8元','书籍教材','../../static/store/book.jpg','九成新',3,'Wqq','2021-09-02 12:13',0),(4,'计院期末考试资料','5元/门','书籍教材','../../static/store/exam.JPG','计算机学院往年期末题，压缩包',4,'bamoon','2021-10-01 16:23',0),(5,'羽毛球拍','60元','生活百货','../../static/store/yumaoqiu.jpg','99.99%成新，刚拆20min就放弃了',6,'wuwuw','2021-09-28 22:09',0),(6,'瑜伽垫','20','生活百货','../../static/store/yujiadian.jpg','80*60*0.8，灰色',1,'Prrrr','2021-10-02 20:33',0),(7,'AirPods','800','手机数码','../../static/store/airpods.jpg','教育优惠入手，全新未拆封',5,'亦','2021-10-25 20:34',0),(8,'唐宫夜宴演出服','150','服装鞋帽','../../static/store/tang.jpg','八成新，仅演出穿过一次，配饰齐全，含头饰',14,'牛油果','2021-10-25 23:55',0),(9,'出电影票','60','其他','../../static/store/shaqiu.jpg','博纳两张本周六沙丘电影票，68买的，中间位置',8,'lililala','2021-10-27 15:35',0),(10,'openGL计算机图形学课本','10','书籍教材','../../static/store/opengl.JPG','第五版，中文，影印版',1,'Prrrr','2021-10-28 09:01',1),(11,'小电驴','1000','交通工具','../../static/store/dianlv.jpg','骑了两年，毕业优惠出给学弟学妹，充电桩可充',15,'酱汁牛肉味蚕豆','2021-10-28 09:14',1),(12,'卡西欧手表','890','手机数码','../../static/store/watch.png','全新，换也行',12,'成才','2021-09-27 19:23',0),(13,'小米巨能写','9.9','生活百货','../../static/store/pen.JPG','黑色，十只装',16,'wwwfb','2021-09-01 22:13',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `faculty` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `qqnumber` varchar(45) DEFAULT NULL,
  `vxnumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Prrrr','123','计算机学院','信部二舍','1457022550','15172324068'),(2,'21世纪新小屁孩','123','计算机学院','信部十一舍','916306310','15523456789'),(3,'Wqq','123','计算机学院','信部十一舍','836499090','12345678888'),(4,'Bamoon','123','计算机学院','信部十一舍','1360270804','13571111222'),(5,'亦','123','计算机学院','信部十一舍','1041864556','15566622226'),(6,'wuwuw','123','电信','信部二舍','123456789','13888886666'),(7,'喜刷刷','123','测绘学院','信部三舍','987654321','13299990000'),(8,'lililala','123','遥感学院','信部六舍','135798642','13333322223'),(9,'肉夹馍','123','印包','信部十一舍','246897531','18566668888'),(10,'伍六七','123','数学院','梅园六舍','123332233','13677778888'),(11,'许三多','123','数学学院','梅园六舍','222333444','15111123355'),(12,'成才','123','经管学院','湖滨七舍','333444555','12333325556'),(13,'甜心玉子烧','123','电子信息学院','信部二舍','630005387','15635526672'),(14,'牛油果','123','艺术学院','湖滨八舍','702333292','17852438676'),(15,'酱汁牛肉味蚕豆','123','数学院','梅园四舍','1033818633','16682259872'),(16,'wwwfb','123','经管学院','湖滨三舍','12093293','18356678235'),(17,'无','123','计算机学院','国软c7','2771624785','15578651223');
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

-- Dump completed on 2021-11-19 16:08:47
