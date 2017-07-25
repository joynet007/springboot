CREATE DATABASE  IF NOT EXISTS `ruankao` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `ruankao`;
-- MySQL dump 10.13  Distrib 5.6.11, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: ruankao
-- ------------------------------------------------------
-- Server version	5.7.10

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
-- Table structure for table `tb_choicequestion`
--

DROP TABLE IF EXISTS `tb_choicequestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_choicequestion` (
  `questionid` varchar(45) COLLATE utf8_bin NOT NULL,
  `name` text COLLATE utf8_bin,
  `answera` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `answerb` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `answerc` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `answerd` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `answere` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `realanswer` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  `sublevel1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sublevel2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sublevel3` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `moniname` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `monicode` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '模拟的编号',
  PRIMARY KEY (`questionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_choicequestion`
--

LOCK TABLES `tb_choicequestion` WRITE;
/*!40000 ALTER TABLE `tb_choicequestion` DISABLE KEYS */;
INSERT INTO `tb_choicequestion` VALUES ('1e0b64a41a1a4ef38b3e345846863c48','333','3333','333','3333','333',NULL,'B',1500736584544,'1001','1001001','1001001001','2017年上半年','1'),('4bac7adfe6e545858fab0ee95a8193f8','5555558888888','123123','123123','123123','123',NULL,'A',1500858675822,'1001','1001001','1001001001','2017年上半年','1'),('5d2a726570b4489889d405a682088875','大数据对产品、企业和产业有着深刻的影响。把信息技术看作是辅助或服务性的工具己经成为过时的观念，管理者应该认识到信息技术的广泛影响，以及怎样利用信息技术来创造有力而持久的竞争优势，（1）将是未来经济社会发展的一个重要特征','数据驱动','数据驱动','数据驱动','数据驱动',NULL,'A',1500738169885,'1001','1001001','1001001001','2015年下半年','2'),('7d974fea2ee74fba990cd9a5980fd5f9','555','555','555','555','555',NULL,'A',1500736986167,'1001','1001001','1001001001','2016年上半年','3'),('82287fd757d44312a6f8b3d866debd52','777','777','777','777','777',NULL,'A',1500737481487,'1001','1001001','1001001001','2016年下半年','4'),('9bc58c2ce8bb4ea6a923809d6c2f7381','444','4444','444','44','444',NULL,'A',1500736854852,'1001','1001001','1001001002','2016年下半年','5'),('cab07527ba7c477ab474ca526f21f857','666','666','6666','6666','6666',NULL,'A',1500737352877,'1001','1001001','1001001002','2016年下半年','6');
/*!40000 ALTER TABLE `tb_choicequestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_choicequestion_explain`
--

DROP TABLE IF EXISTS `tb_choicequestion_explain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_choicequestion_explain` (
  `questionid` varchar(45) COLLATE utf8_bin NOT NULL,
  `mexplain` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`questionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_choicequestion_explain`
--

LOCK TABLES `tb_choicequestion_explain` WRITE;
/*!40000 ALTER TABLE `tb_choicequestion_explain` DISABLE KEYS */;
INSERT INTO `tb_choicequestion_explain` VALUES ('0a0d9e66025243ce9bd4c1fb12862b12','4444'),('1e0b64a41a1a4ef38b3e345846863c48','33333'),('25cf461180f44c869568ee981c8df2c6','55555'),('4bac7adfe6e545858fab0ee95a8193f8','1231231231'),('57fbf68654614804867824ac63c9488e','1111'),('5d2a726570b4489889d405a682088875','大数据对产品、企业和产业有着深刻的影响。把信息技术看作是辅助或服务性的工具己经成为过时的观念，管理者应该认识到信息技术的广泛影响，以及怎样利用信息技术来创造有力而持久的竞争优势，（1）将是未来经济社会发展的一个重要特征'),('7d974fea2ee74fba990cd9a5980fd5f9','55555'),('82287fd757d44312a6f8b3d866debd52','77777'),('9bc58c2ce8bb4ea6a923809d6c2f7381','444444'),('a35465a3cefa46098d35942976c0adef','33333333'),('a7ea8a95342347b2b085256b343895ff','111'),('c0ec64e3fb0940f993645c83d4f31a7e','222'),('cab07527ba7c477ab474ca526f21f857','666666666'),('e5894e83a23f4ed6ad0366e4e636fbe5','1111'),('fab2997bb43c4e90b8d64a1e1fb22cdc','222222222');
/*!40000 ALTER TABLE `tb_choicequestion_explain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_moniyear`
--

DROP TABLE IF EXISTS `tb_moniyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_moniyear` (
  `moniid` bigint(20) NOT NULL AUTO_INCREMENT,
  `moniname` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `mstatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `mindex` int(11) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`moniid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_moniyear`
--

LOCK TABLES `tb_moniyear` WRITE;
/*!40000 ALTER TABLE `tb_moniyear` DISABLE KEYS */;
INSERT INTO `tb_moniyear` VALUES (1,'2017年上半年','status.start',7),(2,'2016年下半年','status.start',6),(3,'2016年上半年','status.start',5),(4,'2015年下半年','status.start',4),(5,'2015年上半年','status.start',3),(6,'2014年下半年','status.start',2),(7,'2014年上半年','status.start',1);
/*!40000 ALTER TABLE `tb_moniyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_question_type`
--

DROP TABLE IF EXISTS `tb_question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_question_type` (
  `questiontypeid` varchar(40) COLLATE utf8_bin NOT NULL,
  `questiontypename` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`questiontypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_question_type`
--

LOCK TABLES `tb_question_type` WRITE;
/*!40000 ALTER TABLE `tb_question_type` DISABLE KEYS */;
INSERT INTO `tb_question_type` VALUES ('1001','章节练习'),('1002','随机练习'),('1003','历年真题');
/*!40000 ALTER TABLE `tb_question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_subject`
--

DROP TABLE IF EXISTS `tb_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_subject` (
  `subjectid` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '科目编码',
  `subjectname` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '科目名称',
  `mstatus` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '状态',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `parentid` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `mlevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_subject`
--

LOCK TABLES `tb_subject` WRITE;
/*!40000 ALTER TABLE `tb_subject` DISABLE KEYS */;
INSERT INTO `tb_subject` VALUES ('1001','系统集成项目管理工程师','mstatus.normal',1499912773024,'rootid',1),('1001001','项目管理类','mstatus.normal',1499927023816,'1001',2),('1001001001','项目立项','mstatus.normal',1499928089399,'1001001',3),('1001001002','项目整体管理','mstatus.normal',1499928103816,'1001001',3),('1001001003','项目范围管理','mstatus.normal',1499928126479,'1001001',3),('1001001004','项目成本管理','mstatus.normal',1499928143147,'1001001',3),('1001001005','项目质量管理','mstatus.normal',1499928183064,'1001001',3),('1001002','信息系统类','mstatus.normal',1499927040684,'1001',2),('1001003','行业应用类','mstatus.normal',1499927062588,'1001',2),('1001004','专题类','mstatus.normal',1499927073521,'1001',2),('1002','信息系统项目管理师','mstatus.normal',1499912877237,'rootid',1),('1003','系统架构设计师','mstatus.normal',1499912892471,'rootid',1),('rootid','科目列表','mstatus.normal',1496993224251,'root',0);
/*!40000 ALTER TABLE `tb_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_subject_type`
--

DROP TABLE IF EXISTS `tb_subject_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_subject_type` (
  `subtypeid` int(11) NOT NULL AUTO_INCREMENT,
  `subtypename` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `subtypestatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `subtypesite` int(11) NOT NULL,
  `picpath` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `createtime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`subtypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_subject_type`
--

LOCK TABLES `tb_subject_type` WRITE;
/*!40000 ALTER TABLE `tb_subject_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_subject_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_userinfo`
--

DROP TABLE IF EXISTS `tb_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_userinfo` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编码唯一自整张',
  `usertel` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户手机号',
  `username` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名称',
  `userpassword` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `mstatus` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '状态 user.stop 停止使用  user.normal 正常使用，默认正常',
  `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `msex` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userinfo`
--

LOCK TABLES `tb_userinfo` WRITE;
/*!40000 ALTER TABLE `tb_userinfo` DISABLE KEYS */;
INSERT INTO `tb_userinfo` VALUES (3,'18611600827','中国人','698d51a19d8a121ce581499d7b701668','mstatus.normal',1499740776459,'msex.male'),(4,'18611600828','袁亮','698d51a19d8a121ce581499d7b701668','mstatus.normal',1499740825767,'msex.male'),(5,'18811131101','居里夫人','698d51a19d8a121ce581499d7b701668','mstatus.normal',1499762971976,'msex.male'),(6,'123','123','698d51a19d8a121ce581499d7b701668','mstatus.normal',1500277687794,'msex.male');
/*!40000 ALTER TABLE `tb_userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-25 10:14:51
