/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.28 : Database - personal_bill
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`personal_bill` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;

USE `personal_bill`;

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '账单编号',
    `order_id`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '账单订单号(微信支付宝)',
    `type`        int                                                           NOT NULL COMMENT '账单类型(0支出1收入)',
    `method`      varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '账单支付方式',
    `amount`      double                                                        NOT NULL COMMENT '账单金额',
    `date`        datetime                                                      NOT NULL COMMENT '账单时间',
    `category_id` bigint                                                        NOT NULL DEFAULT '1' COMMENT '账单分类',
    `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账单备注',
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '逻辑删除(0正常1删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 164
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

/*Data for the table `bill` */
/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '分类编号',
    `name`        varchar(30)  NOT NULL COMMENT '分类名称',
    `description` varchar(200) NOT NULL COMMENT '分类描述',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1)   NOT NULL DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

/*Data for the table `category` */

insert into `category`(`id`, `name`, `description`, `create_time`, `update_time`, `is_deleted`)
values (1, '未分类', '未分类', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
       (2, '餐饮', '三餐、饮品', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
       (3, '交通', '地铁、公交、滴滴打车', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
       (4, '住房', '房租、水电', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
       (5, '购物', '非日常生活必需品', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
       (6, '日常', '日用品、消耗品，包括网上购买的衣服、鞋子等生活用品', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
       (7, '医疗', '医疗', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
       (8, '娱乐', '娱乐场所消费、旅游等', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
