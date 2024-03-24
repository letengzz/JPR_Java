/*
 Navicat Premium Data Transfer

 Source Server         : Mydb
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : mybatis-example

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 29/08/2023 16:09:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `mybatis-example`;

USE `mybatis-example`;
-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `dept_id` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `dept_city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, 'ACCOUNTING', 'NEW YORK');
INSERT INTO `t_dept` VALUES (2, 'RESEARCH', 'DALLAS');
INSERT INTO `t_dept` VALUES (3, 'SALES', 'CHICAGO');
INSERT INTO `t_dept` VALUES (4, 'OPERATIONS', 'BOSTON');

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp`  (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `emp_name` char(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `emp_salary` double(10, 5) NULL DEFAULT NULL,
  `dept_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `dept_id`(`dept_id` ASC) USING BTREE,
  CONSTRAINT `t_emp_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES (1, 'SMITH', 100.10000, 1);
INSERT INTO `t_emp` VALUES (2, 'WARD', 200.10000, 1);
INSERT INTO `t_emp` VALUES (3, 'JONES', 122.10000, 3);

SET FOREIGN_KEY_CHECKS = 1;
