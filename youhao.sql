/*
Navicat MySQL Data Transfer

Source Server         : youhao
Source Server Version : 50710
Source Host           : 127.0.0.1:3306
Source Database       : youhao

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-05-08 17:26:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `age` int(10) NOT NULL,
  `pass` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'youhao', '28', '');
INSERT INTO `user` VALUES ('2', '李艳玲', '28', '');
INSERT INTO `user` VALUES ('7', 'Achilles', '14', '');
INSERT INTO `user` VALUES ('8', 'Achilles', '14', '');
INSERT INTO `user` VALUES ('9', 'Bean', '7', '');
