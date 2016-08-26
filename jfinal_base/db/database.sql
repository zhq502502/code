SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- ¸½¼þ±í
-- ----------------------------
-- ----------------------------
-- Table structure for `tb1`
-- ----------------------------
DROP TABLE IF EXISTS `tb1`;
CREATE TABLE `tb1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

