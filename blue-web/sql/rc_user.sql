/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:37:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_user
-- ----------------------------
DROP TABLE IF EXISTS `rc_user`;
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

-- ----------------------------
-- Records of rc_user
-- ----------------------------
INSERT INTO `rc_user` VALUES ('46', null, 'super', '5844591dff62349ce65f98c60baa669e', 'e8z0i', '超级管理员', '2017-06-22 14:26:09', '1', null, null, '8', '34', '1', '2017-06-20 15:12:16', null);
INSERT INTO `rc_user` VALUES ('48', null, 'admin', '439b9b33eb18d644f3b57e182f45b86a', 'bycca', '管理员', null, '1', null, null, '6', null, '1', '2017-06-26 17:31:41', null);
INSERT INTO `rc_user` VALUES ('60', null, 'test', 'f8aaa0fe0f05cc7ed873a9eed2c1fcd5', 't52ot', 'test', null, '1', null, null, '6', null, '1', '2017-09-23 22:13:55', null);
SET FOREIGN_KEY_CHECKS=1;
