create schema simple_performance;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_performance`.`user` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `registered_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_performance`.`reservation` (
  `id` BIGINT(32) NOT NULL,
  `user_id` BIGINT(32) NOT NULL,
  `performance_id` BIGINT(32) NOT NULL,
  `reservation_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` VARCHAR(45) NULL DEFAULT 'unpaid',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`performance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_performance`.`performance` (
  `performance_id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `price` BIGINT(32) NOT NULL,
  PRIMARY KEY (`performance_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_performance`.`reservation` (
  `id` BIGINT(32) NOT NULL,
  `user_id` BIGINT(32) NOT NULL,
  `performance_id` BIGINT(32) NOT NULL,
  `reservation_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` VARCHAR(45) NULL DEFAULT 'unpaid',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
