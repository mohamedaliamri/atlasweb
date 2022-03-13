# atlasweb

Il faut créer ces req avant de commencer les devs :


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";



--
-- Base de données : `atlasdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`name`) VALUES
('ROLE_ADMIN'),
('ROLE_CLIENT');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `phonenumber` varchar(256) NOT NULL,
  `code` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `role_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`phonenumber`),
  KEY `role_name` (`role_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`phonenumber`, `code`, `name`, `role_name`) VALUES
('3334', '3', 'MED', 'ROLE_ADMIN'),
('4444', '5', 'ALI', 'ROLE_CLIENT')

-- --------------------------------------------------------

--
-- Structure de la table `utilisatuer`
--

DROP TABLE IF EXISTS `utilisatuer`;
CREATE TABLE IF NOT EXISTS `utilisatuer` (
  `phonenumber` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phonenumber`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

