-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sistema-powerrangers
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sistema-powerrangers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistema-powerrangers` DEFAULT CHARACTER SET utf8 ;
USE `sistema-powerrangers` ;

-- -----------------------------------------------------
-- Table `sistema-powerrangers`.`Megazord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema-powerrangers`.`Megazord` (
  `idMegazord` INT NOT NULL AUTO_INCREMENT,
  `nomeMegazord` VARCHAR(45) NOT NULL,
  `poderMegazord` INT NOT NULL,
  PRIMARY KEY (`idMegazord`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema-powerrangers`.`Zord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema-powerrangers`.`Zord` (
  `idZord` INT NOT NULL AUTO_INCREMENT,
  `nomeZord` VARCHAR(45) NOT NULL,
  `poderZord` INT NOT NULL,
  `Megazord_idMegazord` INT NULL,
  PRIMARY KEY (`idZord`),
  INDEX `fk_Zord_Megazord1_idx` (`Megazord_idMegazord` ASC),
  CONSTRAINT `fk_Zord_Megazord1`
    FOREIGN KEY (`Megazord_idMegazord`)
    REFERENCES `sistema-powerrangers`.`Megazord` (`idMegazord`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistema-powerrangers`.`Ranger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema-powerrangers`.`Ranger` (
  `idRanger` INT NOT NULL AUTO_INCREMENT,
  `corRanger` VARCHAR(45) NOT NULL,
  `habilidadeRanger` INT NOT NULL,
  `armaRanger` VARCHAR(45) NOT NULL,
  `Zord_idZord` INT NULL,
  PRIMARY KEY (`idRanger`),
  INDEX `fk_Ranger_Zord1_idx` (`Zord_idZord` ASC),
  CONSTRAINT `fk_Ranger_Zord1`
    FOREIGN KEY (`Zord_idZord`)
    REFERENCES `sistema-powerrangers`.`Zord` (`idZord`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
