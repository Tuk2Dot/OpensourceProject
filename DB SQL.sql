/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `student`;

/*Table structure for table `building` */

DROP TABLE IF EXISTS `building`;

CREATE TABLE `building` (
  `id` int(8) NOT NULL AUTO_INCREMENT,     
  `bname` varchar(200) DEFAULT NULL COMMENT '건물 이름',
  `classroom` varchar(100) DEFAULT NULL COMMENT '건물 구조',
  `office` varchar(100) DEFAULT NULL COMMENT '상담실,study room등',
  `officenum` int(10) DEFAULT NULL COMMENT '학과전화번호',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `building` */

insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(1,'S4-1 소프트웨어학과','101-312','흡연구역 1개,studio room 1개 ',0432612260);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(2,'S4-2 간호학과','101-414','흡연구역 2개,studio room 3개 ',0432491710);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(3,'S1-5 미생물학과','101-423','흡연구역2개, studio room 4개',0432613575);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(4,'S1-6 화학과','101-619','흡연구역3개, studio room 6개',0432612304);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(5,'S20 응용생명공학부','101-518','흡연구역3개, studio room 4개',0432612525);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(6,'S21-3 특용식물학과','101-311','흡연구역1개, studio room 2개',0432615681);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(7,'S21-5 농업경제학과','110-282','흡연구역2개, studio room 2개',0432612587);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(8,'S21-22 농업생명환경대학','101-202','흡연구역1개, studio room 0개',0432612504);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(9,'S21-20 농업생명환경대학','106-110','흡연구역1개, studio room 0개',0432612504);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(10,'S21-23 농업생명환경대학','101-106','흡연구역1개, studio room 0개',0432612504);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(11,'S21-24 바이오시스템공학과','101-212','흡연구역1개, studio room 1개',0432612579);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(12,'N14 인문사회관','101-704','흡연구역1개, studio room 4개',0432612563);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(13,'N15 사회과학대학','101-450','흡연구역2개, studio room 4개',0432612174);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(14,'N13 국제경영학과','101-508','흡연구역1개, studio room 3개',04326123246);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(15,'N16 인문대학','101-421','흡연구역2개, studio room 3개',0432612090);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(16,'N18 역사교욕과','102-323','흡연구역1개, studio room 3개',0432612742);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(17,'N20-1 생확대학','101-417','흡연구역1개, studio room 3개',0432612744);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(18,'N20-2 아동복지학과','101-201','오락시설 1개 ',0432612793);
insert  into `building`(`id`,`bname`,`classroom`,`office`,`officenum`) values 
(19,'E1-1 E1-2 사번대학','E1-1-101-E1-4-413','흡연구역2개, studio room 4개',04326126446);

/*Table structure for table `buildinginfo` */

DROP TABLE IF EXISTS `buildinginfo`;

CREATE TABLE `buildinginfo` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `pname` varchar(200) DEFAULT NULL,
  `hour` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `buildinginfo` */
insert  into `buildinginfo`(`id`,`pname`,`hour`) values 
(1,'E2 개신문화관','충북대 서점(10:00-18:00),구두수선점(10:00-18:00),도장점(10:00-18:00),안경점(10:00-18:00),카페점(10:00-18:00)，디저트 가게(10:00-18:00)');
insert  into `buildinginfo`(`id`,`pname`,`hour`) values 
(2,'N21 은하수식당','학기중(11:30-13:30 17:30-19:00),방학중(11:30-13:30)');
insert  into `buildinginfo`(`id`,`pname`,`hour`) values 
(3,'E3 1층 한빛식당','학기중(09:00-18:30),방학중(11:00-14:00)');
insert  into `buildinginfo`(`id`,`pname`,`hour`) values 
(4,'E3 2층 병빛식당','학기중(11:30-14:00),방학중(11:30-14:00)');
insert  into `buildinginfo`(`id`,`pname`,`hour`) values 
(5,'NH농협은행','09:30-15:30');
insert  into `buildinginfo`(`id`,`pname`,`hour`) values 
(6,'대운동장','00:00-24:00');


/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(8) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `studentno` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;user
