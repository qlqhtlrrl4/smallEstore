CREATE TABLE `estore`.`covidhospital` (
  `dywk` VARCHAR(100) NULL,
  `covid_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `endTm` VARCHAR(100) NULL,
  `hldyYn` VARCHAR(100) NULL,
  `lunchEndTm` VARCHAR(100) NULL,
  `lunchSttTm` VARCHAR(100) NULL,
  `orgTlno` VARCHAR(100) NULL,
  `orgZipaddr` VARCHAR(100) NULL,
  `orgcd` VARCHAR(100) NULL,
  `orgnm` VARCHAR(100) NULL,
  `slrYmd` VARCHAR(100) NULL,
  `sttTm` VARCHAR(100) NULL,
  PRIMARY KEY (`covid_id`));
  
