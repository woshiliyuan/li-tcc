/*
MySQL
*/
create database `tcc` charset=utf8;

create database `tcc_account` charset=utf8;

use `tcc_account`;

/*Table structure for table `account` */

drop table if exists `account`;

create table `account` (
  `id` bigint(20) not null auto_increment,
  `user_id` varchar(128) not null,
  `balance` decimal(10,0) not null comment '用户余额',
  `freeze_amount` decimal(10,0) not null comment '冻结金额，扣款暂存余额',
  `create_time` datetime not null,
  `update_time` datetime default null,
  primary key (`id`)
) engine=InnoDB default charset=utf8;

/*Data for the table `account` */

insert  into `account`(`id`,`user_id`,`balance`,`freeze_amount`,`create_time`,`update_time`) 
values(1,'10000',10000,0,current_timestamp,current_timestamp);



create database `tcc_stock` charset=utf8;

use `tcc_stock`;

/*table structure for table `inventory` */

drop table if exists `inventory`;

create table `inventory` (
  `id` bigint(20) not null auto_increment,
  `product_id` varchar(128) not null,
  `total_inventory` int(10) not null comment '总库存',
  `lock_inventory` int(10) not null comment '锁定库存',
  primary key (`id`)
) engine=InnoDB default charset=utf8;

/*Data for the table `inventory` */

insert  into `inventory`(`id`,`product_id`,`total_inventory`,`lock_inventory`) 
values(1,'1',1000,0);


create database `tcc_order` charset=utf8;

use `tcc_order`;
drop table if exists `order`;

create table `order` (
  `id` bigint(20) not null auto_increment,
  `create_time` datetime not null,
  `number` varchar(20)  not null,
  `status` tinyint(4) not null,
  `product_id` varchar(128) not null,
  `total_amount` decimal(10,0) not null,
  `count` int(4) not null,
  `user_id` varchar(128) not null,
  primary key (`id`)
) engine=InnoDB default charset=utf8;



