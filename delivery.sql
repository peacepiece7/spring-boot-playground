create schema if not exists delivery;

CREATE TABLE IF NOT EXISTS `delivery`.`account` (
  `id` BIGINT(32) NOT NULL,
  PRIMARY KEY (`id`)); 