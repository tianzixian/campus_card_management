/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.34-log : Database - campus_card_management
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`campus_card_management` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `campus_card_management`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` varchar(20) NOT NULL COMMENT '卡号',
  `account_balance` int(10) DEFAULT NULL COMMENT '账户余额',
  `consumption_today` int(10) DEFAULT NULL COMMENT '今日消费',
  `last_recharge_amount` int(10) DEFAULT NULL COMMENT '末次充值金额',
  `last_recharge_time` date DEFAULT NULL COMMENT '末次充值时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`id`,`account_balance`,`consumption_today`,`last_recharge_amount`,`last_recharge_time`) values 
('1905110120',0,0,0,NULL),
('1905110121',400,50,100,'2021-01-06'),
('1905110122',8900,200,100,'2021-12-22'),
('1905110123',5000,1000,1000,'2021-11-10'),
('1905110124',1000,100,200,'2010-10-07'),
('1905110125',0,0,0,NULL),
('1905110126',0,0,0,NULL),
('1905110127',0,0,0,NULL),
('1905110128',0,0,0,NULL),
('1905110129',0,0,0,NULL),
('1905110130',0,0,0,NULL),
('1905110131',0,0,0,NULL),
('1905110132',0,0,0,NULL),
('1905110133',0,0,0,NULL),
('1905110134',0,0,0,NULL),
('22211020312',0,0,0,NULL),
('22211020313',0,0,0,NULL),
('22211020314',0,0,0,NULL),
('22211020315',0,0,0,NULL),
('22211020316',0,0,0,NULL);

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` varchar(20) NOT NULL COMMENT '管理员id',
  `password` varchar(25) NOT NULL COMMENT '管理员密码',
  `name` varchar(20) DEFAULT NULL COMMENT '管理员名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

insert  into `administrator`(`id`,`password`,`name`) values 
('admin','123456','admin1');

/*Table structure for table `bank_card` */

DROP TABLE IF EXISTS `bank_card`;

CREATE TABLE `bank_card` (
  `cid` varchar(20) NOT NULL COMMENT '银行卡卡号',
  `cpassword` varchar(20) NOT NULL COMMENT '银行卡密码',
  `balance` int(10) DEFAULT NULL COMMENT '银行卡余额',
  `description` varchar(15) DEFAULT NULL COMMENT '银行卡描述',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bank_card` */

insert  into `bank_card`(`cid`,`cpassword`,`balance`,`description`) values 
('555555','123456',4988,'建设银行'),
('666666','123456',6700,'农业银行'),
('777777','123456',3900,'招商银行'),
('888888','123456',4200,'建设银行');

/*Table structure for table `student_bank_card` */

DROP TABLE IF EXISTS `student_bank_card`;

CREATE TABLE `student_bank_card` (
  `sid` varchar(20) NOT NULL COMMENT '学生id',
  `cid` varchar(20) NOT NULL COMMENT '银行卡id（外键）',
  PRIMARY KEY (`sid`,`cid`),
  KEY `cid` (`cid`),
  CONSTRAINT `student_bank_card_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `bank_card` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_bank_card` */

insert  into `student_bank_card`(`sid`,`cid`) values 
('1905110120','555555'),
('1905110122','666666'),
('1905110121','777777'),
('1905110122','888888');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL COMMENT '学工号',
  `password` varchar(25) NOT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `major` varchar(20) DEFAULT NULL COMMENT '学院',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `sex` enum('男','女','保密') DEFAULT NULL COMMENT '性别',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `telephone` varchar(11) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`password`,`name`,`major`,`age`,`sex`,`email`,`birthday`,`telephone`) values 
('1905110120','123456','张三','软件工程',20,'男','zd0746263yuem@163.com','2002-06-12','13714457881'),
('1905110121','123456','李四','自动化',19,'男','y53677311luboxio@163.com','2002-02-13','13813457291'),
('1905110122','123456','丁韦','软件工程',20,'男','997717421@qq.com','2002-02-04','15971248510'),
('1905110123','123456','王五','大数据',21,'男','hr95214827liaodao@163.com','2002-06-12','15813457291'),
('1905110124','123456','赵六','自动化',20,'女','zhangxindeailian1@163.com','2002-06-12','15813457291'),
('1905110125','123456','孙七','软件工程',19,'男','sojc9545341shansi@163.com','2002-06-12','15813457291'),
('1905110126','123456','周八','软件工程',19,'女','12121211@qq.com','2002-06-12','15813457291'),
('1905110127','123456','朱浩','数字媒体与技术',20,'男','yingjun@1236','2001-02-28','13293457292'),
('1905110128','123456','両儀夏','数字媒体与技术',20,'女','123@qq.com','2001-01-18','15947764629'),
('1905110129','123456','杜鹏','数字媒体与技术',21,'女','112233@qq.com','2000-06-21','15947766628'),
('1905110130','123456','张志远','数字媒体与技术',21,'男','99231984hda@123.com','2000-09-26','13293457291'),
('1905110131','123456','杜元坤','自动化',22,'男','dasdasdasd@qq.com','1999-11-26','13893457252'),
('1905110132','123456','赵昊芗','自动化',21,'男','123@qq.com','2000-05-16','13847712628'),
('1905110133','123456','夏夏','数字媒体与技术',0,'女','123@qq.com','1996-05-21','15947766628'),
('1905110134','123456','明明','网络工程',20,'男','123@qq.com','2001-01-24','15947766628'),
('22211020312','123456','吴九','自动化',18,'女','34433434@qq.com','2002-06-12','15813457291'),
('22211020313','123456','郑十','大数据',19,'男','4334444@qq.com','2002-06-12','13213457291'),
('22211020314','123456','柳勇','软件工程',21,'男','56566565@qq.com','2002-06-12','15813457291'),
('22211020315','123456','黄精望','软件工程',20,'保密','lw38373238dangji@163.com','2002-06-12','15813457291'),
('22211020316','123456','孙浙','软件工程',22,'男','oko7190962ta@163.com','2002-06-12','15813457291');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
