use jay_two_slave12;
drop table if exists `user`;
create table `user`
(
    `id`       int(11)      NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
insert into `user`(`id`, `username`, `password`, `addr`)
values (41, '老王', '123456', 'jay_two_slave12'),
       (42, '小二王', '123456', '北京金燕龙'),
       (43, '小二王', '123456', '北京金燕龙'),
       (45, '王大锤', '123456', '北京金燕龙'),
       (46, '老王', '123456', '北京');

drop table if exists `user_1`;
create table `user_1`
(
    `id`       int(11)      NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
insert into `user_1`(`id`, `username`, `password`, `addr`)
values (41, '老王', '123456', 'jay_two_slave12.user_1'),
       (42, '小二王', '123456', '北京金燕龙'),
       (43, '小二王', '123456', '北京金燕龙'),
       (45, '王大锤', '123456', '北京金燕龙'),
       (46, '老王', '123456', '北京');
drop table if exists `user_2`;
create table `user_2`
(
    `id`       int(11)      NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
insert into `user_2`(`id`, `username`, `password`, `addr`)
values (41, '老王', '123456', 'jay_two_slave12.user_2'),
       (42, '小二王', '123456', '北京金燕龙'),
       (43, '小二王', '123456', '北京金燕龙'),
       (45, '王大锤', '123456', '北京金燕龙'),
       (46, '老王', '123456', '北京');
drop table if exists `user_3`;
create table `user_3`
(
    `id`       int(11)      NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
insert into `user_3`(`id`, `username`, `password`, `addr`)
values (41, '老王', '123456', 'jay_two_slave12.user_3'),
       (42, '小二王', '123456', '北京金燕龙'),
       (43, '小二王', '123456', '北京金燕龙'),
       (45, '王大锤', '123456', '北京金燕龙'),
       (46, '老王', '123456', '北京');
drop table if exists `user_4`;
create table `user_4`
(
    `id`       int(11)      NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
insert into `user_4`(`id`, `username`, `password`, `addr`)
values (41, '老王', '123456', 'jay_two_slave12.user_4'),
       (42, '小二王', '123456', '北京金燕龙'),
       (43, '小二王', '123456', '北京金燕龙'),
       (45, '王大锤', '123456', '北京金燕龙'),
       (46, '老王', '123456', '北京');
