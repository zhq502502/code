/*
MySQL Data Transfer
Source Host: localhost
Source Database: task
Target Host: localhost
Target Database: task
Date: 2013/5/27 0:19:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for task_host
-- ----------------------------
DROP TABLE IF EXISTS `task_host`;
CREATE TABLE `task_host` (
  `id` int(11) NOT NULL auto_increment,
  `host` varchar(200) default NULL,
  `type` varchar(10) default NULL,
  `port` int(10) default NULL,
  `describe` varchar(200) default NULL,
  `runtime` date default NULL,
  `runcycle` varchar(10) default NULL,
  `runtype` varchar(10) default NULL,
  `flag` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task_url
-- ----------------------------
DROP TABLE IF EXISTS `task_url`;
CREATE TABLE `task_url` (
  `id` int(10) NOT NULL,
  `host_id` int(10) default NULL,
  `url` varchar(1000) default NULL,
  `method_type` varchar(50) default NULL,
  `sorting` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task_user
-- ----------------------------
DROP TABLE IF EXISTS `task_user`;
CREATE TABLE `task_user` (
  `id` int(10) NOT NULL auto_increment,
  `loginname_name` varchar(200) default NULL,
  `loginname_value` varchar(200) default NULL,
  `loginpwd_name` varchar(200) default NULL,
  `loginpwd_value` varchar(200) default NULL,
  `host_id` int(10) default NULL,
  `param1_name` varchar(200) default NULL,
  `param1_value` varchar(200) default NULL,
  `param2_name` varchar(200) default NULL,
  `param2_value` varchar(200) default NULL,
  `last_runtime` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `task_host` VALUES ('1', 'www.freedede.com', 'http', '80', null, null, '', 'nowtime', '1');
INSERT INTO `task_url` VALUES ('1', '1', '/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&fastloginfield=username&handlekey=ls&quickforward=yes', 'post', '1');
INSERT INTO `task_url` VALUES ('2', '1', '/plugin.php?id=dsu_amupper&ppersubmit=true&formhash=484287f6&infloat=yes&handlekey=dsu_amupper&inajax=1&ajaxtarget=fwin_content_dsu_amupper', 'get', '2');
INSERT INTO `task_user` VALUES ('1', 'username', 'zhq502502', 'password', '4741118', '1', null, null, null, null, '2013-05-27');
