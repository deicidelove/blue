/*
Navicat MySQL Data Transfer

Source Server         : amkong
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-29 19:20:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_blue_advert
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_advert`;
CREATE TABLE `tb_blue_advert` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0：广告 1：通知',
  `url` varchar(50) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `context` text COMMENT '文本内容',
  `is_delete` smallint(6) DEFAULT '0' COMMENT '0未删除 1删除',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='蓝鲟广告通知表';

-- ----------------------------
-- Records of tb_blue_advert
-- ----------------------------
INSERT INTO `tb_blue_advert` VALUES ('5', '1', 'D:/pics/111.png', 'yk', '1234', '0', '2017-10-18 23:10:37', '2017-09-28 15:34:19');
INSERT INTO `tb_blue_advert` VALUES ('6', '0', 'D:/pics/111.png', 'ss', 'www', '0', '2017-10-18 23:15:00', '2017-09-28 15:39:21');
INSERT INTO `tb_blue_advert` VALUES ('7', '1', 'D:/pics/无标题.png', 'amkong', 'ww', '0', '2017-10-18 23:10:33', '2017-09-29 14:05:50');
INSERT INTO `tb_blue_advert` VALUES ('8', '1', 'D:/pics/111.png', 'qq', 'ww', '0', '2017-09-29 15:48:32', '2017-09-29 15:48:33');

-- ----------------------------
-- Table structure for tb_blue_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_dept`;
CREATE TABLE `tb_blue_dept` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '科室名字',
  `context` text COMMENT '科室介绍',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='科室表';

-- ----------------------------
-- Records of tb_blue_dept
-- ----------------------------
INSERT INTO `tb_blue_dept` VALUES ('1', '牙科', '牙科', '2017-09-24 00:32:33', '2017-09-24 00:32:31');
INSERT INTO `tb_blue_dept` VALUES ('2', '保健科', '保健科', '2017-09-24 00:33:24', '2017-09-24 00:33:22');
INSERT INTO `tb_blue_dept` VALUES ('3', '修复科', '修复科', '2017-09-24 00:33:27', '2017-09-24 00:33:25');
INSERT INTO `tb_blue_dept` VALUES ('4', '正畸科', '正畸科正畸科正畸科', '2017-09-27 23:40:41', '2017-09-24 00:33:28');
INSERT INTO `tb_blue_dept` VALUES ('7', '种植科', '种植科种植科种植科种植科', '2017-09-28 21:15:33', '2017-09-28 21:15:34');

-- ----------------------------
-- Table structure for tb_blue_doctor_schedule
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_doctor_schedule`;
CREATE TABLE `tb_blue_doctor_schedule` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) NOT NULL COMMENT '员工ID',
  `dept_id` int(11) NOT NULL,
  `shift_time` varchar(255) NOT NULL COMMENT '班次id',
  `dept_name` varchar(255) NOT NULL,
  `staff_name` varchar(255) NOT NULL,
  `count` int(11) NOT NULL COMMENT '被预约次数',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='蓝鲟医生排班表';

-- ----------------------------
-- Records of tb_blue_doctor_schedule
-- ----------------------------
INSERT INTO `tb_blue_doctor_schedule` VALUES ('2', '1', '1', '上午', '牙科', 'amkong', '12', '2017-10-26 19:43:50', '2017-10-23 00:00:00');
INSERT INTO `tb_blue_doctor_schedule` VALUES ('12', '6', '3', '上午', '修复科', 'ykyk', '11', '2017-10-26 17:06:49', '2017-10-23 00:00:00');
INSERT INTO `tb_blue_doctor_schedule` VALUES ('13', '6', '3', '下午', '修复科', 'ykyk', '44', '2017-10-29 16:07:47', '2017-10-29 00:00:00');

-- ----------------------------
-- Table structure for tb_blue_encyclopedias
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_encyclopedias`;
CREATE TABLE `tb_blue_encyclopedias` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0：口腔 1：精选',
  `url` varchar(50) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `context` text COMMENT '文本内容',
  `is_delete` smallint(6) DEFAULT '0' COMMENT '0未删除 1删除',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='蓝鲟百科表';

-- ----------------------------
-- Records of tb_blue_encyclopedias
-- ----------------------------
INSERT INTO `tb_blue_encyclopedias` VALUES ('5', '1', 'D:/pics/Paxos.png', 'yk', '1234', '0', '2017-10-23 19:44:13', '2017-09-28 15:34:19');
INSERT INTO `tb_blue_encyclopedias` VALUES ('6', '0', 'D:/pics/111.png', 'ss', 'www', '0', '2017-10-18 23:15:00', '2017-09-28 15:39:21');
INSERT INTO `tb_blue_encyclopedias` VALUES ('7', '1', 'D:/pics/无标题.png', 'amkong', 'ww', '0', '2017-10-18 23:10:33', '2017-09-29 14:05:50');
INSERT INTO `tb_blue_encyclopedias` VALUES ('8', '1', 'D:/pics/111.png', 'qq', 'ww', '0', '2017-09-29 15:48:32', '2017-09-29 15:48:33');

-- ----------------------------
-- Table structure for tb_blue_hospital
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_hospital`;
CREATE TABLE `tb_blue_hospital` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '0:医院简介 1：医院文化 2：医院环境',
  `url` varchar(255) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `context` text COMMENT '内容',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='蓝鲟医院概况表';

-- ----------------------------
-- Records of tb_blue_hospital
-- ----------------------------
INSERT INTO `tb_blue_hospital` VALUES ('1', '21', 'D:/pics/111.png', '医院简介-推介理由', '医院简介-推介理由', '2017-10-23 14:05:25', '2017-09-22 20:39:23');
INSERT INTO `tb_blue_hospital` VALUES ('2', '1', null, '百年蓝鲟', 'rr', '2017-10-23 13:56:00', '2017-09-22 20:40:20');
INSERT INTO `tb_blue_hospital` VALUES ('3', '0', '', '医院环境', 'ww', '2017-10-23 14:31:46', '2017-09-22 20:40:23');
INSERT INTO `tb_blue_hospital` VALUES ('5', '11', 'D:/pics/无标题.png', 'www', '百年蓝鲟-企业愿景', '2017-10-23 14:06:29', '2017-09-28 21:13:13');
INSERT INTO `tb_blue_hospital` VALUES ('6', '12', 'D:/pics/111.png', 'qqq', 'ww-管理理念', '2017-10-23 14:06:48', '2017-09-29 15:32:39');
INSERT INTO `tb_blue_hospital` VALUES ('7', '13', 'D:/pics/111.png', 'fff', 'hhh-企业使命', '2017-10-23 14:06:54', '2017-09-29 15:32:50');
INSERT INTO `tb_blue_hospital` VALUES ('11', '14', null, 'dd', 'dd-经营理念', '2017-10-23 14:07:01', '2017-10-22 22:27:54');
INSERT INTO `tb_blue_hospital` VALUES ('12', '22', null, 'ee-科室特色', 'ee-科室特色', '2017-10-23 14:05:19', '2017-10-22 22:31:38');
INSERT INTO `tb_blue_hospital` VALUES ('13', '0', 'D:/pics/111.png', '医院环境', 'ww', '2017-10-23 14:11:50', '2017-09-22 20:40:23');

-- ----------------------------
-- Table structure for tb_blue_needwork
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_needwork`;
CREATE TABLE `tb_blue_needwork` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `needNum` int(11) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `wages` varchar(255) DEFAULT NULL,
  `work_time` text,
  `work_address` text,
  `description` text,
  `requirement` text,
  `fringe_benefits` text,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_blue_needwork
-- ----------------------------
INSERT INTO `tb_blue_needwork` VALUES ('1', '前台导医', '12', '大专及以上', '3年工作经验', '10k-15k', '每天8小时工作，单休', '南京市江宁区东山街道新亭路龙湾新寓B06', '1、负责前台的迎宾和接待工作\r\n2、负责到诊顾客的登记、分诊、导诊工作 \r\n3、负责复诊顾客的档案提取及导诊安排 \r\n4、负责前台电话接', '1、中专及以上学历，护理专业应届毕业生优先\r\n2、形象好，气质佳，声音甜美，普通话标准，亲和力较强\r\n3、具有团队精神，适应能力强\r\n4、良好的沟通应变能力和服务意识，有相关工作经验者优先', '带薪年假、节日福利、生日关怀、旅游、五险、专业培训、提供住宿和工作餐', '2017-10-24 10:10:46');
INSERT INTO `tb_blue_needwork` VALUES ('2', '前台导医', '12', '大专及以上', '3年工作经验', '10k-15k', '每天8小时工作，单休', '南京市江宁区东山街道新亭路龙湾新寓B06', '1、负责前台的迎宾和接待工作\r\n2、负责到诊顾客的登记、分诊、导诊工作 \r\n3、负责复诊顾客的档案提取及导诊安排 \r\n4、负责前台电话接', '1、中专及以上学历，护理专业应届毕业生优先\r\n2、形象好，气质佳，声音甜美，普通话标准，亲和力较强\r\n3、具有团队精神，适应能力强\r\n4、良好的沟通应变能力和服务意识，有相关工作经验者优先', '带薪年假、节日福利、生日关怀、旅游、五险、专业培训、提供住宿和工作餐', '2017-10-24 10:10:34');
INSERT INTO `tb_blue_needwork` VALUES ('4', '前台导医', '12', '大专及以上', '3年工作经验', '10k-15k', '每天8小时工作，单休', '南京市江宁区东山街道新亭路龙湾新寓B06', '1、负责前台的迎宾和接待工作\r\n2、负责到诊顾客的登记、分诊、导诊工作 \r\n3、负责复诊顾客的档案提取及导诊安排 \r\n4、负责前台电话接', '1、中专及以上学历，护理专业应届毕业生优先\r\n2、形象好，气质佳，声音甜美，普通话标准，亲和力较强\r\n3、具有团队精神，适应能力强\r\n4、良好的沟通应变能力和服务意识，有相关工作经验者优先', '带薪年假、节日福利、生日关怀、旅游、五险、专业培训、提供住宿和工作餐', '2017-10-24 10:10:34');

-- ----------------------------
-- Table structure for tb_blue_oppointment
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_oppointment`;
CREATE TABLE `tb_blue_oppointment` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) DEFAULT NULL COMMENT '医生员工的ID',
  `staff_name` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `pation_id` int(11) DEFAULT NULL COMMENT '就诊人id',
  `user_id` int(11) DEFAULT NULL COMMENT '预约用户ID',
  `pay_money` float DEFAULT NULL COMMENT '挂号费',
  `context` text COMMENT '内容',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '预约时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='蓝鲟预约表';

-- ----------------------------
-- Records of tb_blue_oppointment
-- ----------------------------
INSERT INTO `tb_blue_oppointment` VALUES ('1', '1', 'amkong', '1', '牙科', '1', '1', '30', '牙疼', '2017-09-28 00:00:00', '2017-09-27 19:41:06');

-- ----------------------------
-- Table structure for tb_blue_pation
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_pation`;
CREATE TABLE `tb_blue_pation` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '就诊人名字',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `is_default` int(255) DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='蓝鲟就诊人信息表';

-- ----------------------------
-- Records of tb_blue_pation
-- ----------------------------

-- ----------------------------
-- Table structure for tb_blue_project
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_project`;
CREATE TABLE `tb_blue_project` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `context` text COMMENT '项目内容',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='蓝鲟项目表';

-- ----------------------------
-- Records of tb_blue_project
-- ----------------------------
INSERT INTO `tb_blue_project` VALUES ('1', 'D:/pics/Paxos.png', '项目', 'aaa', '2017-10-15 11:02:47', '2017-09-23 00:02:33');
INSERT INTO `tb_blue_project` VALUES ('3', 'D:/pics/111.png', 'fff', '333', '2017-09-29 15:33:41', '2017-09-29 15:32:12');
INSERT INTO `tb_blue_project` VALUES ('4', 'D:/pics/111.png', 'ww', 'qq', '2017-09-29 15:34:58', '2017-09-29 15:34:59');
INSERT INTO `tb_blue_project` VALUES ('5', 'D:/pics/111.png', 'amkong', 'www', '2017-09-29 15:57:27', '2017-09-29 15:49:39');
INSERT INTO `tb_blue_project` VALUES ('7', 'D:/pics/Paxos.png', 'qq', 'ee', '2017-09-29 15:58:11', '2017-09-29 15:58:12');
INSERT INTO `tb_blue_project` VALUES ('9', 'D:/pics/无标题.png', '!@#$', 'wert', '2017-09-30 10:46:46', '2017-09-30 10:46:46');

-- ----------------------------
-- Table structure for tb_blue_shift
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_shift`;
CREATE TABLE `tb_blue_shift` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_id` int(11) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL COMMENT '时段（eg：周一上午）',
  `count` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='蓝鲟班次表';

-- ----------------------------
-- Records of tb_blue_shift
-- ----------------------------
INSERT INTO `tb_blue_shift` VALUES ('2', '12', '8:30', '10', '2017-10-29 16:07:25');
INSERT INTO `tb_blue_shift` VALUES ('3', '12', '9:00', '10', '2017-10-29 16:07:25');
INSERT INTO `tb_blue_shift` VALUES ('4', '12', '9:30', '10', '2017-10-29 16:07:25');
INSERT INTO `tb_blue_shift` VALUES ('5', '12', '10:00', '10', '2017-10-29 16:07:25');
INSERT INTO `tb_blue_shift` VALUES ('6', '12', '10:30', '10', '2017-10-29 16:07:25');
INSERT INTO `tb_blue_shift` VALUES ('7', '12', '11:00', '10', '2017-10-29 16:07:25');
INSERT INTO `tb_blue_shift` VALUES ('8', '12', '11:30', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('9', '12', '12:00', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('10', '13', '14:30', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('11', '13', '15:00', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('12', '13', '15:30', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('13', '13', '16:00', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('14', '13', '16:30', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('15', '13', '17:00', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('16', '13', '17:30', '10', '2017-10-29 16:07:26');
INSERT INTO `tb_blue_shift` VALUES ('17', '13', '18:00', '10', '2017-10-29 16:07:26');

-- ----------------------------
-- Table structure for tb_blue_source
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_source`;
CREATE TABLE `tb_blue_source` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL COMMENT '科室id\r\n            ',
  `dept_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL COMMENT '职位id',
  `context` text COMMENT '文本',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='蓝鲟资料表';

-- ----------------------------
-- Records of tb_blue_source
-- ----------------------------
INSERT INTO `tb_blue_source` VALUES ('1', 'sss', '3', '修复科', 'D:/pics/111.png', '18', '牙疼不是病，疼起来要人命', '2017-09-28 20:14:03', '2017-09-22 14:22:40');
INSERT INTO `tb_blue_source` VALUES ('2', '托尔斯泰', '2', '保健科', 'D:/pics/111.png', '18', 'aa', '2017-09-28 20:15:09', '2017-09-22 23:25:21');
INSERT INTO `tb_blue_source` VALUES ('5', 'qq', '2', '保健科', 'D:/pics/111.png', '18', 'ww', '2017-09-29 15:46:52', '2017-09-29 15:46:52');
INSERT INTO `tb_blue_source` VALUES ('6', 'test', '1', '牙科', 'D:/pics/Paxos.png', '18', 'qwert', '2017-10-15 12:25:55', '2017-10-15 12:25:56');

-- ----------------------------
-- Table structure for tb_blue_staff
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_staff`;
CREATE TABLE `tb_blue_staff` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '医生名字',
  `password` varchar(255) NOT NULL,
  `job_num` varchar(50) NOT NULL COMMENT '工号',
  `dept_name` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL COMMENT '科室id',
  `sex` int(6) DEFAULT '0' COMMENT '性别（0：女 1：男）',
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号\r\n            ',
  `introduce` text COMMENT '介绍',
  `call_fee` double(255,0) NOT NULL DEFAULT '0' COMMENT '诊费',
  `position_name` varchar(255) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL COMMENT '职位id',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`sid`),
  UNIQUE KEY `jobNum_index` (`job_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='蓝鲟员工表';

-- ----------------------------
-- Records of tb_blue_staff
-- ----------------------------
INSERT INTO `tb_blue_staff` VALUES ('1', 'amkong', '111111', '001', '牙科', '1', '0', '一楼', '12345678987', '牙科专家，获奖无数牙科专家，获奖无数牙科专家，获奖无数牙科专家，获奖无数牙科专家，获奖无数', '12', '副主任医师', '18', '2017-10-29 16:25:02', '2017-09-23 23:15:15');
INSERT INTO `tb_blue_staff` VALUES ('6', 'ykyk', '111111', '1234', '修复科', '3', '1', '二楼', '1234565432123', '修复科专家', '12', '副主任医师', '18', '2017-10-29 16:25:11', '2017-09-27 17:04:56');

-- ----------------------------
-- Table structure for tb_blue_wantwork
-- ----------------------------
DROP TABLE IF EXISTS `tb_blue_wantwork`;
CREATE TABLE `tb_blue_wantwork` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `want_job` varchar(255) DEFAULT NULL,
  `want_wage` varchar(255) DEFAULT NULL,
  `describe` text,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_blue_wantwork
-- ----------------------------
INSERT INTO `tb_blue_wantwork` VALUES ('1', 'test', '18226543678', '牙科', '牙医', '10000', '呀呀呀呀呀哎呀呀呀呀呀呀呀哎呀呀呀呀呀呀呀哎呀呀qertt12345!@#$', '2017-10-25 14:58:37');
INSERT INTO `tb_blue_wantwork` VALUES ('2', 'test', '18226543678', '牙科', '牙医', '10000', '呀呀呀呀呀哎呀呀呀呀呀呀呀哎呀呀呀呀呀呀呀哎呀呀qertt12345!@#$', '2017-10-25 15:24:45');
