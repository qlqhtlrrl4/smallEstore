CREATE TABLE `estore`.`covidstatus` (
  `accDefRate` DOUBLE NULL,
  `covidstatus_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `accExamCnt` INT(11) NULL,
  `accExamCompCnt` INT(11) NULL,
  `careCnt` INT(11) NULL,
  `clearCnt` INT(11) NULL,
  `createDt` VARCHAR(255) NULL,
  `stateDt` VARCHAR(255) NULL,
  `stateTime` VARCHAR(255) NULL,
  `updateDt` VARCHAR(255) NULL,
  `deathCnt` INT(11) NULL,
  `decideCnt` INT(11) NULL,
  `examCnt` INT(11) NULL,
  `resutlNegCnt` INT(11) NULL,
  `seq` INT(11) NULL,
  PRIMARY KEY (`covidstatus_id`));
  
