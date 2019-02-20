# Host: 127.0.0.1  (Version 5.7.24)
# Date: 2019-02-20 15:08:57
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "db_file"
#

DROP TABLE IF EXISTS `db_file`;
CREATE TABLE `db_file` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `upload_time` date DEFAULT NULL,
  `is_public` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_file"
#

INSERT INTO `db_file` VALUES (1,1,'11','111','2019-01-17',0);

#
# Structure for table "db_notice"
#

DROP TABLE IF EXISTS `db_notice`;
CREATE TABLE `db_notice` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_notice"
#


#
# Structure for table "db_order"
#

DROP TABLE IF EXISTS `db_order`;
CREATE TABLE `db_order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `requirement` varchar(255) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `status` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_order"
#


#
# Structure for table "db_shop"
#

DROP TABLE IF EXISTS `db_shop`;
CREATE TABLE `db_shop` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `region` varchar(25) DEFAULT NULL,
  `room_number` varchar(3) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `content` varchar(1255) DEFAULT NULL,
  `open_up` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_shop"
#

INSERT INTO `db_shop` VALUES (1,'我的打印铺','东29','212','便宜 实惠 可彩印 接毕业装订','这里是介绍但我实在不知道该写点啥于是就有了这段话 嗯',1),(2,'迷之打印','西11','223','便宜 可配送','这里是介绍但我实在不知道该写点啥于是就有了这段话 嗯',0),(3,NULL,NULL,NULL,NULL,NULL,1),(4,NULL,NULL,NULL,NULL,NULL,1),(5,NULL,NULL,NULL,NULL,NULL,1),(6,NULL,NULL,NULL,NULL,NULL,1),(7,NULL,NULL,NULL,NULL,NULL,1),(8,NULL,NULL,NULL,NULL,NULL,1),(9,NULL,NULL,NULL,NULL,NULL,1),(10,NULL,NULL,NULL,NULL,NULL,1);

#
# Structure for table "db_tag"
#

DROP TABLE IF EXISTS `db_tag`;
CREATE TABLE `db_tag` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL DEFAULT '',
  `user_name` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `used_count` int(11) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_tag"
#

INSERT INTO `db_tag` VALUES (1,'123','123',5,1,'2019-02-14');

#
# Structure for table "db_time"
#

DROP TABLE IF EXISTS `db_time`;
CREATE TABLE `db_time` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_time"
#


#
# Structure for table "db_user"
#

DROP TABLE IF EXISTS `db_user`;
CREATE TABLE `db_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `head_pic` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `tel_num` varchar(25) DEFAULT NULL,
  `open_shop` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_user"
#

INSERT INTO `db_user` VALUES (1,'admin','admin','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(2,'skkkr','123456','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(3,'skkkrs','123456','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(4,'ThreadLocal','123456dsfadf','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(5,'123','123','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(6,'233','233','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(7,'12412','214151','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(8,'1234','123','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0);
