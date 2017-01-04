
-- 需要 MySQL 5.6.5以上的版本
CREATE DATABASE sagiller;
USE sagiller;

-- 设备表
CREATE TABLE `_device` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`serial_number` varchar(50) DEFAULT NULL COMMENT '设备序列号',
	`ip` varchar(30) DEFAULT NULL COMMENT 'iP地址',
	`app_version` varchar(30) DEFAULT NULL COMMENT 'app 版本',
	`vendor` varchar(30) DEFAULT NULL COMMENT '设备供应商',
	`model` varchar(30) DEFAULT NULL COMMENT '设备型号',
	`os_type` int(10) DEFAULT NULL COMMENT '设备系统类型，1：ios，2：android',
	`os_version` varchar(30) DEFAULT NULL COMMENT '设备系统版本',
	`address` varchar(100) DEFAULT NULL COMMENT 'IP地址解析出来的地址',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '设备表';

CREATE TABLE `_launch` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `serial_number` varchar(50) DEFAULT NULL COMMENT '设备序列号',
  `launch_time` timestamp NULL DEFAULT NULL COMMENT '启动时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `_image_loading` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`serial_number` varchar(50) DEFAULT NULL COMMENT '设备序列号',
	`loading_max` float DEFAULT NULL COMMENT '加载图片最大耗时',
	`loading_min` float DEFAULT NULL COMMENT '加载图片最小耗时',
	`loading_average` float DEFAULT NULL COMMENT '加载图片平均耗时',
	`loading_times` int(11) DEFAULT NULL COMMENT '加载次数',
	`draw_max` float DEFAULT NULL COMMENT '绘制图片最大耗时',
	`draw_min` float DEFAULT NULL COMMENT '绘制图片最小耗时',
	`draw_average` float DEFAULT NULL COMMENT '绘制图片平均耗时',
	`draw_times` int(11) DEFAULT NULL COMMENT '绘制次数',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '图片加载时间表';

-- 用户表
CREATE TABLE _user(
`user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
`user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
`user_phone` BIGINT NOT NULL COMMENT '手机号',
`score` INT NOT NULL COMMENT '积分',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`user_id`),
KEY `idx_user_phone`(`user_phone`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 商品表
CREATE TABLE _goods(
`goods_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID',
`title` VARCHAR(120) NOT NULL COMMENT '商品名称',
`state` INT NOT NULL COMMENT '商品状态',
`price` FLOAT NOT NULL COMMENT '商品价格',
`number` INT NOT NULL COMMENT '商品数量',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`goods_id`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 订单表
CREATE TABLE _order(
`order_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
`user_id` BIGINT NOT NULL  COMMENT '用户ID',
`goods_id` BIGINT NOT NULL  COMMENT '商品ID',
`title` VARCHAR(120) NOT NULL COMMENT '订单名称',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`order_id`),
KEY `idx_user_id`(`user_id`),
KEY `idx_goods_id`(`goods_id`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='订单表';


-- 插入初始数据
INSERT INTO 
	_user(user_name, user_phone, score)
VALUES
	('阿坚', 18768128888, 0),
	('小明', 18968129999, 0);


INSERT INTO 
	_goods(title, state, price,number)
VALUES
	('iphone7', 1, 3999, 100),
	('ipad3', 1, 1999, 2000);

INSERT INTO
  _device(serial_number, ip, app_version,vendor,model,os_type,os_version)
VALUES
  ('iej92jijije92939djiwxna', '51.221.50.83', '1.0.1', 'sunsung','Note7',2,'6.0.1'),
  ('wds92jejije92939djiwxew', '121.40.208.198', '1.0.2', 'huawei','P9',2,'7.0.0'),
  ('dfweji932jif8u823nijfu8', '120.40.208.33', '1.0.0', 'apple','iphone6s',1,'7.0.0');

INSERT INTO `_image_loading` (`id`, `serial_number`, `max`, `min`, `average`, `times`)
VALUES
	(1, 'dfefwe92jlijf93232', 15.3, 4.1, 6.7, 28),
	(2, 'dfefwe92jlijf93230', 13, 1.2, 3.1, 14),
	(3, 'dfefwe92jlijf93130', 12, 2.5, 1.2, 7),
	(4, 'iej92jijije92939djiwxna', 9.9, 3.4, 3.6, 29),
	(5, 'wds92jejije92939djiwxew', 9.1, 2.8, 6.6, 78),
	(6, 'dfweji932jif8u823nijfu81', 12, 4.6, 8.7, 72);
