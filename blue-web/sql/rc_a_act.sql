/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:34:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_a_act
-- ----------------------------
DROP TABLE IF EXISTS `rc_a_act`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rc_a_act
-- ----------------------------
INSERT INTO `rc_a_act` VALUES ('2', '1', '12', '1', '12', '0', '0', '2017-09-18 22:27:46', '2017-09-21 23:29:53');
SET FOREIGN_KEY_CHECKS=1;
