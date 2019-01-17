/*
Navicat MySQL Data Transfer

Source Server         : tyshawn
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : tyshawn_test

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2019-01-17 16:58:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'tyshawn', '21');
INSERT INTO `user` VALUES ('2', '123', '321');
INSERT INTO `user` VALUES ('3', '999', '999');
SET FOREIGN_KEY_CHECKS=1;
