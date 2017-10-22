/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_dict
-- ----------------------------
DROP TABLE IF EXISTS `rc_dict`;
CREATE TABLE `rc_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_dict
-- ----------------------------
INSERT INTO `rc_dict` VALUES ('16', '0', '0', '状态', null);
INSERT INTO `rc_dict` VALUES ('17', '1', '16', '启用', null);
INSERT INTO `rc_dict` VALUES ('18', '2', '16', '禁用', null);
INSERT INTO `rc_dict` VALUES ('29', '0', '0', '性别', null);
INSERT INTO `rc_dict` VALUES ('30', '1', '29', '男', null);
INSERT INTO `rc_dict` VALUES ('31', '2', '29', '女', null);
INSERT INTO `rc_dict` VALUES ('35', '0', '0', '账号状态', null);
INSERT INTO `rc_dict` VALUES ('36', '1', '35', '启用', null);
INSERT INTO `rc_dict` VALUES ('37', '2', '35', '冻结', null);
INSERT INTO `rc_dict` VALUES ('38', '3', '35', '已删除', null);
SET FOREIGN_KEY_CHECKS=1;
