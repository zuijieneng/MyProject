/*
SQLyog Ultimate v8.32 
MySQL - 5.1.51-community : Database - db_car
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_car` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_car`;

/*Table structure for table `tab_car` */

DROP TABLE IF EXISTS `tab_car`;

CREATE TABLE `tab_car` (
  `car_id` varchar(50) NOT NULL,
  `car_logo` varchar(50) DEFAULT NULL,
  `car_number` char(7) DEFAULT NULL,
  `car_time` datetime DEFAULT NULL,
  `car_zuo` int(11) DEFAULT NULL,
  `car_color` char(1) DEFAULT NULL,
  `car_price` decimal(10,1) DEFAULT NULL,
  `car_photo` varchar(100) DEFAULT NULL,
  `car_status` int(11) DEFAULT '1' COMMENT '车辆状态：1 未租，2 已租,3 不可以',
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_car` */

insert  into `tab_car`(`car_id`,`car_logo`,`car_number`,`car_time`,`car_zuo`,`car_color`,`car_price`,`car_photo`,`car_status`) values ('car_0011','宝来','豫A88888','2021-05-04 00:00:00',4,'百','100000.0','car_0011.jpeg',1),('car_0012','特斯拉','豫A88881','2020-01-21 11:11:11',7,'黑','200000.0','car_0012.jpeg',1),('car_0013','宝马','豫A88882','2020-01-11 11:11:11',6,'白','300000.0','car_0013.jpeg',1),('car_7756396bbd1c48c1b05097500e0cfd1b','五菱宏光','豫A11111','2021-05-04 00:00:00',1,'红','11111.0','car_7756396bbd1c48c1b05097500e0cfd1b.jpeg',1);

/*Table structure for table `tab_car_check` */

DROP TABLE IF EXISTS `tab_car_check`;

CREATE TABLE `tab_car_check` (
  `car_check_id` varchar(50) NOT NULL,
  `car_id` varchar(50) DEFAULT NULL,
  `car_check_time` datetime DEFAULT NULL,
  `car_describe` varchar(1000) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`car_check_id`),
  KEY `fk_17` (`car_id`),
  KEY `fk_16` (`user_id`),
  CONSTRAINT `fk_16` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`user_id`),
  CONSTRAINT `fk_17` FOREIGN KEY (`car_id`) REFERENCES `tab_car` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_car_check` */

insert  into `tab_car_check`(`car_check_id`,`car_id`,`car_check_time`,`car_describe`,`user_id`) values ('car_check_0204f2dddfaf4887bfaae877f95cef9d','car_0012','2021-05-12 09:19:13','111111','user_0011'),('car_check_2bca7991f95f409db185487b401880c5','car_7756396bbd1c48c1b05097500e0cfd1b','2021-05-11 11:23:38','3333','user_0011'),('car_check_e23e138e10f94dd5b4fa31ad6e14739c','car_0011','2021-05-12 11:02:33',NULL,'user_0011');

/*Table structure for table `tab_car_in` */

DROP TABLE IF EXISTS `tab_car_in`;

CREATE TABLE `tab_car_in` (
  `car_in_id` varchar(50) NOT NULL,
  `car_out_id` varchar(50) DEFAULT NULL,
  `car_check_id` varchar(50) DEFAULT NULL,
  `car_in_time` datetime DEFAULT NULL,
  `car_in_oil` float(100,1) DEFAULT NULL COMMENT '油量',
  `car_in_money` decimal(10,1) DEFAULT NULL COMMENT '总租金',
  `car_in_feedback` varchar(400) DEFAULT NULL COMMENT '用车反馈',
  `user_id` varchar(50) DEFAULT NULL,
  `car_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`car_in_id`),
  KEY `fk_18` (`car_out_id`),
  KEY `fk_19` (`car_check_id`),
  KEY `fk_1001` (`user_id`),
  KEY `fk_1010` (`car_id`),
  CONSTRAINT `fk_1010` FOREIGN KEY (`car_id`) REFERENCES `tab_car` (`car_id`),
  CONSTRAINT `fk_1001` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`user_id`),
  CONSTRAINT `fk_18` FOREIGN KEY (`car_out_id`) REFERENCES `tab_car_out` (`car_out_id`),
  CONSTRAINT `fk_19` FOREIGN KEY (`car_check_id`) REFERENCES `tab_car_check` (`car_check_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_car_in` */

insert  into `tab_car_in`(`car_in_id`,`car_out_id`,`car_check_id`,`car_in_time`,`car_in_oil`,`car_in_money`,`car_in_feedback`,`user_id`,`car_id`) values ('car_in_7e0fc588b9884187ae3fd4004d9fc632','car_out_8a76bc295c764852930979c0299b86cf','car_check_0204f2dddfaf4887bfaae877f95cef9d','2021-05-12 13:11:16',111.0,'444.0','1111115555','user_0011','car_0011');

/*Table structure for table `tab_car_out` */

DROP TABLE IF EXISTS `tab_car_out`;

CREATE TABLE `tab_car_out` (
  `car_out_id` varchar(50) NOT NULL,
  `car_id` varchar(50) DEFAULT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  `car_check_id` varchar(50) DEFAULT NULL,
  `car_out_start_time` datetime DEFAULT NULL,
  `car_out_end_time` datetime DEFAULT NULL,
  `car_out_day_price` decimal(6,1) DEFAULT NULL COMMENT '天租金',
  `car_out_deposit` decimal(7,1) DEFAULT NULL COMMENT '押金',
  `car_out_oil` float(100,1) DEFAULT NULL COMMENT '油量',
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`car_out_id`),
  KEY `fk_21` (`car_check_id`),
  KEY `fk_22` (`customer_id`),
  KEY `fk_23` (`car_id`),
  KEY `fk_1000` (`user_id`),
  CONSTRAINT `fk_1000` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`user_id`),
  CONSTRAINT `fk_21` FOREIGN KEY (`car_check_id`) REFERENCES `tab_car_check` (`car_check_id`),
  CONSTRAINT `fk_22` FOREIGN KEY (`customer_id`) REFERENCES `tab_customer` (`customer_id`),
  CONSTRAINT `fk_23` FOREIGN KEY (`car_id`) REFERENCES `tab_car` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_car_out` */

insert  into `tab_car_out`(`car_out_id`,`car_id`,`customer_id`,`car_check_id`,`car_out_start_time`,`car_out_end_time`,`car_out_day_price`,`car_out_deposit`,`car_out_oil`,`user_id`) values ('car_out_3cff94ac1d6e4413b5bd63596374a6f6','car_0011','customer_003','car_check_0204f2dddfaf4887bfaae877f95cef9d','2021-05-12 11:05:49','2021-05-13 00:00:00','222.0','2.0',22.0,'user_0011'),('car_out_6ad92771872c4b4499a40f65c9f7dab9','car_0012','customer_007','car_check_2bca7991f95f409db185487b401880c5','2021-05-12 11:08:22','2021-05-13 00:00:00','11.0','12.0',13.0,'user_0011'),('car_out_8a76bc295c764852930979c0299b86cf','car_0013','customer_004','car_check_2bca7991f95f409db185487b401880c5','2021-05-12 11:39:44','2021-05-06 00:00:00','44.0','44.0',444.0,'user_0011');

/*Table structure for table `tab_customer` */

DROP TABLE IF EXISTS `tab_customer`;

CREATE TABLE `tab_customer` (
  `customer_id` varchar(50) NOT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `customer_sex` char(1) DEFAULT NULL,
  `customer_phone` char(11) DEFAULT NULL,
  `customer_pid` char(18) DEFAULT NULL,
  `customer_job` varchar(18) DEFAULT NULL,
  `customer_address` varchar(100) DEFAULT NULL,
  `customer_photo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_customer` */

insert  into `tab_customer`(`customer_id`,`customer_name`,`customer_sex`,`customer_phone`,`customer_pid`,`customer_job`,`customer_address`,`customer_photo`) values ('customer_003','王五五','男','15036088042','410186198502222223','会计','河南.郑州','customer_003.jpg'),('customer_004','赵六','男','15036088043','410186198502222224','司机','河南.郑州','customer_004.jpg'),('customer_005','田七','男','15036088044','410186198502222225','小贩','河南.郑州','customer_005.jpg'),('customer_006','吴八','男','15036088045','410186198502222226','无业','河南.郑州','customer_006.jpg'),('customer_007','苗九','男','15036088045','410186198502222226','无业','河南.郑州','customer_007.jpg'),('customer_2ec08bb652a14aea9b77fe1f90d834a5','武大郎','男','15036088040','410185198502229999','嘻嘻哈哈','天津.塘沽','customer_2ec08bb652a14aea9b77fe1f90d834a5.jpg'),('customer_2f9a4fee9f5f4c1dae5c4e4eee189440','33333','男','110','3333333','1111','河南省.南阳','customer_2f9a4fee9f5f4c1dae5c4e4eee189440.jpeg');

/*Table structure for table `tab_department` */

DROP TABLE IF EXISTS `tab_department`;

CREATE TABLE `tab_department` (
  `department_id` varchar(50) NOT NULL,
  `department_name` varchar(10) DEFAULT NULL,
  `department_describe` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_department` */

insert  into `tab_department`(`department_id`,`department_name`,`department_describe`) values ('department_001','客户部','客户维护和客户招揽1'),('department_002','车辆部','车辆管理和车辆出入'),('department_003','车检部','车辆检查'),('department_004','董事局','所有事务');

/*Table structure for table `tab_dictionary` */

DROP TABLE IF EXISTS `tab_dictionary`;

CREATE TABLE `tab_dictionary` (
  `dictionary_id` int(11) NOT NULL AUTO_INCREMENT,
  `dictionary_name` varchar(11) DEFAULT NULL,
  `dictionary_type` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dictionary_id`),
  KEY `fk_33` (`province_id`),
  CONSTRAINT `fk_33` FOREIGN KEY (`province_id`) REFERENCES `tab_dictionary` (`dictionary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `tab_dictionary` */

insert  into `tab_dictionary`(`dictionary_id`,`dictionary_name`,`dictionary_type`,`province_id`) values (10,'河南省',1,NULL),(11,'郑州',2,10),(12,'南阳',2,10),(13,'安阳',2,10),(14,'信阳',2,10),(20,'河北省',1,NULL),(21,'石家庄',2,20),(22,'太原',2,20),(23,'邯郸',2,20),(24,'廊坊',2,20),(30,'北京',1,NULL),(31,'丰台',2,30),(32,'海淀',2,30),(33,'朝阳',2,30),(34,'通州',2,30),(40,'云南省',1,NULL),(41,'昆明',2,40),(42,'丽江',2,40),(43,'大理',2,40),(44,'西双版纳',2,40);

/*Table structure for table `tab_role` */

DROP TABLE IF EXISTS `tab_role`;

CREATE TABLE `tab_role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_describe` varchar(200) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_role` */

insert  into `tab_role`(`role_id`,`role_name`,`role_describe`) values ('role_01','总经理','可以执行所有操作'),('role_02','部门经理','可以执行除了系统操作以外的所有操作'),('role_03','部门员工','只能执行当前部门的操作和其他部门的查看操作');

/*Table structure for table `tab_role_url` */

DROP TABLE IF EXISTS `tab_role_url`;

CREATE TABLE `tab_role_url` (
  `role_id` varchar(50) NOT NULL DEFAULT '',
  `url_id` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`url_id`),
  KEY `fk_14` (`url_id`),
  CONSTRAINT `fk_13` FOREIGN KEY (`role_id`) REFERENCES `tab_role` (`role_id`),
  CONSTRAINT `fk_14` FOREIGN KEY (`url_id`) REFERENCES `tab_url` (`url_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_role_url` */

insert  into `tab_role_url`(`role_id`,`url_id`) values ('role_02','url_34597f7ced22424481fe0638426d4c0a'),('role_01','url_4990c3d1c41244c4ac06bea536541f81'),('role_01','url_5073e48130114c4b89b68bac2b749fec'),('role_02','url_5073e48130114c4b89b68bac2b749fec'),('role_02','url_6bf55449482f4a69a28bcd0eb0e7399c'),('role_01','url_6d8c3f6bdef14ebb9d67ade8cf4b86f6'),('role_01','url_7916dbf304774526a2d5461b348bd83a'),('role_01','url_890f9bbafb614e51a50bde87df7f1f9d'),('role_01','url_8a7e0fd8a1934f20b3fa1642085a3637'),('role_01','url_8aad4242efab4392803978818eebcee8'),('role_01','url_9737045f3f0046fba7407271446646d9'),('role_01','url_97a90555ef424575a79391c5ad5a7386'),('role_01','url_b66c5fb87ca348cfac133a68ed07c462'),('role_01','url_e5c72475b3d5492e80220bc4b99fac3d'),('role_01','url_f5623792f14340a18803a5a019f5c03b');

/*Table structure for table `tab_url` */

DROP TABLE IF EXISTS `tab_url`;

CREATE TABLE `tab_url` (
  `url_id` varchar(50) NOT NULL,
  `url_path` varchar(50) DEFAULT NULL,
  `url_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`url_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_url` */

insert  into `tab_url`(`url_id`,`url_path`,`url_name`) values ('url_34597f7ced22424481fe0638426d4c0a','/department/update','部门修改'),('url_4990c3d1c41244c4ac06bea536541f81','/carCheck/delete','车检删除'),('url_5073e48130114c4b89b68bac2b749fec','/carCheck/update','车检修改'),('url_6110e6458eba4e1f91a2f7777a109add','/role/add','角色添加'),('url_6bf55449482f4a69a28bcd0eb0e7399c','/user/delete','用户删除'),('url_6d8c3f6bdef14ebb9d67ade8cf4b86f6','/car/add','车辆添加'),('url_7916dbf304774526a2d5461b348bd83a','/customer/get','客户获取'),('url_890f9bbafb614e51a50bde87df7f1f9d','/user/get','用户获取'),('url_8a7e0fd8a1934f20b3fa1642085a3637','/customer/update','客户修改'),('url_8aad4242efab4392803978818eebcee8','/department/get','部门获取'),('url_9737045f3f0046fba7407271446646d9','/car/update','车辆修改'),('url_97a90555ef424575a79391c5ad5a7386','/url/update','操作修改'),('url_998b5c34a2c94326a88f8ae2b6aaabe8','/url/add','操作添加'),('url_9ea334de6e194961947cc54a638ed901','/url/delete','操作删除'),('url_a4df24fd0a0c4e9b8f5c15a0fa7e8bf0','/user/update','用户修改'),('url_a82a51cb822f4e5ba4347b7a11ad19eb','/role/delete','角色删除'),('url_b66c5fb87ca348cfac133a68ed07c462','/role/get','角色获取'),('url_b86e1f6959a14a9891e231fbf30ae4ec','/car/get','车辆获取'),('url_bc45a416d9544233be86027a49f462e1','/department/delete','部门删除'),('url_cbc623c5bd96445280dbbb6977dc7a86','/role/update','接受修改'),('url_d1736235bbf14ff48df7494a53410fa9','/department/add','部门添加'),('url_dbbdbd1f7dc04b80b44fc8727f245ae2','/carCheck/add','车检添加'),('url_e14f52dbf4384883975e6c549aa85616','/customer/add','客户添加'),('url_e2269a7a1863409593a3eaa95a8816f4','/carCheck/get','车检获取'),('url_e54ba54d834841c59e59181585f913b3','/car/delete','车辆删除'),('url_e5c72475b3d5492e80220bc4b99fac3d','/customer/delete','客户删除'),('url_ea22d25c590c47848cf458769c167dfb','/user/add','用户添加'),('url_f5623792f14340a18803a5a019f5c03b','/url/get','删除获取');

/*Table structure for table `tab_user` */

DROP TABLE IF EXISTS `tab_user`;

CREATE TABLE `tab_user` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `user_sex` char(1) DEFAULT NULL,
  `user_pwd` varchar(50) DEFAULT NULL,
  `user_phone` char(11) DEFAULT NULL,
  `user_photo` varchar(100) DEFAULT NULL,
  `department_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_15` (`department_id`),
  CONSTRAINT `fk_15` FOREIGN KEY (`department_id`) REFERENCES `tab_department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_user` */

insert  into `tab_user`(`user_id`,`user_name`,`user_sex`,`user_pwd`,`user_phone`,`user_photo`,`department_id`) values ('user_0011','张工','男','e10adc3949ba59abbe56e057f20f883e','15036088049','user_0011.jpeg','department_001'),('user_0012','张总','男','e10adc3949ba59abbe56e057f20f883e','15036088042','user_0012.jpeg','department_001'),('user_0013','王工','男','e10adc3949ba59abbe56e057f20f883e','15036088049','user_0013.jpeg','department_002'),('user_0014','王总','男','e10adc3949ba59abbe56e057f20f883e','15036088049','user_0014.jpeg','department_002'),('user_0015','刘工','男','e10adc3949ba59abbe56e057f20f883e','15036088049','user_0015.jpeg','department_003'),('user_0016','刘总','男','e10adc3949ba59abbe56e057f20f883e','15036088049','user_0016.jpeg','department_003'),('user_0017','高经理','男','e10adc3949ba59abbe56e057f20f883e','15036088049','user_0017.jpeg','department_004'),('user_c79106f1b0df41cbb925aad6d5a9de58','呵呵','男','123456','22222222222','user_c79106f1b0df41cbb925aad6d5a9de58.jpeg','department_003');

/*Table structure for table `tab_user_role` */

DROP TABLE IF EXISTS `tab_user_role`;

CREATE TABLE `tab_user_role` (
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `role_id` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_12` (`role_id`),
  CONSTRAINT `fk_11` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`user_id`),
  CONSTRAINT `fk_12` FOREIGN KEY (`role_id`) REFERENCES `tab_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_user_role` */

insert  into `tab_user_role`(`user_id`,`role_id`) values ('user_0017','role_01'),('user_0012','role_02'),('user_0014','role_02'),('user_0016','role_02'),('user_0011','role_03'),('user_0013','role_03'),('user_0015','role_03');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
