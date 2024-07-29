/* @formatter:off */
SET NAMES utf8mb4;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`, `username`, `password`, `addr`)
VALUES (41, '老王', '123456', '北京'),
       (42, '小二王', '123456', '北京金燕龙'),
       (43, '小二王', '123456', '北京金燕龙'),
       (45, '王大锤', '123456', '北京金燕龙'),
       (46, '老王', '123456', '北京');

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`
(
    `customer_id`   int(11) NOT NULL auto_increment COMMENT '主键客户ID',
    `customer_name` varchar(32)  NOT NULL DEFAULT '' COMMENT '客户名称',
    PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `customer`(`customer_id`, `customer_name`)
VALUES (10, 'jack'),
       (11, 'jon'),
       (12, 'julia');

DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `userid`   int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
    `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
    `price`    int(11) NOT NULL DEFAULT '0' COMMENT '价格 单位分',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `order_info` (`id`, `userid`, `username`, `price`)
VALUES (1, 21, 'zhangsan', 5000),
       (2, 22, 'lisi', 4000);

