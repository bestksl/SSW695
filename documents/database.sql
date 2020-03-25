-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(64) NOT NULL,
  `last_name` VARCHAR(64) NULL,
  `nick_name` VARCHAR(32) NULL,
  `email` VARCHAR(64) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `date_of_birth` VARCHAR(45) NULL,
  `gender` CHAR(16) NULL,
  `permissions` VARCHAR(128) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

INSERT INTO `category` (id, `name`) VALUES 
    (1, 'Art'), 
    (2, 'Education'),
    (3, 'Entertainment'),
    (4, 'Sports');

-- -----------------------------------------------------
-- Table `hobby`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hobby` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `description` VARCHAR(45) NULL,
  `category_id` INT NOT NULL,
  `plus18_only` TINYINT NULL,
  `photo_id` VARCHAR(42) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_hobby_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_hobby_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hobby_id` INT NOT NULL,
  `title` VARCHAR(64) NOT NULL,
  `on_datetime` DATETIME NOT NULL,
  `location` VARCHAR(128) NULL,
  `capacity` DOUBLE NULL,
  `description` VARCHAR(2048) NULL,
  `plus18_only` TINYINT NULL,
  `organizer` VARCHAR(128) NULL,
  `photo_id` VARCHAR(42) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_hobby1_idx` (`hobby_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_event_hobby1`
    FOREIGN KEY (`hobby_id`)
    REFERENCES `hobby` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
