-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: common-admin
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `rc_a_act`
--

DROP TABLE IF EXISTS `rc_a_act`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_act` (
  `act_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `act_name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `act_total_num` int(11) DEFAULT NULL COMMENT '当前活动参与总人数',
  `act_giving_num` int(11) DEFAULT NULL COMMENT '参与活动的中奖数',
  `act_periods` int(11) DEFAULT NULL COMMENT '当前活动期数',
  `act_is_expire` int(11) DEFAULT NULL COMMENT '活动是否过期',
  `act_is_delete` int(11) DEFAULT NULL COMMENT '是否删除',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`act_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_act`
--

LOCK TABLES `rc_a_act` WRITE;
/*!40000 ALTER TABLE `rc_a_act` DISABLE KEYS */;
INSERT INTO `rc_a_act` VALUES (2,'1',12,1,12,0,0,'2017-09-18 14:27:46','2017-09-21 15:29:53');
/*!40000 ALTER TABLE `rc_a_act` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_consumer`
--

DROP TABLE IF EXISTS `rc_a_consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_consumer` (
  `consumer_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `consumer_name` varchar(255) DEFAULT NULL,
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品主键',
  `giving_code` varchar(255) DEFAULT NULL COMMENT '中奖code',
  `giving_code_source` varchar(255) DEFAULT NULL COMMENT '获得中奖code来源',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '当前时间',
  `openid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`consumer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_consumer`
--

LOCK TABLES `rc_a_consumer` WRITE;
/*!40000 ALTER TABLE `rc_a_consumer` DISABLE KEYS */;
INSERT INTO `rc_a_consumer` VALUES (1,'王涛',2,1,'1333333333','支付宝','2017-09-23 14:29:11','2017-10-28 15:33:34','1');
/*!40000 ALTER TABLE `rc_a_consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_giving`
--

DROP TABLE IF EXISTS `rc_a_giving`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_giving` (
  `giving_id` int(11) NOT NULL AUTO_INCREMENT,
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品 主键',
  `giving_consumer_id` int(11) DEFAULT NULL COMMENT '用户id',
  `giving_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '中奖号码',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`giving_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_giving`
--

LOCK TABLES `rc_a_giving` WRITE;
/*!40000 ALTER TABLE `rc_a_giving` DISABLE KEYS */;
INSERT INTO `rc_a_giving` VALUES (2,12,12,12,'12','2017-09-19 14:26:23');
/*!40000 ALTER TABLE `rc_a_giving` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_goods`
--

DROP TABLE IF EXISTS `rc_a_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_goods` (
  `goods_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品主键',
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品名称',
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_pic_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品图片url',
  `goods_detail_pic_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品详情图片',
  `goods_title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_detail` text CHARACTER SET utf8,
  `is_delete` bit(1) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `category` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_goods`
--

LOCK TABLES `rc_a_goods` WRITE;
/*!40000 ALTER TABLE `rc_a_goods` DISABLE KEYS */;
INSERT INTO `rc_a_goods` VALUES (1,2,'测试',2.00,'11','11','abcddddddddddddd','ddddddddddddddd','\0','2017-09-19 14:30:34','2017-10-28 15:01:07',NULL);
/*!40000 ALTER TABLE `rc_a_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_goods_consumer_relate`
--

DROP TABLE IF EXISTS `rc_a_goods_consumer_relate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_goods_consumer_relate` (
  `goods_consumer_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '参与活动表',
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品主键',
  `consumer_id` int(11) DEFAULT NULL COMMENT '用户主键',
  `consumer_giving_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户的中奖号码 ',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `open_id` varchar(255) DEFAULT NULL,
  `giving_code_source` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`goods_consumer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_goods_consumer_relate`
--

LOCK TABLES `rc_a_goods_consumer_relate` WRITE;
/*!40000 ALTER TABLE `rc_a_goods_consumer_relate` DISABLE KEYS */;
INSERT INTO `rc_a_goods_consumer_relate` VALUES (1,2,1,1,'1333333333','2017-09-19 14:38:48','1',NULL);
/*!40000 ALTER TABLE `rc_a_goods_consumer_relate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_goods_img`
--

DROP TABLE IF EXISTS `rc_a_goods_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_goods_img` (
  `goods_img_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品主键',
  `img_type` varchar(100) DEFAULT NULL COMMENT '图片类型（列表图、广告图）',
  `goods_img_url` varchar(100) DEFAULT NULL COMMENT '图片url',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`goods_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_goods_img`
--

LOCK TABLES `rc_a_goods_img` WRITE;
/*!40000 ALTER TABLE `rc_a_goods_img` DISABLE KEYS */;
INSERT INTO `rc_a_goods_img` VALUES (1,1,'list_img','http://localhost:8888/images/icon-91.png','2017-10-27 15:25:10','2017-10-28 15:08:27');
/*!40000 ALTER TABLE `rc_a_goods_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_order`
--

DROP TABLE IF EXISTS `rc_a_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_order` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) DEFAULT NULL,
  `goods_num` int(11) DEFAULT NULL COMMENT '购买数量',
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单类型',
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单来源',
  `status` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `open_id` varchar(255) DEFAULT NULL COMMENT '微信唯一标识',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pre_pay_id` varchar(100) DEFAULT NULL COMMENT '微信预支付账号',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_order`
--

LOCK TABLES `rc_a_order` WRITE;
/*!40000 ALTER TABLE `rc_a_order` DISABLE KEYS */;
INSERT INTO `rc_a_order` VALUES (5,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:41:01','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:41:01',NULL),(6,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:41:52','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:41:52',NULL),(7,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:43:10','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:43:10',NULL),(8,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:43:54','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:43:54',NULL),(9,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:44:09','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:44:09',NULL),(10,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:48:18','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:48:18',NULL),(11,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:51:45','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:51:45',NULL),(12,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:51:57','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:51:57',NULL),(13,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:54:11','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:54:11',NULL),(14,1,1,'wxPayCode','wxPayCode','wzf','2017-10-29 15:55:05','okbfzs2TG2WwpAgWOBq97ZOxfUHY','2017-10-29 15:55:05',NULL);
/*!40000 ALTER TABLE `rc_a_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_wx_detail`
--

DROP TABLE IF EXISTS `rc_a_wx_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_wx_detail` (
  `sid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_wx_detail`
--

LOCK TABLES `rc_a_wx_detail` WRITE;
/*!40000 ALTER TABLE `rc_a_wx_detail` DISABLE KEYS */;
INSERT INTO `rc_a_wx_detail` VALUES (1,'1','1','../images/icon-23.png','塔头');
/*!40000 ALTER TABLE `rc_a_wx_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_a_wx_user`
--

DROP TABLE IF EXISTS `rc_a_wx_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_a_wx_user` (
  `sid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_a_wx_user`
--

LOCK TABLES `rc_a_wx_user` WRITE;
/*!40000 ALTER TABLE `rc_a_wx_user` DISABLE KEYS */;
INSERT INTO `rc_a_wx_user` VALUES (2,'1','111');
/*!40000 ALTER TABLE `rc_a_wx_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_dept`
--

DROP TABLE IF EXISTS `rc_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `simple_name` varchar(45) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_dept`
--

LOCK TABLES `rc_dept` WRITE;
/*!40000 ALTER TABLE `rc_dept` DISABLE KEYS */;
INSERT INTO `rc_dept` VALUES (34,NULL,0,'总公司','总公司',NULL,NULL),(35,NULL,34,'技术部','技术部',NULL,NULL),(36,NULL,34,'运营部','运营部',NULL,NULL),(37,NULL,34,'招商部','招商部',NULL,NULL);
/*!40000 ALTER TABLE `rc_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_dict`
--

DROP TABLE IF EXISTS `rc_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_dict`
--

LOCK TABLES `rc_dict` WRITE;
/*!40000 ALTER TABLE `rc_dict` DISABLE KEYS */;
INSERT INTO `rc_dict` VALUES (16,0,0,'状态',NULL),(17,1,16,'启用',NULL),(18,2,16,'禁用',NULL),(29,0,0,'性别',NULL),(30,1,29,'男',NULL),(31,2,29,'女',NULL),(35,0,0,'账号状态',NULL),(36,1,35,'启用',NULL),(37,2,35,'冻结',NULL),(38,3,35,'已删除',NULL);
/*!40000 ALTER TABLE `rc_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_login_log`
--

DROP TABLE IF EXISTS `rc_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_name` varchar(255) DEFAULT NULL,
  `user_id` int(65) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `succeed` varchar(255) DEFAULT NULL,
  `message` text,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_login_log`
--

LOCK TABLES `rc_login_log` WRITE;
/*!40000 ALTER TABLE `rc_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `rc_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_menu`
--

DROP TABLE IF EXISTS `rc_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_menu` (
  `id` varchar(64) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `p_code` varchar(255) DEFAULT NULL COMMENT '菜单父编码',
  `p_id` varchar(255) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `is_menu` int(11) DEFAULT NULL COMMENT '是否是菜单',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `status` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_menu`
--

LOCK TABLES `rc_menu` WRITE;
/*!40000 ALTER TABLE `rc_menu` DISABLE KEYS */;
INSERT INTO `rc_menu` VALUES ('000000000000000000','root','0','0','系统根目录','',1,0,1,1,NULL,'2017-08-03 18:31:54',NULL),('893287144657780736','system','root','000000000000000000','系统设置','system',1,1,10,1,'','2017-08-04 09:47:06',NULL),('893288715881807872','userList','system','893287144657780736','用户管理','user/list',1,2,1,1,'','2017-08-04 09:53:21','2017-08-07 18:18:39'),('893304960282787840','user/add','userList','893288715881807872','用户添加','user/add',0,3,1,1,'','2017-08-04 10:57:54','2017-08-08 11:02:55'),('894396523532517376','user/edit','userList','893288715881807872','用户修改','user/edit',0,3,1,1,'','2017-08-07 11:15:23','2017-08-07 16:57:52'),('894473486712438784','user/view','userList','893288715881807872','用户查看','user/View',0,3,2,1,'','2017-08-07 16:21:12',NULL),('894473651837992960','user/delete','userList','893288715881807872','用户删除','user/delete',0,3,4,1,'','2017-08-07 16:21:52',NULL),('894475142061621248','roleList','system','893287144657780736','角色管理','role/list',1,2,2,1,'','2017-08-07 16:27:47','2017-08-08 10:34:56'),('894475827880656896','role/add','roleList','894475142061621248','角色添加','role/add',0,3,1,1,'','2017-08-07 16:30:31',NULL),('894475985452269568','role/edit','roleList','894475142061621248','角色编辑','role/edit',0,3,2,1,'','2017-08-07 16:31:08',NULL),('894476118730473472','role/delete','roleList','894475142061621248','角色删除','role/delete',0,3,2,1,'','2017-08-07 16:31:40','2017-08-07 16:37:24'),('894476276402749440','role/permission','roleList','894475142061621248','角色配权','role/permission',0,3,3,1,'','2017-08-07 16:32:18',NULL),('894476950951690240','menu/list','system','893287144657780736','菜单管理','menu/list',1,2,2,1,'','2017-08-07 16:34:58',NULL),('894477107919323136','menu/add','menu/list','894476950951690240','菜单添加','menu/add',0,3,1,1,'','2017-08-07 16:35:36',NULL),('894477244926263296','menu/edit','menu/list','894476950951690240','菜单编辑','menu/edit',0,3,2,1,'','2017-08-07 16:36:08',NULL),('894477420512411648','menu/delete','menu/list','894476950951690240','菜单删除','menu/delete',0,3,2,1,'','2017-08-07 16:36:50',NULL),('894477851082883072','apidoc','system','893287144657780736','Api文档','swagger-ui.html',1,2,9,1,'','2017-08-07 16:38:33','2017-08-08 09:56:57'),('894477995903811584','database/log','system','893287144657780736','数据库日志','druid',1,2,10,1,'','2017-08-07 16:39:07','2017-08-08 09:56:29'),('894752734459199488','companyList','root','000000000000000000','公司管理','companyList',1,1,1,1,'','2017-08-08 10:50:50',NULL),('894769217763540992','department/list','root','000000000000000000','部门管理','department/list',1,1,3,1,'','2017-08-08 11:56:20',NULL),('910886747682897920','act','root','000000000000000000','活动管理','act',1,1,1,1,'','2017-09-21 23:21:39','2017-09-23 21:58:12'),('911588598329901056','goodsconsumerrelate','root','000000000000000000','中奖人员管理','goodsconsumerrelate',1,1,2,1,'','2017-09-23 21:50:33','2017-09-23 21:58:58'),('911596151210246144','goodsconsumerrelatelist','goodsconsumerrelate','911588598329901056','中奖人员管理','goodsconsumerrelate/list',1,2,1,1,'','2017-09-23 22:20:34',NULL),('911596420069326848','actlist','act','910886747682897920','活动管理','act/list',1,2,1,1,'','2017-09-23 22:21:38',NULL),('919942115465625600','goods','root','000000000000000000','商品管理','goods',1,1,2,1,'','2017-10-16 23:04:27',NULL),('919943161537626112','goodsList','goods','919942115465625600','商品管理','goods/list',1,2,1,1,'','2017-10-16 23:08:36',NULL),('919949314753560576','goodsAdd','goodsList','919943161537626112','商品添加','goods/add',0,3,1,1,'','2017-10-16 23:33:03',NULL);
/*!40000 ALTER TABLE `rc_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_notice`
--

DROP TABLE IF EXISTS `rc_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_notice`
--

LOCK TABLES `rc_notice` WRITE;
/*!40000 ALTER TABLE `rc_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `rc_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_operation_log`
--

DROP TABLE IF EXISTS `rc_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT,
  `log_type` varchar(255) DEFAULT NULL,
  `log_name` varchar(255) DEFAULT NULL,
  `user_id` int(65) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `method` text,
  `create_time` datetime DEFAULT NULL,
  `succeed` varchar(255) DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_operation_log`
--

LOCK TABLES `rc_operation_log` WRITE;
/*!40000 ALTER TABLE `rc_operation_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `rc_operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_privilege`
--

DROP TABLE IF EXISTS `rc_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_privilege` (
  `role_id` int(11) DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_privilege`
--

LOCK TABLES `rc_privilege` WRITE;
/*!40000 ALTER TABLE `rc_privilege` DISABLE KEYS */;
INSERT INTO `rc_privilege` VALUES (8,'893287144657780736','2017-09-04 23:31:24'),(8,'893288715881807872','2017-09-04 23:31:24'),(8,'893304960282787840','2017-09-04 23:31:24'),(8,'894396523532517376','2017-09-04 23:31:24'),(8,'894473486712438784','2017-09-04 23:31:24'),(8,'894473651837992960','2017-09-04 23:31:24'),(8,'894475142061621248','2017-09-04 23:31:24'),(8,'894475827880656896','2017-09-04 23:31:24'),(8,'894475985452269568','2017-09-04 23:31:24'),(8,'894476118730473472','2017-09-04 23:31:24'),(8,'894476276402749440','2017-09-04 23:31:24'),(8,'894476950951690240','2017-09-04 23:31:24'),(8,'894477107919323136','2017-09-04 23:31:24'),(8,'894477244926263296','2017-09-04 23:31:24'),(8,'894477420512411648','2017-09-04 23:31:24'),(8,'894477851082883072','2017-09-04 23:31:24'),(8,'894477995903811584','2017-09-04 23:31:24'),(8,'894752734459199488','2017-09-04 23:31:24'),(8,'894769217763540992','2017-09-04 23:31:24'),(8,'904728257406959616','2017-09-04 23:31:25'),(6,'919942115465625600','2017-10-16 23:46:43'),(6,'919943161537626112','2017-10-16 23:46:43'),(6,'919949314753560576','2017-10-16 23:46:43'),(6,'910886747682897920','2017-10-16 23:46:43'),(6,'911596420069326848','2017-10-16 23:46:43'),(6,'919942115465625600','2017-10-16 23:46:43'),(6,'919943161537626112','2017-10-16 23:46:43'),(6,'919949314753560576','2017-10-16 23:46:43'),(6,'910886747682897920','2017-10-16 23:46:43'),(6,'911596420069326848','2017-10-16 23:46:43'),(6,'911588598329901056','2017-10-16 23:46:43'),(6,'911596151210246144','2017-10-16 23:46:43'),(6,'919942115465625600','2017-10-16 23:46:43'),(6,'919943161537626112','2017-10-16 23:46:43'),(6,'919949314753560576','2017-10-16 23:46:43');
/*!40000 ALTER TABLE `rc_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_relation`
--

DROP TABLE IF EXISTS `rc_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3723 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_relation`
--

LOCK TABLES `rc_relation` WRITE;
/*!40000 ALTER TABLE `rc_relation` DISABLE KEYS */;
INSERT INTO `rc_relation` VALUES (3679,1,8,'2017-06-22 10:40:19',NULL),(3680,2,8,'2017-06-22 10:40:30',NULL),(3681,3,8,'2017-06-22 10:40:38',NULL),(3682,4,8,'2017-06-22 10:40:48',NULL),(3683,5,8,'2017-06-22 10:40:55',NULL),(3684,6,8,'2017-06-22 10:41:03',NULL),(3685,7,8,'2017-06-22 10:41:11',NULL),(3686,8,8,'2017-06-22 10:41:18',NULL),(3687,10,8,'2017-06-22 10:41:40',NULL),(3688,11,8,'2017-06-22 10:41:50',NULL),(3689,12,8,'2017-06-22 10:41:58',NULL),(3690,13,8,'2017-06-22 10:42:06',NULL),(3704,1,6,'2017-06-26 12:46:09',NULL),(3705,2,6,'2017-06-26 12:46:09',NULL),(3706,3,6,'2017-06-26 12:46:09',NULL),(3707,4,6,'2017-06-26 12:46:09',NULL),(3708,5,6,'2017-06-26 12:46:09',NULL),(3709,6,6,'2017-06-26 12:46:09',NULL),(3710,7,6,'2017-06-26 12:46:09',NULL),(3711,8,6,'2017-06-26 12:46:09',NULL),(3712,81,6,'2017-06-26 12:46:09',NULL),(3719,5,17,'2017-07-21 09:41:28',NULL),(3720,6,17,'2017-07-21 09:41:28',NULL),(3721,7,17,'2017-07-21 09:41:28',NULL),(3722,8,17,'2017-07-21 09:41:28',NULL);
/*!40000 ALTER TABLE `rc_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_role`
--

DROP TABLE IF EXISTS `rc_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`name`),
  UNIQUE KEY `unique_role_value` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_role`
--

LOCK TABLES `rc_role` WRITE;
/*!40000 ALTER TABLE `rc_role` DISABLE KEYS */;
INSERT INTO `rc_role` VALUES (6,'管理员','admin',NULL,'2017-06-20 15:07:13','2017-06-26 12:46:09',1),(8,'超级管理员','super',NULL,'2017-06-20 15:08:45','2017-09-23 22:13:16',1),(17,'用户','user',NULL,'2017-09-23 22:13:08','2017-09-23 22:13:13',1);
/*!40000 ALTER TABLE `rc_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rc_user`
--

DROP TABLE IF EXISTS `rc_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rc_user`
--

LOCK TABLES `rc_user` WRITE;
/*!40000 ALTER TABLE `rc_user` DISABLE KEYS */;
INSERT INTO `rc_user` VALUES (46,NULL,'super','5844591dff62349ce65f98c60baa669e','e8z0i','超级管理员','2017-06-22 14:26:09',1,NULL,NULL,8,34,1,'2017-06-20 15:12:16',NULL),(48,NULL,'admin','439b9b33eb18d644f3b57e182f45b86a','bycca','管理员',NULL,1,NULL,NULL,6,NULL,1,'2017-06-26 17:31:41',NULL),(60,NULL,'test','f8aaa0fe0f05cc7ed873a9eed2c1fcd5','t52ot','test',NULL,1,NULL,NULL,6,NULL,1,'2017-09-23 22:13:55',NULL);
/*!40000 ALTER TABLE `rc_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'common-admin'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-29 23:58:02
