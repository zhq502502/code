-- -------------企业信息表 ----------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `orgid` bigint(20) unsigned NOT NULL COMMENT '企业ID',
  `orgname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '企业名称',
  `orglogo` varchar(255) DEFAULT NULL COMMENT '企业LOGO',
  `orglogo_blob` longblob DEFAULT NULL COMMENT '企业LOGO源数据',
  `emailloginname` varchar(255) DEFAULT NULL COMMENT '邮箱登录名',
  `emailloginpwd` varchar(255) DEFAULT NULL COMMENT '邮箱登录密码',
  `emailport` varchar(50) DEFAULT NULL COMMENT '邮件认证服务器端口',
  `emailhost` varchar(255) DEFAULT NULL COMMENT '邮件认证服务器地址',
  `emailalias` varchar(255) DEFAULT NULL COMMENT '发件人昵称',
  `conf_order_name` varchar(100) DEFAULT NULL COMMENT '会议排序字段',
  `conf_order_type` varchar(10) DEFAULT NULL COMMENT '会议排序方式', 
  `confuser_tip` varchar(10) DEFAULT NULL COMMENT '新建会议并发数提示',
  PRIMARY KEY (`orgid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- -------------录像文件信息表 ----------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `file_real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件显示名称',
  `file_hash_name` varchar(255) NOT NULL COMMENT '文件存储名称',
  `file_text` text  COMMENT '描述',
  `file_time` datetime NOT NULL COMMENT '文件上传时间',
  `file_size` varchar(32) NOT NULL DEFAULT '0' COMMENT '文件大小',
  `file_view_power` smallint(5) unsigned DEFAULT '1' COMMENT '阅读权限',
  `file_flag` smallint(5) unsigned DEFAULT '0' COMMENT '文件标识',
  `file_down_power` smallint(5) unsigned DEFAULT '1' COMMENT '文件下载权限',
  `file_categories` smallint(5) unsigned DEFAULT '1' COMMENT '文件分类',
  `download_url` varchar(255) NOT NULL COMMENT '现在地址',
  `del_url` varchar(255) NOT NULL COMMENT '删除地址',
  `orgid` bigint(20) NOT NULL COMMENT '企业ID',
  PRIMARY KEY (`id`),
  KEY `orgid` (`orgid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- -------------用户信息表  ----------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(255) NOT NULL COMMENT '用户帐号',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `role` int(20) NOT NULL COMMENT '角色ID',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `account_email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `account_mobile` varchar(32) DEFAULT '' COMMENT '移动电话',
  `account_phone` varchar(32) DEFAULT '' COMMENT '固定电话',
  `password_md5` char(32) NOT NULL COMMENT 'MD5密码',
  `password_text` char(32) DEFAULT '' COMMENT '密码明文',
  `dep_id` int(11) NOT NULL DEFAULT '0' COMMENT '标识',
  `lastlogintime` datetime NOT NULL COMMENT '最后登录时间',
  `logintag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '登录标识',
  `flag` smallint(5) unsigned DEFAULT '0' COMMENT '用户标识',
  `load_balance` smallint(5) unsigned DEFAULT '0' COMMENT '负载均衡标记',
  `conf_server_ip` varchar(128) DEFAULT NULL COMMENT '指定会议服务器IP',
  `proxytype` tinyint(1) DEFAULT '0' COMMENT '代理类型',
  `proxyaddr` varchar(32) DEFAULT NULL COMMENT '代理地址',
  `proxyport` varchar(32) DEFAULT NULL COMMENT '代理端口',
  `proxyuser` varchar(128) DEFAULT NULL COMMENT '代理登录用户名',
  `proxypass` varchar(128) DEFAULT NULL COMMENT '代理登录密码',
  `departid` bigint(20)DEFAULT NULL COMMENT '部门ID',
  `orgid` bigint(20) DEFAULT NULL COMMENT '企业ID',
  `phonetype` smallint(5) unsigned DEFAULT '0' COMMENT '电话会议类型',
  `orders` int(11) default NULL COMMENT '排序',
  `sys` int(11) default NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_orgid_unique` (`user_name`,`orgid`),
  KEY `orgid` (`orgid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- ----部门信息表----
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL auto_increment,
  `departnum` int(11) default NULL,
  `departpnum` int(11) default NULL,
  `departname` varchar(255) default NULL,
  `orders` int(11) default NULL,
  `orgid` int(11) default NULL,
  `sys` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `departnum` (`departnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
