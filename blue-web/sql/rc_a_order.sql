/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_a_order
-- ----------------------------
DROP TABLE IF EXISTS `rc_a_order`;
CREATE TABLE `rc_a_order` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) DEFAULT NULL,
  `goods_num` int(11) DEFAULT NULL COMMENT '购买数量',
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单类型',
  `source` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单来源',
  `status` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rc_a_order
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
