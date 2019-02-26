# Host: 127.0.0.1  (Version 5.7.24)
# Date: 2019-02-26 18:37:54
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
  `status` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_order"
#

INSERT INTO `db_order` VALUES (1,1,5,'open.jpg close.jpg','http://up-z2.qiniup.com/5201902251771164.jpg http://up-z2.qiniup.com/5201902251619453.jpg ','44564561456','2019-02-25 13:52:50','11',0),(2,1,5,'open.jpg close.jpg','http://up-z2.qiniup.com/5201902251771164.jpg http://up-z2.qiniup.com/5201902251619453.jpg ','145615','2019-02-25 16:41:16','11',0),(3,1,5,'open.jpg close.jpg ','http://up-z2.qiniup.com/5201902251771164.jpg http://up-z2.qiniup.com/5201902251619453.jpg ','44','2019-02-25 16:55:09','自提444',0);

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

INSERT INTO `db_shop` VALUES (1,'我的打印铺','东29','212','便宜 实惠 可彩印 接毕业装订','这里是介绍但我实在不知道该写点啥于是就有了这段话 嗯',1),(2,'迷之打印','西11','223','便宜 可配送','这里是介绍但我实在不知道该写点啥于是就有了这段话 嗯',0),(5,'哇卡卡卡','西11','123','便宜 实惠 可彩印 接毕业装订','对对对',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_tag"
#

INSERT INTO `db_tag` VALUES (1,'可单面','123',5,1,'2019-02-14'),(2,'可彩印','123',5,3,'2019-02-14'),(3,'可双面','123',5,0,'2019-02-21'),(4,'可胶装','123',5,0,'2019-02-21'),(5,'送货上门','123',5,0,'2019-02-21'),(6,'可复印','123',5,0,'2019-02-21'),(7,'可黑白','123',5,0,'2019-02-21'),(8,'可优惠','123',5,0,'2019-02-21');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

#
# Data for table "db_user"
#

INSERT INTO `db_user` VALUES (1,'admin','admin','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',1),(2,'skkkr','123456','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',1),(3,'skkkrs','123456','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(4,'ThreadLocal','123456dsfadf','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(5,'123','123','','女','http://up-z2.qiniup.com/52019022617711129.jpg','12345556','15622703794',1),(6,'233','233','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(7,'e','214151','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(8,'1234','123','123345','男','https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg','wechatnum','15393740054',0),(9,'kk','123','','女','https://pic.heartqiu.cn/920190226302511.png','ffg','1234556',0);
