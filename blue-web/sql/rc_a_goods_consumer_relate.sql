/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:35:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_a_goods_consumer_relate
-- ----------------------------
DROP TABLE IF EXISTS `rc_a_goods_consumer_relate`;
CREATE TABLE `rc_a_goods_consumer_relate` (
  `goods_consumer_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '参与活动表',
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品主键',
  `consumer_id` int(11) DEFAULT NULL COMMENT '用户主键',
  `consumer_giving_code` varchar(255) DEFAULT NULL COMMENT '用户的中奖号码 ',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`goods_consumer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rc_a_goods_consumer_relate
-- ----------------------------
INSERT INTO `rc_a_goods_consumer_relate` VALUES ('1', '2', '1', '1', '1333333333', '2017-09-19 22:38:48');
SET FOREIGN_KEY_CHECKS=1;
