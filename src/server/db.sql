SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `travel` DEFAULT CHARACTER SET utf8 ;
USE `travel` ;

-- -----------------------------------------------------
-- Table `travel`.`Image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`Image` ;

CREATE  TABLE IF NOT EXISTS `travel`.`Image` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `path` VARCHAR(255) NOT NULL ,
  `date` TIMESTAMP NOT NULL DEFAULT NOW() ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`User` ;

CREATE  TABLE IF NOT EXISTS `travel`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nickname` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `date_reg` TIMESTAMP NOT NULL DEFAULT NOW() ,
  `date_last` TIMESTAMP NULL ,
  `fk_userimage` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_userimage` (`fk_userimage` ASC) ,
  CONSTRAINT `fk_userimage`
    FOREIGN KEY (`fk_userimage` )
    REFERENCES `travel`.`Image` (`id` )
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`Item` ;

CREATE  TABLE IF NOT EXISTS `travel`.`Item` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `position` GEOMETRY NOT NULL ,
  `desc` VARCHAR(255) NOT NULL ,
  `addr` VARCHAR(255) NOT NULL ,
  `date` TIMESTAMP NOT NULL DEFAULT NOW() ,
  `fk_creater` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_creater` (`fk_creater` ASC) ,
  CONSTRAINT `fk_creater`
    FOREIGN KEY (`fk_creater` )
    REFERENCES `travel`.`User` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel`.`Book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`Book` ;

CREATE  TABLE IF NOT EXISTS `travel`.`Book` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(255) NOT NULL ,
  `date` TIMESTAMP NOT NULL ,
  `duration` INT NOT NULL ,
  `fk_bookuser` INT NOT NULL ,
  `fk_bookimage` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_bookimage` (`fk_bookimage` ASC) ,
  INDEX `fk_bookuser` (`fk_bookuser` ASC) ,
  CONSTRAINT `fk_bookuser`
    FOREIGN KEY (`fk_bookuser` )
    REFERENCES `travel`.`User` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bookimage`
    FOREIGN KEY (`fk_bookimage` )
    REFERENCES `travel`.`Image` (`id` )
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`MAP_BookItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`MAP_BookItem` ;

CREATE  TABLE IF NOT EXISTS `travel`.`MAP_BookItem` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `start_minute` INT NOT NULL ,
  `fk_bmap_item` INT NULL ,
  `fk_bmap_book` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_bmap_item` (`fk_bmap_item` ASC) ,
  INDEX `fk_bmap_book` (`fk_bmap_book` ASC) ,
  CONSTRAINT `fk_bmap_item`
    FOREIGN KEY (`fk_bmap_item` )
    REFERENCES `travel`.`Item` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bmap_book`
    FOREIGN KEY (`fk_bmap_book` )
    REFERENCES `travel`.`Book` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`Category` ;

CREATE  TABLE IF NOT EXISTS `travel`.`Category` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`MAP_CategoryItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`MAP_CategoryItem` ;

CREATE  TABLE IF NOT EXISTS `travel`.`MAP_CategoryItem` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fk_cmap_category` INT NULL ,
  `fk_cmap_item` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_cmap_category` (`fk_cmap_category` ASC) ,
  INDEX `fk_cmap_item` (`fk_cmap_item` ASC) ,
  CONSTRAINT `fk_cmap_category`
    FOREIGN KEY (`fk_cmap_category` )
    REFERENCES `travel`.`Category` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cmap_item`
    FOREIGN KEY (`fk_cmap_item` )
    REFERENCES `travel`.`Item` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel`.`MAP_ItemImage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `travel`.`MAP_ItemImage` ;

CREATE  TABLE IF NOT EXISTS `travel`.`MAP_ItemImage` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fk_imap_image` INT NULL ,
  `fk_imap_item` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_imap_image` (`fk_imap_image` ASC) ,
  INDEX `fk_imap_item` (`fk_imap_item` ASC) ,
  CONSTRAINT `fk_imap_item`
    FOREIGN KEY (`fk_imap_item` )
    REFERENCES `travel`.`Item` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_imap_image`
    FOREIGN KEY (`fk_imap_image` )
    REFERENCES `travel`.`Image` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
