/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.45-community-nt : Database - db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db`;

/*Table structure for table `countries` */

DROP TABLE IF EXISTS `countries`;

CREATE TABLE `countries` (
  `country_id` int(11) NOT NULL auto_increment,
  `country` varchar(30) NOT NULL,
  PRIMARY KEY  (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `countries` */

insert  into `countries`(`country_id`,`country`) values (1,'India'),(2,'America'),(3,'Australia'),(4,'China'),(5,'Germany'),(6,'Nepal'),(7,'Shri Lanka'),(8,'Bangladesh'),(9,'South Africa'),(10,'Britain'),(11,'Tokio'),(12,'Japan');

/*Table structure for table `feedback_types` */

DROP TABLE IF EXISTS `feedback_types`;

CREATE TABLE `feedback_types` (
  `feedback_type_id` int(11) NOT NULL auto_increment,
  `feedback_type` varchar(7) NOT NULL,
  PRIMARY KEY  (`feedback_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `feedback_types` */

insert  into `feedback_types`(`feedback_type_id`,`feedback_type`) values (1,'like'),(2,'dislike'),(3,'spam');

/*Table structure for table `gender` */

DROP TABLE IF EXISTS `gender`;

CREATE TABLE `gender` (
  `gender_id` int(11) NOT NULL auto_increment,
  `gender` varchar(6) NOT NULL,
  PRIMARY KEY  (`gender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `gender` */

insert  into `gender`(`gender_id`,`gender`) values (1,'male'),(2,'female');

/*Table structure for table `like_dislikes` */

DROP TABLE IF EXISTS `like_dislikes`;

CREATE TABLE `like_dislikes` (
  `like_dislike_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `feedback_type_id` int(11) NOT NULL,
  PRIMARY KEY  (`like_dislike_id`),
  KEY `fk_like_dislike_users` (`user_id`),
  KEY `fk_like_dislike_feedback_types` (`feedback_type_id`),
  KEY `fk_like_dislike_posts` (`post_id`),
  CONSTRAINT `fk_like_dislike_posts` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_like_dislike_feedback_types` FOREIGN KEY (`feedback_type_id`) REFERENCES `feedback_types` (`feedback_type_id`),
  CONSTRAINT `fk_like_dislike_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=latin1;

/*Data for the table `like_dislikes` */

insert  into `like_dislikes`(`like_dislike_id`,`user_id`,`post_id`,`feedback_type_id`) values (126,6,27,1),(127,6,42,2),(128,6,42,1),(129,6,31,1),(130,6,31,1),(131,6,31,1),(132,6,31,1),(133,6,31,1),(134,6,31,1),(135,6,31,1),(136,6,31,1),(137,6,31,1),(138,6,31,1),(139,6,31,1),(140,6,31,1),(141,6,31,1);

/*Table structure for table `moderators` */

DROP TABLE IF EXISTS `moderators`;

CREATE TABLE `moderators` (
  `moderator_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  PRIMARY KEY  (`moderator_id`),
  KEY `fk_moderators_users` (`user_id`),
  KEY `fk_moderators_topics` (`topic_id`),
  KEY `fk_moderators_statuses` (`status_id`),
  CONSTRAINT `fk_moderators_statuses` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `fk_moderators_topics` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `fk_moderators_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `moderators` */

/*Table structure for table `post_types` */

DROP TABLE IF EXISTS `post_types`;

CREATE TABLE `post_types` (
  `post_type_id` int(11) NOT NULL auto_increment,
  `post_type` varchar(10) NOT NULL,
  PRIMARY KEY  (`post_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `post_types` */

insert  into `post_types`(`post_type_id`,`post_type`) values (1,'question'),(2,'reply');

/*Table structure for table `posts` */

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `post_type_id` int(11) NOT NULL,
  `post_date_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `spam_count` int(11) NOT NULL default '0',
  `like_count` int(11) NOT NULL default '0',
  `dislike_count` int(11) NOT NULL default '0',
  `status_id` int(11) default '1',
  PRIMARY KEY  (`post_id`),
  KEY `fk_posts_users` (`user_id`),
  KEY `fk_posts_post_types` (`post_type_id`),
  KEY `fk_posts_statuses` (`status_id`),
  CONSTRAINT `fk_posts_post_types` FOREIGN KEY (`post_type_id`) REFERENCES `post_types` (`post_type_id`),
  CONSTRAINT `fk_posts_statuses` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `fk_posts_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `posts` */

insert  into `posts`(`post_id`,`user_id`,`post_type_id`,`post_date_time`,`spam_count`,`like_count`,`dislike_count`,`status_id`) values (27,6,2,'2016-05-05 18:31:37',0,1,0,1),(30,6,2,'2016-05-05 19:34:46',0,0,0,1),(31,6,1,'2016-05-13 21:48:40',0,13,0,1),(42,6,2,'2016-05-06 10:37:35',0,1,1,1);

/*Table structure for table `professions` */

DROP TABLE IF EXISTS `professions`;

CREATE TABLE `professions` (
  `profession_id` int(11) NOT NULL auto_increment,
  `profession` varchar(30) NOT NULL,
  PRIMARY KEY  (`profession_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `professions` */

insert  into `professions`(`profession_id`,`profession`) values (1,'developer'),(2,'softsware manager'),(3,'software tester'),(4,'trainer'),(5,'software architect'),(6,'student');

/*Table structure for table `questions` */

DROP TABLE IF EXISTS `questions`;

CREATE TABLE `questions` (
  `question_id` int(11) NOT NULL auto_increment,
  `question_title` varchar(60) NOT NULL,
  `question_detail` varchar(50000) NOT NULL,
  `reply_count` int(11) NOT NULL default '0',
  `topic_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY  (`question_id`),
  KEY `fk_questions_topics` (`topic_id`),
  KEY `fk_questions_posts` (`post_id`),
  CONSTRAINT `fk_questions_posts` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_questions_topics` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `questions` */

/*Table structure for table `replies` */

DROP TABLE IF EXISTS `replies`;

CREATE TABLE `replies` (
  `reply_id` int(11) NOT NULL auto_increment,
  `question_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `message` varchar(5000) NOT NULL,
  PRIMARY KEY  (`reply_id`),
  KEY `fk_replies_questions` (`question_id`),
  KEY `fk_replies_posts` (`post_id`),
  CONSTRAINT `fk_replies_posts` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_replies_questions` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `replies` */

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `status_id` int(11) NOT NULL auto_increment,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY  (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `status` */

insert  into `status`(`status_id`,`status`) values (1,'active'),(2,'blocked'),(3,'closed'),(4,'inactive');

/*Table structure for table `subjects` */

DROP TABLE IF EXISTS `subjects`;

CREATE TABLE `subjects` (
  `subject_id` int(11) NOT NULL auto_increment,
  `subject` varchar(25) NOT NULL,
  `topic_count` int(11) NOT NULL default '0',
  PRIMARY KEY  (`subject_id`),
  UNIQUE KEY `subject` (`subject`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `subjects` */

insert  into `subjects`(`subject_id`,`subject`,`topic_count`) values (13,'JAVA',0);

/*Table structure for table `topics` */

DROP TABLE IF EXISTS `topics`;

CREATE TABLE `topics` (
  `topic_id` int(11) NOT NULL auto_increment,
  `topic` varchar(25) NOT NULL,
  `post_count` int(11) NOT NULL default '0',
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY  (`topic_id`),
  UNIQUE KEY `topic` (`topic`),
  UNIQUE KEY `topic_2` (`topic`),
  KEY `fk_topics_subjects` (`subject_id`),
  CONSTRAINT `fk_topics_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `topics` */

/*Table structure for table `user_types` */

DROP TABLE IF EXISTS `user_types`;

CREATE TABLE `user_types` (
  `user_type_id` int(11) NOT NULL auto_increment,
  `user_type` varchar(25) NOT NULL,
  PRIMARY KEY  (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `user_types` */

insert  into `user_types`(`user_type_id`,`user_type`) values (1,'administrator'),(2,'moderator'),(3,'general_user');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `user_type_id` int(11) NOT NULL default '3',
  `joining_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `user_question_count` int(11) NOT NULL default '0',
  `user_reply_count` int(11) NOT NULL default '0',
  `status_id` int(11) NOT NULL default '4',
  `activation_code` varchar(100) NOT NULL,
  `activation_status` tinyint(1) NOT NULL default '0',
  `profession_id` int(11) default NULL,
  `gender_id` int(11) default NULL,
  `country_id` int(11) default NULL,
  `pic_path` varchar(150) default NULL,
  `date_of_birth` date default NULL,
  `interest` varchar(500) default NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_users_user_types` (`user_type_id`),
  KEY `fk_users_statuses` (`status_id`),
  KEY `fk_profession_users` (`profession_id`),
  KEY `gender_id` (`gender_id`),
  KEY `country_id` (`country_id`),
  CONSTRAINT `fk_profession_users` FOREIGN KEY (`profession_id`) REFERENCES `professions` (`profession_id`),
  CONSTRAINT `fk_users_statuses` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `fk_users_user_types` FOREIGN KEY (`user_type_id`) REFERENCES `user_types` (`user_type_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`profession_id`) REFERENCES `professions` (`profession_id`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`),
  CONSTRAINT `users_ibfk_3` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`user_name`,`email`,`password`,`user_type_id`,`joining_date`,`user_question_count`,`user_reply_count`,`status_id`,`activation_code`,`activation_status`,`profession_id`,`gender_id`,`country_id`,`pic_path`,`date_of_birth`,`interest`) values (1,'abcd','abc@gmail.com','1111111',3,'2016-05-09 10:09:27',0,0,1,'',0,NULL,NULL,NULL,NULL,'0000-00-00',NULL),(2,'mayank','mayank@gmail.com','1111111',3,'2015-08-13 11:00:23',0,0,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL),(3,'palak','palak@gm.com','1111111',3,'2015-08-15 10:00:29',0,0,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL),(4,'manu','manu@gmail.com','QZUXMrYgM7IcAvZNICj+hlCFxBJ9qDvxuisOedfcpOhv4OgCBWHpI7Pzj+EhRQ2e',3,'2016-05-09 10:20:08',0,0,1,'144135429709794FAE42AF9A7EBB135370A606A43A1F7',0,NULL,NULL,NULL,NULL,'0000-00-00',NULL),(5,'pragna pahariya','pragna.pahariya@gmail.com','AHv/Y6hTAeZShEml7qx4eo5sbkL5Fnai8R9yD2oktvnAv4aVDAOKSYAO6EvgY1Er',3,'2015-09-05 22:49:15',0,0,1,'1441473555617EC17AD4F83D7FF4F5D60FCDDB60FC28F',0,NULL,NULL,NULL,NULL,NULL,NULL),(6,'harsh pahariya','himanshu@gmail.com','5t1ogHH8uzw71ttVkYFjcFJoC7rajjCQrMvEcSomRxKNTNCrKbP26gOq7YSVF7nO',1,'2016-05-13 21:47:00',0,0,1,'14415075244706AFC9B5C56C1053DF68CE55DD51FFAE3',0,1,1,1,NULL,'2016-01-01','MUHBUNIOM'),(7,'vaishali jar','vjar@gmail.com','omFcEFxUSW25WdMBNZsESBI+e3xsOW/gqTq2rP+2nk/FW1/DeRaoBVzwMXIwajwV',3,'2016-02-28 22:11:04',0,0,4,'14566776642986FE6D9E3D115A87C4D3136562222C583',0,NULL,NULL,NULL,NULL,NULL,NULL),(8,'krati reja','krati@gmail.com','dzC/UQhAMCcA6hC3Yam7Gpa0gt8yzQFpayuwP1oiATZWWLo5+IG9IrNsQqbX8icE',3,'2016-05-12 08:39:52',0,0,4,'145951395274036B5DF5B58E2016CC5A086092CE55928',0,NULL,NULL,NULL,NULL,'2007-07-07',NULL),(9,'Pari Pahariya','pari@gmail.com','FR5Aiv3qlgjl98PhnFB/22rRIadb83L4k9PcHvdg5OdzTDbTxiaj7ii+pe+BjaKX',3,'2016-05-06 17:11:54',0,0,4,'1462534913937C9B64D6CFEB5ED2BCDD863296633771F',0,NULL,NULL,NULL,NULL,NULL,NULL),(10,'kanchi','kanchi@gmail.com','muoLFDvsGrA1I/fJBOPvgp63FOTC24YcHq5nKm0KJQJeMGHTx15kfj8q4J5qbArO',3,'2016-05-12 08:36:44',0,0,4,'14628652154320699E30F78ACBE62F395FA4BAEFC3EF2',0,2,NULL,5,NULL,'2004-02-01',NULL),(11,'gaurav','sarawgi','FWu0aoQfr3ogjf1MfCSVhJexOAZoq3fIcj5f7Wpa1DLWss4CWTh0xJNJp9g5e3TH',3,'2016-05-13 21:41:01',0,0,4,'14631558615167C8588D1B72BB296091AB1591F17C8E4',0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
