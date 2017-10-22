/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:35:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_a_giving
-- ----------------------------
DROP TABLE IF EXISTS `rc_a_giving`;
CREATE TABLE `rc_a_giving` (
  `giving_id` int(11) NOT NULL AUTO_INCREMENT,
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品 主键',
  `giving_consumer_id` int(11) DEFAULT NULL COMMENT '用户id',
  `giving_code` varchar(255) DEFAULT NULL COMMENT '中奖号码',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`giving_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rc_a_giving
-- ----------------------------
INSERT INTO `rc_a_giving` VALUES ('2', '12', '12', '12', '12', '2017-09-19 22:26:23');
SET FOREIGN_KEY_CHECKS=1;
