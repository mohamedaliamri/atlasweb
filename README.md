# atlasweb

Il faut créer ces req avant de commencer les devs :


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";



DROP TABLE IF EXISTS `role`;

CREATE TABLE IF NOT EXISTS `role` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



INSERT INTO `role` (`name`) VALUES
('ROLE_ADMIN'),
('ROLE_CLIENT');




DROP TABLE IF EXISTS `utilisateur`;

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `phonenumber` varchar(256) NOT NULL,
  `code` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `role_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`phonenumber`),
  KEY `role_name` (`role_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



INSERT INTO `utilisateur` (`phonenumber`, `code`, `name`, `role_name`) VALUES
('3334', '3', 'MED', 'ROLE_ADMIN'),
('4444', '5', 'ALI', 'ROLE_CLIENT');






