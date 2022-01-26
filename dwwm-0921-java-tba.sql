-- Adminer 4.7.7 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `room` (`id`, `name`, `description`) VALUES
(1,	'bedroom',	'This is where you usually sleep. It\'s quite small, but at least the bed is comfy.'),
(2,	'bathroom',	'This is the bathroom. There\'s no windows in there, so it tends to get easily dank.'),
(3,	'kitchen',	'This is the kitchen. It still smells of yesterday\'s dinner.'),
(4,	'attic',	'Dusty.');

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT 1,
  `room_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `item` (`id`, `name`, `visible`, `room_id`) VALUES
(1,	'bed',	1,	1),
(3,	'curtains',	1,	1),
(4,	'shower',	1,	2),
(5,	'toothbrush',	1,	2),
(6,	'cookie',	1,	3),
(7,	'faucet',	1,	3),
(8,	'lift',	1,	4),
(9,	'lift',	1,	1);

DROP TABLE IF EXISTS `direction_command`;
CREATE TABLE `direction_command` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `direction_command` (`id`, `name`) VALUES
(1,	'east'),
(4,	'north'),
(2,	'south'),
(3,	'west');

DROP TABLE IF EXISTS `room_connection`;
CREATE TABLE `room_connection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_room_id` int(10) unsigned NOT NULL,
  `to_room_id` int(10) unsigned NOT NULL,
  `direction_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `from_room_id_direction_id` (`from_room_id`,`direction_id`),
  KEY `to_room_id` (`to_room_id`),
  KEY `direction_id` (`direction_id`),
  CONSTRAINT `room_connection_ibfk_1` FOREIGN KEY (`from_room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE,
  CONSTRAINT `room_connection_ibfk_2` FOREIGN KEY (`to_room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE,
  CONSTRAINT `room_connection_ibfk_3` FOREIGN KEY (`direction_id`) REFERENCES `direction_command` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `room_connection` (`id`, `from_room_id`, `to_room_id`, `direction_id`) VALUES
(1,	1,	2,	1),
(2,	2,	1,	3),
(3,	1,	3,	4),
(4,	3,	1,	2);

-- 2022-01-24 10:44:14
