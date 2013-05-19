/*
 Navicat Premium Data Transfer

 Source Server         : mysql_127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50611
 Source Host           : localhost
 Source Database       : studentstsinghua

 Target Server Type    : MySQL
 Target Server Version : 50611
 File Encoding         : utf-8

 Date: 05/17/2013 14:11:04 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `TShow`
-- ----------------------------
DROP TABLE IF EXISTS `TShow`;
CREATE TABLE `TShow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `showId` int(11) DEFAULT NULL,
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `location` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `date` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `time` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `team` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `price` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `ticketstate` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `host` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `desc` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `TShow`
-- ----------------------------
BEGIN;
INSERT INTO `TShow` VALUES ('1', '234', 'hello', 'location', 'date', 'time', 'team', 'price', 'ticket', 'host', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
