/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:35:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_a_goods
-- ----------------------------
DROP TABLE IF EXISTS `rc_a_goods`;
CREATE TABLE `rc_a_goods` (
  `goods_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品主键',
  `act_id` int(11) DEFAULT NULL COMMENT '活动主键',
  `goods_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品名称',
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_pic_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品图片url',
  `goods_detail_pic_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品详情图片',
  `goods_title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_detail` text CHARACTER SET utf8,
  `is_delete` bit(1) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rc_a_goods
-- ----------------------------
INSERT INTO `rc_a_goods` VALUES ('1', '11', '测试', '2.00', '11', '11', null, null, '\0', '2017-09-19 22:30:34', '2017-10-16 23:14:07');
SET FOREIGN_KEY_CHECKS=1;
