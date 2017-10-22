/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_login_log
-- ----------------------------
DROP TABLE IF EXISTS `rc_login_log`;
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

-- ----------------------------
-- Records of rc_login_log
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
