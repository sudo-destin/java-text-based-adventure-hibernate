-- Adminer 4.7.7 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP DATABASE IF EXISTS `2itech-cda-lyon-tba`;
CREATE DATABASE `2itech-cda-lyon-tba` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `2itech-cda-lyon-tba`;

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `rooms` (`id`, `name`) VALUES
(1,	'bedroom'),
(2,	'bathroom'),
(3,	'corridor'),
(4,	'kitchen');

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_room_id` (`name`,`room_id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `items` (`id`, `name`, `room_id`) VALUES
(1,	'bed',	1),
(3,	'cookie',	4),
(2,	'window',	1);

DROP TABLE IF EXISTS `commands`;
CREATE TABLE `commands` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `command` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `default_message` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `commands` (`id`, `command`, `default_message`) VALUES
(1,	'open %',	'This doesn\'t seem to open.'),
(2,	'eat %',	'This is not edible!'),
(3,	'use %',	'You have no idea how to use this.');

DROP TABLE IF EXISTS `room_connections`;
CREATE TABLE `room_connections` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_room_id` int(10) unsigned NOT NULL,
  `to_room_id` int(10) unsigned NOT NULL,
  `direction_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `from_room_id_direction_id` (`from_room_id`,`direction_id`),
  KEY `to_room_id` (`to_room_id`),
  KEY `direction_id` (`direction_id`),
  CONSTRAINT `room_connections_ibfk_1` FOREIGN KEY (`from_room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE,
  CONSTRAINT `room_connections_ibfk_2` FOREIGN KEY (`to_room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE,
  CONSTRAINT `room_connections_ibfk_3` FOREIGN KEY (`direction_id`) REFERENCES `directions` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `room_connections` (`id`, `from_room_id`, `to_room_id`, `direction_id`) VALUES
(1,	1,	2,	3),
(2,	1,	3,	4),
(3,	2,	1,	1),
(4,	3,	1,	2),
(5,	3,	4,	1),
(6,	4,	3,	3);

DROP TABLE IF EXISTS `directions`;
CREATE TABLE `directions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `command` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `command` (`command`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `directions` (`id`, `command`, `name`) VALUES
(1,	'east',	'East'),
(2,	'south',	'South'),
(3,	'west',	'West'),
(4,	'north',	'North');

DROP TABLE IF EXISTS `effects`;
CREATE TABLE `effects` (
  `effect_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(10) unsigned NOT NULL,
  `_order` int(10) unsigned NOT NULL,
  `command_id` int(10) unsigned NOT NULL,
  `new_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `target_item_id` int(10) unsigned DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `target_state_id` int(10) unsigned DEFAULT NULL,
  `operator` tinyint(4) DEFAULT NULL,
  `boolean_value` tinyint(1) DEFAULT NULL,
  `number_value` double DEFAULT NULL,
  `string_value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id_command_id__order` (`item_id`,`command_id`,`_order`),
  KEY `command_id` (`command_id`),
  KEY `target_item_id` (`target_item_id`),
  KEY `target_state_id` (`target_state_id`),
  CONSTRAINT `effects_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE,
  CONSTRAINT `effects_ibfk_2` FOREIGN KEY (`command_id`) REFERENCES `commands` (`id`) ON DELETE CASCADE,
  CONSTRAINT `effects_ibfk_3` FOREIGN KEY (`target_item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE,
  CONSTRAINT `effects_ibfk_4` FOREIGN KEY (`target_state_id`) REFERENCES `states` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `effects` (`effect_type`, `id`, `item_id`, `_order`, `command_id`, `new_name`, `target_item_id`, `message`, `target_state_id`, `operator`, `boolean_value`, `number_value`, `string_value`) VALUES
('Message',	1,	2,	0,	1,	NULL,	NULL,	'The window is now open.',	NULL,	NULL,	NULL,	NULL,	NULL),
('RemoveItem',	2,	3,	0,	2,	NULL,	3,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
('Message',	3,	3,	1,	2,	NULL,	NULL,	'You ate the cookie. Delicious!',	NULL,	NULL,	NULL,	NULL,	NULL),
('ChangeNumberState',	4,	1,	0,	3,	NULL,	NULL,	NULL,	2,	1,	NULL,	1,	NULL),
('Message',	5,	1,	1,	3,	NULL,	NULL,	'You took a quick nap. You feel refreshed!',	NULL,	NULL,	NULL,	NULL,	NULL);

DROP TABLE IF EXISTS `states`;
CREATE TABLE `states` (
  `state_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  `boolean_default_value` tinyint(1) DEFAULT NULL,
  `number_default_value` double DEFAULT NULL,
  `string_default_value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `states_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `states` (`state_type`, `id`, `name`, `item_id`, `boolean_default_value`, `number_default_value`, `string_default_value`) VALUES
('Boolean',	1,	'window_open',	2,	0,	NULL,	NULL),
('Number',	2,	'times_slept',	1,	NULL,	0,	NULL);

-- 2021-09-03 09:46:12
