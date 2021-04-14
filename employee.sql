/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.16 : Database - employee
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`employee` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `employee`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `dept_id` varchar(255) NOT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `department` */

insert  into `department`(`dept_id`,`dept_name`) values 
('7e7aa0d1-31d3-42b6-974d-37c848e06040','Lead'),
('d42f2322-446a-475e-a26c-42879c697f16','Manager');

/*Table structure for table `tbl_employee` */

DROP TABLE IF EXISTS `tbl_employee`;

CREATE TABLE `tbl_employee` (
  `emp_id` varchar(255) NOT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FK7musitethmimn49matruix6xl` (`department`),
  CONSTRAINT `FK7musitethmimn49matruix6xl` FOREIGN KEY (`department`) REFERENCES `department` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tbl_employee` */

insert  into `tbl_employee`(`emp_id`,`emp_name`,`department`) values 
('d4c1ee19-75e2-48ae-b08d-cab602c62a5d','karthick','d42f2322-446a-475e-a26c-42879c697f16');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
