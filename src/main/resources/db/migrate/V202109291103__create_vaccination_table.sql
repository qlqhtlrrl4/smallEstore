CREATE TABLE `estore`.`vaccination` (
  `vaccin_id` BIGINT(20) NOT NULL,
  `accumulatedFirstCnt` INT(11) NULL,
  `accumulatedSecondCnt` INT(11) NULL,
  `baseDate` VARCHAR(255) NULL,
  `firstCnt` INT(11) NULL,
  `secondCnt` INT(11) NULL,
  `sido` VARCHAR(255) NULL,
  `totalFirstCnt` INT(11) NULL,
  `totalSecondCnt` INT(11) NULL,
  PRIMARY KEY (`vaccin_id`));