create schema if not exists delivery;

CREATE TABLE IF NOT EXISTS `delivery`.`account` (
  `id` BIGINT(32) NOT NULL,
  PRIMARY KEY (`id`)); 
  
  CREATE TABLE IF NOT EXISTS `delivery`.`user` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `last_login` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;