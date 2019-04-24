/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2019-04-22 15:32:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `grades`
-- ----------------------------
DROP TABLE IF EXISTS `grades`;
CREATE TABLE `grades` (
  `sid` int(10) NOT NULL,
  `math` int(5) NOT NULL,
  `chinese` int(5) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grades
-- ----------------------------
INSERT INTO `grades` VALUES ('1', '89', '89');
INSERT INTO `grades` VALUES ('2', '99', '79');
INSERT INTO `grades` VALUES ('3', '78', '56');
INSERT INTO `grades` VALUES ('4', '67', '97');
INSERT INTO `grades` VALUES ('5', '86', '68');
INSERT INTO `grades` VALUES ('6', '57', '56');

-- ----------------------------
-- Table structure for `students`
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `sex` char(1) NOT NULL,
  `birthday` varchar(10) NOT NULL,
  `class` varchar(45) NOT NULL,
  `academy` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('2', '李二', '女', '0709', '三班', '计算机', '001');
INSERT INTO `students` VALUES ('3', '王三', '男', '0304', '三班', '软件', '002');
INSERT INTO `students` VALUES ('4', '李四', '女', '0307', '二班', '软件', '003');
INSERT INTO `students` VALUES ('5', '叶五', '男', '1109', '三班', '软件', '004');
INSERT INTO `students` VALUES ('6', '王六', '女', '0605', '一班', '软件', '005');
INSERT INTO `students` VALUES ('8', '等待', '女', '0508', '一', '软件', '8');
