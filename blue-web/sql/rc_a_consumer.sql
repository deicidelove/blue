/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:35:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_a_consumer
-- ----------------------------
DROP TABLE IF EXISTS `rc_a_consumer`;
CREATE TABLE `rc_a_consumer` (
  `consumer_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `consumer_name` varchar(255) DEFAULT NULL,
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品主键',
  `giving_code` varchar(255) CHARACTER SET latin1 DEFAULT NULL COMMENT '中奖code',
  `giving_code_source` varchar(255) DEFAULT NULL COMMENT '获得中奖code来源',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '当前时间',
  PRIMARY KEY (`consumer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_a_consumer
-- ----------------------------
INSERT INTO `rc_a_consumer` VALUES ('1', '王涛', '2', '1', '1333333333', '支付宝', '2017-09-23 22:29:11', '2017-09-23 22:29:11');
SET FOREIGN_KEY_CHECKS=1;
