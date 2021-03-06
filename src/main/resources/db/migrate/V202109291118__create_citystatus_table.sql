CREATE TABLE `estore`.`citystatus` (
  `citystatus_id` BIGINT(20) NOT NULL,
  `deathCnt` INT(11) NULL,
  `defCnt` INT(11) NULL,
  `createDt` VARCHAR(255) NULL,
  `gubunCn` VARCHAR(255),
  `gubunEn` VARCHAR(255),
  `gubun` VARCHAR(255) NULL,
  `incDec` INT(11) NULL,
  `isolClearCnt` INT(11) NULL,
  `isolIngCnt` INT(11) NULL,
  `localOccCnt` INT(11) NULL,
  `overFlowCnt` INT(11) NULL,
  `qurRate` DOUBLE NULL,
  `seq` INT(11) NULL,
  `stdDay` VARCHAR(255) NULL,
  `updateDt` VARCHAR(255) NULL,
  PRIMARY KEY (`citystatus_id`));