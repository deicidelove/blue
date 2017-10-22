/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `rc_operation_log`;
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

-- ----------------------------
-- Records of rc_operation_log
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
