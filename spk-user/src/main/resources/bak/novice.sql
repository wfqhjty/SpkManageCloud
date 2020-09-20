/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : novice

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-08-12 21:07:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for framedept
-- ----------------------------
DROP TABLE IF EXISTS `framedept`;
CREATE TABLE `framedept` (
  `deptid` int(11) NOT NULL AUTO_INCREMENT,
  `deptname` varchar(255) DEFAULT NULL,
  `deptshortname` varchar(255) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`deptid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of framedept
-- ----------------------------
INSERT INTO `framedept` VALUES ('1', '人事部', 'person', null);
INSERT INTO `framedept` VALUES ('2', '技术部', 'technical', null);
INSERT INTO `framedept` VALUES ('3', '财务部', 'finance ', null);
INSERT INTO `framedept` VALUES ('4', '行政部', 'administration', null);
INSERT INTO `framedept` VALUES ('5', '人事一部', 'person-1', '1');
INSERT INTO `framedept` VALUES ('6', '人事一部', 'person-2', '1');
INSERT INTO `framedept` VALUES ('7', '技术一部', 'technical-1', '2');
INSERT INTO `framedept` VALUES ('8', '技术二部', 'technical-2', '2');
INSERT INTO `framedept` VALUES ('9', '技术三部', 'technical-3', '2');

-- ----------------------------
-- Table structure for frameuser
-- ----------------------------
DROP TABLE IF EXISTS `frameuser`;
CREATE TABLE `frameuser` (
  `userid` int(50) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `deptname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frameuser
-- ----------------------------
INSERT INTO `frameuser` VALUES ('2', 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-05 00:05:00', '15678789898', '5', 'persondept');
INSERT INTO `frameuser` VALUES ('3', '17625801536', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-30 16:37:58', '17625801536', '5', 'persondept');
INSERT INTO `frameuser` VALUES ('9', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-31 12:27:59', '17625801536', '6', 'persondept');
INSERT INTO `frameuser` VALUES ('10', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-03 06:56:21', '17625801536', '6', 'devolopdept');
INSERT INTO `frameuser` VALUES ('11', 'zhangwang', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-06 17:30:04', '17625801536', '7', 'devolopdept');
INSERT INTO `frameuser` VALUES ('12', 'songwang', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-06 17:33:41', '17625801536', '7', 'devolopdept');
INSERT INTO `frameuser` VALUES ('13', 'jiangling', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-09 11:51:00', '17625801536', '8', 'managerdept');
INSERT INTO `frameuser` VALUES ('14', 'jiangli', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-09 11:53:19', '17625801536', '9', 'managerdept');
