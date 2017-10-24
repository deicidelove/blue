CREATE TABLE `tb_msg_verify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weixinOpenId` varchar(100) COLLATE gb2312_bin DEFAULT NULL,
  `checkCode` varchar(50) COLLATE gb2312_bin DEFAULT NULL,
  `expireTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `openId_index` (`weixinOpenId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312 COLLATE=gb2312_bin;