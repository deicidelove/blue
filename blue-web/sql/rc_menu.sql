/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-22 23:36:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_menu
-- ----------------------------
DROP TABLE IF EXISTS `rc_menu`;
CREATE TABLE `rc_menu` (
  `id` varchar(64) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `p_code` varchar(255) DEFAULT NULL COMMENT '菜单父编码',
  `p_id` varchar(255) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `is_menu` int(11) DEFAULT NULL COMMENT '是否是菜单',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `status` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_menu
-- ----------------------------
INSERT INTO `rc_menu` VALUES ('000000000000000000', 'root', '0', '0', '系统根目录', '', '1', '0', '1', '1', null, '2017-08-03 18:31:54', null);
INSERT INTO `rc_menu` VALUES ('893287144657780736', 'system', 'root', '000000000000000000', '系统设置', 'system', '1', '1', '10', '1', '', '2017-08-04 09:47:06', null);
INSERT INTO `rc_menu` VALUES ('893288715881807872', 'userList', 'system', '893287144657780736', '用户管理', 'user/list', '1', '2', '1', '1', '', '2017-08-04 09:53:21', '2017-08-07 18:18:39');
INSERT INTO `rc_menu` VALUES ('893304960282787840', 'user/add', 'userList', '893288715881807872', '用户添加', 'user/add', '0', '3', '1', '1', '', '2017-08-04 10:57:54', '2017-08-08 11:02:55');
INSERT INTO `rc_menu` VALUES ('894396523532517376', 'user/edit', 'userList', '893288715881807872', '用户修改', 'user/edit', '0', '3', '1', '1', '', '2017-08-07 11:15:23', '2017-08-07 16:57:52');
INSERT INTO `rc_menu` VALUES ('894473486712438784', 'user/view', 'userList', '893288715881807872', '用户查看', 'user/View', '0', '3', '2', '1', '', '2017-08-07 16:21:12', null);
INSERT INTO `rc_menu` VALUES ('894473651837992960', 'user/delete', 'userList', '893288715881807872', '用户删除', 'user/delete', '0', '3', '4', '1', '', '2017-08-07 16:21:52', null);
INSERT INTO `rc_menu` VALUES ('894475142061621248', 'roleList', 'system', '893287144657780736', '角色管理', 'role/list', '1', '2', '2', '1', '', '2017-08-07 16:27:47', '2017-08-08 10:34:56');
INSERT INTO `rc_menu` VALUES ('894475827880656896', 'role/add', 'roleList', '894475142061621248', '角色添加', 'role/add', '0', '3', '1', '1', '', '2017-08-07 16:30:31', null);
INSERT INTO `rc_menu` VALUES ('894475985452269568', 'role/edit', 'roleList', '894475142061621248', '角色编辑', 'role/edit', '0', '3', '2', '1', '', '2017-08-07 16:31:08', null);
INSERT INTO `rc_menu` VALUES ('894476118730473472', 'role/delete', 'roleList', '894475142061621248', '角色删除', 'role/delete', '0', '3', '2', '1', '', '2017-08-07 16:31:40', '2017-08-07 16:37:24');
INSERT INTO `rc_menu` VALUES ('894476276402749440', 'role/permission', 'roleList', '894475142061621248', '角色配权', 'role/permission', '0', '3', '3', '1', '', '2017-08-07 16:32:18', null);
INSERT INTO `rc_menu` VALUES ('894476950951690240', 'menu/list', 'system', '893287144657780736', '菜单管理', 'menu/list', '1', '2', '2', '1', '', '2017-08-07 16:34:58', null);
INSERT INTO `rc_menu` VALUES ('894477107919323136', 'menu/add', 'menu/list', '894476950951690240', '菜单添加', 'menu/add', '0', '3', '1', '1', '', '2017-08-07 16:35:36', null);
INSERT INTO `rc_menu` VALUES ('894477244926263296', 'menu/edit', 'menu/list', '894476950951690240', '菜单编辑', 'menu/edit', '0', '3', '2', '1', '', '2017-08-07 16:36:08', null);
INSERT INTO `rc_menu` VALUES ('894477420512411648', 'menu/delete', 'menu/list', '894476950951690240', '菜单删除', 'menu/delete', '0', '3', '2', '1', '', '2017-08-07 16:36:50', null);
INSERT INTO `rc_menu` VALUES ('894477851082883072', 'apidoc', 'system', '893287144657780736', 'Api文档', 'swagger-ui.html', '1', '2', '9', '1', '', '2017-08-07 16:38:33', '2017-08-08 09:56:57');
INSERT INTO `rc_menu` VALUES ('894477995903811584', 'database/log', 'system', '893287144657780736', '数据库日志', 'druid', '1', '2', '10', '1', '', '2017-08-07 16:39:07', '2017-08-08 09:56:29');
INSERT INTO `rc_menu` VALUES ('894752734459199488', 'companyList', 'root', '000000000000000000', '公司管理', 'companyList', '1', '1', '1', '1', '', '2017-08-08 10:50:50', null);
INSERT INTO `rc_menu` VALUES ('894769217763540992', 'department/list', 'root', '000000000000000000', '部门管理', 'department/list', '1', '1', '3', '1', '', '2017-08-08 11:56:20', null);
INSERT INTO `rc_menu` VALUES ('910886747682897920', 'act', 'root', '000000000000000000', '活动管理', 'act', '1', '1', '1', '1', '', '2017-09-21 23:21:39', '2017-09-23 21:58:12');
INSERT INTO `rc_menu` VALUES ('911588598329901056', 'goodsconsumerrelate', 'root', '000000000000000000', '中奖人员管理', 'goodsconsumerrelate', '1', '1', '2', '1', '', '2017-09-23 21:50:33', '2017-09-23 21:58:58');
INSERT INTO `rc_menu` VALUES ('911596151210246144', 'goodsconsumerrelatelist', 'goodsconsumerrelate', '911588598329901056', '中奖人员管理', 'goodsconsumerrelate/list', '1', '2', '1', '1', '', '2017-09-23 22:20:34', null);
INSERT INTO `rc_menu` VALUES ('911596420069326848', 'actlist', 'act', '910886747682897920', '活动管理', 'act/list', '1', '2', '1', '1', '', '2017-09-23 22:21:38', null);
INSERT INTO `rc_menu` VALUES ('919942115465625600', 'goods', 'root', '000000000000000000', '商品管理', 'goods', '1', '1', '2', '1', '', '2017-10-16 23:04:27', null);
INSERT INTO `rc_menu` VALUES ('919943161537626112', 'goodsList', 'goods', '919942115465625600', '商品管理', 'goods/list', '1', '2', '1', '1', '', '2017-10-16 23:08:36', null);
INSERT INTO `rc_menu` VALUES ('919949314753560576', 'goodsAdd', 'goodsList', '919943161537626112', '商品添加', 'goods/add', '0', '3', '1', '1', '', '2017-10-16 23:33:03', null);
SET FOREIGN_KEY_CHECKS=1;
