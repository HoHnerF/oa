/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : oa

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-10-09 22:30:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for claim_voucher
-- ----------------------------
DROP TABLE IF EXISTS `claim_voucher`;
CREATE TABLE `claim_voucher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(100) DEFAULT NULL,
  `create_sn` char(5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `next_deal_sn` char(5) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_2` (`next_deal_sn`),
  KEY `FK_Reference_3` (`create_sn`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`next_deal_sn`) REFERENCES `employee` (`sn`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`create_sn`) REFERENCES `employee` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of claim_voucher
-- ----------------------------
INSERT INTO `claim_voucher` VALUES ('5', '出差', '1532', '2019-05-21 18:58:37', null, '2000', '已打款');
INSERT INTO `claim_voucher` VALUES ('6', '外出', '1532', '2019-05-21 19:49:30', '10002', '323', '已审核');
INSERT INTO `claim_voucher` VALUES ('7', 'gh', '1532', '2019-05-22 00:21:38', '10002', '45', '已审核');
INSERT INTO `claim_voucher` VALUES ('8', '二位', '1532', '2019-05-22 10:47:55', '10002', '20000', '已审核');
INSERT INTO `claim_voucher` VALUES ('9', 'fds', '1532', '2019-05-22 18:52:28', '10003', '12322', '已提交');
INSERT INTO `claim_voucher` VALUES ('10', 'fds', '1532', '2019-05-22 18:52:33', '10003', '12322', '已提交');
INSERT INTO `claim_voucher` VALUES ('20', '生病', '1532', '2019-05-24 17:11:45', '1532', '123123', '新创建');

-- ----------------------------
-- Table structure for claim_voucher_item
-- ----------------------------
DROP TABLE IF EXISTS `claim_voucher_item`;
CREATE TABLE `claim_voucher_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `claim_voucher_id` int(11) DEFAULT NULL,
  `item` varchar(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_4` (`claim_voucher_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`claim_voucher_id`) REFERENCES `claim_voucher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of claim_voucher_item
-- ----------------------------
INSERT INTO `claim_voucher_item` VALUES ('6', '5', '交通', '1000', '俩次');
INSERT INTO `claim_voucher_item` VALUES ('7', '5', '餐饮', '1000', '俩次');
INSERT INTO `claim_voucher_item` VALUES ('8', '6', '住宿', '323', '放到');
INSERT INTO `claim_voucher_item` VALUES ('9', '7', '餐饮', '45', 'fg');
INSERT INTO `claim_voucher_item` VALUES ('22', '20', '交通', '123123', '中国');

-- ----------------------------
-- Table structure for deal_record
-- ----------------------------
DROP TABLE IF EXISTS `deal_record`;
CREATE TABLE `deal_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `claim_voucher_id` int(11) DEFAULT NULL,
  `deal_sn` char(5) DEFAULT NULL,
  `deal_time` datetime DEFAULT NULL,
  `deal_way` varchar(20) DEFAULT NULL,
  `deal_result` varchar(20) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`claim_voucher_id`),
  KEY `FK_Reference_6` (`deal_sn`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`claim_voucher_id`) REFERENCES `claim_voucher` (`id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`deal_sn`) REFERENCES `employee` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deal_record
-- ----------------------------
INSERT INTO `deal_record` VALUES ('8', '5', '1532', '2019-05-21 18:59:00', '提交', '已提交', '无');
INSERT INTO `deal_record` VALUES ('9', '5', '10003', '2019-05-21 19:00:01', '通过', '已审核', '收到');
INSERT INTO `deal_record` VALUES ('10', '5', '10002', '2019-05-21 19:41:03', '打款', '已打款', '请查收');
INSERT INTO `deal_record` VALUES ('11', '6', '1532', '2019-05-21 19:49:47', '提交', '已提交', '无');
INSERT INTO `deal_record` VALUES ('12', '7', '1532', '2019-05-22 17:11:38', '提交', '已提交', '无');
INSERT INTO `deal_record` VALUES ('13', '7', '10003', '2019-05-22 17:42:48', '通过', '已审核', '');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `sn` char(5) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('10001', '总经理办公室', '星星大厦E座1201');
INSERT INTO `department` VALUES ('10002', '财务部', '星星大厦E座1202');
INSERT INTO `department` VALUES ('10003', '事业部', '星星大厦E座1101');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `sn` char(5) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `department_sn` char(5) DEFAULT NULL,
  `post` varchar(20) DEFAULT NULL,
  `eimg` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_Reference_1` (`department_sn`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`department_sn`) REFERENCES `department` (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('00089', '000000', 'fujf', '10001', '员工', null);
INSERT INTO `employee` VALUES ('00090', '000000', 'fd', '10002', '员工', null);
INSERT INTO `employee` VALUES ('10001', '123', '刘备', '10001', '总经理', null);
INSERT INTO `employee` VALUES ('10002', '000000', '孙尚香', '10002', '财务', null);
INSERT INTO `employee` VALUES ('10003', '000000', '关羽', '10003', '部门经理', null);
INSERT INTO `employee` VALUES ('1213', '000000', 'tret', '10003', '员工', null);
INSERT INTO `employee` VALUES ('1532', '000000', 'ge', '10003', '员工', null);
INSERT INTO `employee` VALUES ('4324', '000000', 'rd', '10001', '员工', null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pimage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('12', 'b61fd60b-ee04-4038-8e11-610a1f5cad181.jpg');
INSERT INTO `product` VALUES ('13', '5dd5b185-3e05-4dd8-9b43-01a2957e5f9a2.jpg');
INSERT INTO `product` VALUES ('14', '3af46675-b68e-4137-9383-5ed9b0919e493.jpg');
INSERT INTO `product` VALUES ('15', 'f9e50e62-4cb4-4918-85c6-bb82a667a8a53.jpg');
INSERT INTO `product` VALUES ('16', '67a5b2e7-23d3-4230-b589-7dc594eaeaa92.jpg');
INSERT INTO `product` VALUES ('17', 'c09bbff3-11e8-4b79-b7eb-8e607c99d3883.jpg');
INSERT INTO `product` VALUES ('18', 'a9f2fb10-2d48-4b3b-a655-5223d08854da1.jpg');
INSERT INTO `product` VALUES ('19', '852bc7cb-fa6b-40f3-9c15-8a060d9749c01.jpg');
INSERT INTO `product` VALUES ('20', '8a33329e-5669-483b-bd59-f35afe2f38b63.jpg');
