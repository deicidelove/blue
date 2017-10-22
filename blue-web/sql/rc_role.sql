/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_role
-- ----------------------------
DROP TABLE IF EXISTS `rc_role`;
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

-- ----------------------------
-- Records of rc_role
-- ----------------------------
INSERT INTO `rc_role` VALUES ('6', '管理员', 'admin', null, '2017-06-20 15:07:13', '2017-06-26 12:46:09', '1');
INSERT INTO `rc_role` VALUES ('8', '超级管理员', 'super', null, '2017-06-20 15:08:45', '2017-09-23 22:13:16', '1');
INSERT INTO `rc_role` VALUES ('17', '用户', 'user', null, '2017-09-23 22:13:08', '2017-09-23 22:13:13', '1');
SET FOREIGN_KEY_CHECKS=1;
