use jay_two_slave12;
drop table if exists `user`;
create table `user`
(
    `id`       int(11) NOT NULL auto_increment COMMENT '主键',
    `username` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名称',
    `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `addr`     varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `user`(`id`, `username`, `password`, `addr`) values (41, '老王', '123456', 'jay_two_slave12'),(42, '小二王', '123456', '北京金燕龙'),(43, '小二王', '123456', '北京金燕龙'),(45, '王大锤', '123456', '北京金燕龙'),(46, '老王', '123456', '北京');
