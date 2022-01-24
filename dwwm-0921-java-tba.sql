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

-- 2022-01-24 10:44:14
