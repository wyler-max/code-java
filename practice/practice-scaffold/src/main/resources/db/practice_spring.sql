use jay_one_master;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_one_master'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');

use jay_two_master;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_two_master'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');

use jay_two_slave1;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_two_slave1'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');

use jay_two_slave2;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_two_slave2'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');

use jay_three_master1;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_three_master1'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');

use jay_three_master2;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_three_master2'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');


use jay_three_slave11;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_three_slave11'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');


use jay_three_slave12;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_three_slave12'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');


use jay_three_slave21;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_three_slave21'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');


use jay_three_slave22;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_three_slave22'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');
