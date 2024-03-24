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

 Date: 29/08/2023 11:15:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `mybatis-example`;

USE `mybatis-example`;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '刘备', '456123', 23);
INSERT INTO `user` VALUES (2, '关羽', '123456', 20);
INSERT INTO `user` VALUES (3, '张飞', '124356', 19);
INSERT INTO `user` VALUES (4, '司马光', '494161', 23);
INSERT INTO `user` VALUES (5, '李白', '444444', 28);
INSERT INTO `user` VALUES (6, '杜甫', '124356', 70);

SET FOREIGN_KEY_CHECKS = 1;
