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

CREATE TABLE IF NOT EXISTS `delivery`.`store` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `status` VARCHAR(50) NULL,
  `category` VARCHAR(50) NULL,
  `star` DOUBLE NULL,
  `thumbnail_url` VARCHAR(255) NOT NULL,
  `minimum_amount` DECIMAL(11,4) NOT NULL,
  `minimum_delivery_amount` DECIMAL(11,4) NOT NULL,
  `phone_number` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `delivery`.`store_menu` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `store_id` BIGINT(32) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `amount` DECIMAL(11,4) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `thumbnail_url` VARCHAR(255) NOT NULL,
  `like_count` INT NULL,
  `sequence` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_store_menu_store_idx` (`store_id` ASC) VISIBLE,
  CONSTRAINT `fk_store_menu_store`
    FOREIGN KEY (`store_id`)
    REFERENCES `delivery`.`store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `delivery`.`user_order` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `store_id` BIGINT(32) NOT NULL,
  `user_id` BIGINT(32) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `amount` DECIMAL(11,4) NOT NULL,
  `ordered_at` DATETIME NULL,
  `accepted_at` DATETIME NULL,
  `cooking_started_at` DATETIME NULL,
  `delivery_started_at` DATETIME NULL,
  `received_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_order_user1_idx` (`user_id` ASC) VISIBLE
    )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `delivery`.`user_order_menu` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `user_order_id` BIGINT(32) NOT NULL,
  `store_menu_id` BIGINT(32) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_order_store_menu1_idx` (`store_menu_id` ASC) VISIBLE,
  INDEX `fk_user_order_menu_user_order1_idx` (`user_order_id` ASC) VISIBLE
  -- CONSTRAINT `fk_user_order_store_menu1`
  --  FOREIGN KEY (`store_menu_id`)
  --  REFERENCES `delivery`.`store_menu` (`id`)
  --  ON DELETE NO ACTION
  --  ON UPDATE NO ACTION,
  -- CONSTRAINT `fk_user_order_menu_user_order1`
  -- FOREIGN KEY (`user_order_id`)
  -- REFERENCES `delivery`.`user_order` (`id`)
  -- ON DELETE NO ACTION
  -- ON UPDATE NO ACTION
    )
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `delivery`.`store_user` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `store_id` BIGINT(32) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `last_login_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_store_user_store1_idx` (`store_id` ASC) VISIBLE
  --   CONSTRAINT `fk_store_user_store1`
  --     FOREIGN KEY (`store_id`)
  --     REFERENCES `delivery`.`store` (`id`)
  --     ON DELETE NO ACTION
  --     ON UPDATE NO ACTION
  )
  ENGINE = InnoDB;

