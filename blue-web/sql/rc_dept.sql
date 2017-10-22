/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_dept
-- ----------------------------
DROP TABLE IF EXISTS `rc_dept`;
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

-- ----------------------------
-- Records of rc_dept
-- ----------------------------
INSERT INTO `rc_dept` VALUES ('34', null, '0', '总公司', '总公司', null, null);
INSERT INTO `rc_dept` VALUES ('35', null, '34', '技术部', '技术部', null, null);
INSERT INTO `rc_dept` VALUES ('36', null, '34', '运营部', '运营部', null, null);
INSERT INTO `rc_dept` VALUES ('37', null, '34', '招商部', '招商部', null, null);
SET FOREIGN_KEY_CHECKS=1;
