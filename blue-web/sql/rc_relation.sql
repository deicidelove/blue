/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_relation
-- ----------------------------
DROP TABLE IF EXISTS `rc_relation`;
CREATE TABLE `rc_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3723 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_relation
-- ----------------------------
INSERT INTO `rc_relation` VALUES ('3679', '1', '8', '2017-06-22 10:40:19', null);
INSERT INTO `rc_relation` VALUES ('3680', '2', '8', '2017-06-22 10:40:30', null);
INSERT INTO `rc_relation` VALUES ('3681', '3', '8', '2017-06-22 10:40:38', null);
INSERT INTO `rc_relation` VALUES ('3682', '4', '8', '2017-06-22 10:40:48', null);
INSERT INTO `rc_relation` VALUES ('3683', '5', '8', '2017-06-22 10:40:55', null);
INSERT INTO `rc_relation` VALUES ('3684', '6', '8', '2017-06-22 10:41:03', null);
INSERT INTO `rc_relation` VALUES ('3685', '7', '8', '2017-06-22 10:41:11', null);
INSERT INTO `rc_relation` VALUES ('3686', '8', '8', '2017-06-22 10:41:18', null);
INSERT INTO `rc_relation` VALUES ('3687', '10', '8', '2017-06-22 10:41:40', null);
INSERT INTO `rc_relation` VALUES ('3688', '11', '8', '2017-06-22 10:41:50', null);
INSERT INTO `rc_relation` VALUES ('3689', '12', '8', '2017-06-22 10:41:58', null);
INSERT INTO `rc_relation` VALUES ('3690', '13', '8', '2017-06-22 10:42:06', null);
INSERT INTO `rc_relation` VALUES ('3704', '1', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3705', '2', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3706', '3', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3707', '4', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3708', '5', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3709', '6', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3710', '7', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3711', '8', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3712', '81', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3719', '5', '17', '2017-07-21 09:41:28', null);
INSERT INTO `rc_relation` VALUES ('3720', '6', '17', '2017-07-21 09:41:28', null);
INSERT INTO `rc_relation` VALUES ('3721', '7', '17', '2017-07-21 09:41:28', null);
INSERT INTO `rc_relation` VALUES ('3722', '8', '17', '2017-07-21 09:41:28', null);
SET FOREIGN_KEY_CHECKS=1;
