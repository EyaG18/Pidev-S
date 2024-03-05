-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 02 mars 2024 à 18:07
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev2`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id_avis` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_produit` int(11) NOT NULL,
  `commentaire` text NOT NULL,
  `note` int(11) NOT NULL,
  `date_avis` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`id_avis`, `id_client`, `id_produit`, `commentaire`, `note`, `date_avis`) VALUES
(1, 3, 27, 'bonjour test  test sah', 2, '2024-03-01 15:10:31'),
(2, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:15:03'),
(3, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:26:07'),
(4, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:30:56'),
(5, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:33:13'),
(6, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:34:40'),
(7, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:54:44'),
(8, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:58:34'),
(9, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 14:59:53'),
(11, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 15:03:02'),
(13, 3, 27, 'bonjour test yassmine', 5, '2024-03-01 15:08:30'),
(14, 3, 41, 'hey bent grara', 4, '2024-03-01 15:10:10'),
(15, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 12:42:47'),
(16, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 12:45:15'),
(17, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 12:48:06'),
(18, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 12:55:11'),
(19, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 12:57:12'),
(20, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 12:59:03'),
(21, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:00:20'),
(22, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:02:28'),
(23, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:10:44'),
(24, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:11:47'),
(25, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:13:51'),
(26, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:15:01'),
(27, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:18:07'),
(28, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:19:16'),
(29, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:20:37'),
(30, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:21:15'),
(31, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:22:12'),
(32, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:26:07'),
(33, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:29:56'),
(34, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:30:41'),
(35, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:31:52'),
(36, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:36:04'),
(37, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:37:24'),
(38, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 13:40:55'),
(39, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 15:36:41'),
(40, 3, 27, 'bonjour test yassmine', 5, '2024-03-02 15:55:48');

-- --------------------------------------------------------

--
-- Structure de la table `catégorie`
--

CREATE TABLE `catégorie` (
  `Id_Catégorie` int(20) NOT NULL,
  `NomCatégorie` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `catégorie`
--

INSERT INTO `catégorie` (`Id_Catégorie`, `NomCatégorie`) VALUES
(9, 'Bagues'),
(8, 'Boucles Oreilles'),
(12, 'Chapeaux'),
(1, 'Lunetts Femme'),
(10, 'Pantalons'),
(3, 'Robes Midi'),
(11, 'Vestes');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `Id_Panier` int(11) NOT NULL,
  `id_user` int(255) NOT NULL,
  `Reference` int(11) DEFAULT NULL,
  `Date_com` date NOT NULL,
  `livrable` tinyint(1) NOT NULL,
  `Status` enum('ANNULEE','TRAITEE','EN_ATTENTE','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_commande`, `Id_Panier`, `id_user`, `Reference`, `Date_com`, `livrable`, `Status`) VALUES
(3, 6, 4, 1, '2024-02-25', 1, 'ANNULEE');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id_fournisseur` int(20) NOT NULL,
  `Id_Produit` int(11) NOT NULL,
  `nom_fournisseur` varchar(255) NOT NULL,
  `num_fournisseur` varchar(255) NOT NULL,
  `adresse_fournisseur` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `id_livraison` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `Reference` int(11) DEFAULT NULL,
  `Status_livraison` enum('en attente','traiter','annuler','') NOT NULL,
  `date_livraison` date NOT NULL,
  `prix_livraison` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `idOffre` int(11) NOT NULL,
  `Id_Produit` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `reduction` varchar(255) NOT NULL,
  `titre_offre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `Id_Panier` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `Id_Produit` int(11) NOT NULL,
  `QuantiteParProduit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`Id_Panier`, `id_user`, `Id_Produit`, `QuantiteParProduit`) VALUES
(6, 5, 17, 0),
(17, 9, 25, 3),
(18, 9, 22, 1),
(24, 3, 20, 3),
(37, 3, 17, 2),
(38, 3, 17, 4),
(39, 3, 25, 2),
(40, 3, 20, 5),
(41, 3, 22, 2),
(42, 3, 22, 1),
(43, 3, 26, 2),
(44, 3, 31, 1),
(45, 3, 20, 1),
(46, 3, 20, 2),
(49, 3, 26, 1),
(50, 3, 41, 1);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `Id_Produit` int(11) NOT NULL,
  `Id_Catégorie` int(20) NOT NULL,
  `NomP` varchar(20) NOT NULL,
  `PrixP` float NOT NULL,
  `QteP` int(11) NOT NULL,
  `QteSeuilP` int(11) NOT NULL,
  `ImageP` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`Id_Produit`, `Id_Catégorie`, `NomP`, `PrixP`, `QteP`, `QteSeuilP`, `ImageP`) VALUES
(17, 1, 'Lunettes', 159, 6, 7, 'C:\\xampp\\htdocs\\LunetteMauve.jpg'),
(20, 3, 'Robe Mango', 365, 9, 9, 'C:\\xampp\\htdocs\\PinkDress.jpg'),
(22, 9, 'Shiny Ring', 750, 10, 5, 'C:\\xampp\\htdocs\\bague.jpg'),
(25, 10, 'Mom Jeans', 289, 5, 5, 'C:\\xampp\\htdocs\\MomJeans.jpg'),
(26, 9, 'Diamond Rose', 295, 9, 5, 'C:\\xampp\\htdocs\\DiamondRose.jpg'),
(27, 9, 'Bague Diamon Bleu', 350, 19, 5, 'C:\\xampp\\htdocs\\DiamondBleu.jpg'),
(28, 9, 'Bague Emeraud', 350, 11, 5, 'C:\\xampp\\htdocs\\BagueEmeraude.jpg'),
(30, 9, 'Alliances', 780, 16, 5, 'C:\\xampp\\htdocs\\BagueDiamond.jpg'),
(31, 3, 'Robe Mango Festive', 175, 20, 7, 'C:\\xampp\\htdocs\\RobeEte.png'),
(32, 3, 'Robe Florale DG', 975, 11, 5, 'C:\\xampp\\htdocs\\RobeMangoFloral.jpg'),
(33, 8, 'Boucles Channel', 1250, 23, 5, 'C:\\xampp\\htdocs\\BouclesChannel.jpg'),
(34, 11, 'Manteau  Homme', 285, 11, 5, 'C:\\xampp\\htdocs\\ManteauHomme.jpg'),
(35, 11, 'Trench Femme', 199, 32, 10, 'C:\\xampp\\htdocs\\ManteauFemme.jpg'),
(36, 10, 'Pantalons Zara', 149, 14, 10, 'C:\\xampp\\htdocs\\Pantalon Noir.jpg'),
(37, 8, 'Creoles', 35, 13, 5, 'C:\\xampp\\htdocs\\Creoles.jpg'),
(38, 8, 'Trio de Boucles', 95, 13, 5, 'C:\\xampp\\htdocs\\TrioBoucles.jpg'),
(39, 8, 'Percing Etoilé', 45, 13, 5, 'C:\\xampp\\htdocs\\PercingEtoile.jpg'),
(40, 12, 'Chapeau Unisexe', 79, 17, 10, 'C:\\xampp\\htdocs\\ChapeauBeige.jpg'),
(41, 12, 'Chapeau Femme', 120, 14, 5, 'C:\\xampp\\htdocs\\ChapeauBeige.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date_reclamation` date NOT NULL,
  `statu_reclamation` enum('traité','en attente','non traité') NOT NULL DEFAULT 'en attente',
  `type_reclamation` enum('probléme produit','probléme livraison','service client insatisfaisant','autres') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `id_reclamation` int(11) NOT NULL,
  `reponse` text NOT NULL,
  `date_reponse` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(1) NOT NULL,
  `nomuser` varchar(255) DEFAULT NULL,
  `prenomuser` varchar(255) DEFAULT NULL,
  `AdrUser` varchar(255) NOT NULL,
  `EmailUsr` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `Numtel` int(11) NOT NULL,
  `Role` enum('administrateur','Client','Livreur','Employee','Fournisseur') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `nomuser`, `prenomuser`, `AdrUser`, `EmailUsr`, `password`, `Numtel`, `Role`) VALUES
(3, 'Aya', 'Gadhoumi', 'ariana', 'eyagadhoumy@gmail.com', 'xxxxyza', 23000888, 'Client'),
(4, 'Yassmine', 'Hammami', 'Ariana', 'YassmineHammami@gmail.com', 'efyyfyg', 21050449, 'Client'),
(5, 'Taylor ', 'Swift', 'New York', 'TayTay@gmail.com', 'gfytghbkj', 52000341, 'Client'),
(6, 'Rana', 'Bourayou', 'Borj Cedria', 'Rana@gmail.com', 'xxfeddd@e', 25111000, 'Client'),
(7, 'Ayouta', 'Gadhoumi', 'Menzah 7', 'eya.gadhoumi@esprit.tn', 'password', 21000230, 'administrateur'),
(8, 'aziz', 'grissa', 'Petite Ariana', 'mohamedaziz.grissa@esprit.tn', 'mohamedaziz.grissa@esprit.tn', 52000147, 'Client'),
(9, 'Imen', 'Saoud', 'Menzah 6 ', 'imensaoud@yahoo.fr', 'zedhdbhez', 27400994, 'Client');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vueproduit`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vueproduit` (
`NomProduit` varchar(20)
,`PrixP` float
,`QteP` int(11)
,`QteSeuilP` int(11)
,`ImageP` varchar(255)
,`NomCatégorie` varchar(20)
);

-- --------------------------------------------------------

--
-- Structure de la vue `vueproduit`
--
DROP TABLE IF EXISTS `vueproduit`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vueproduit`  AS SELECT `p`.`NomP` AS `NomProduit`, `p`.`PrixP` AS `PrixP`, `p`.`QteP` AS `QteP`, `p`.`QteSeuilP` AS `QteSeuilP`, `p`.`ImageP` AS `ImageP`, `c`.`NomCatégorie` AS `NomCatégorie` FROM (`produit` `p` join `catégorie` `c` on(`p`.`Id_Catégorie` = `c`.`Id_Catégorie`)) ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id_avis`),
  ADD KEY `fk1_avis` (`id_client`),
  ADD KEY `fk2_avis` (`id_produit`);

--
-- Index pour la table `catégorie`
--
ALTER TABLE `catégorie`
  ADD PRIMARY KEY (`Id_Catégorie`),
  ADD UNIQUE KEY `UC_NomCatégorie` (`NomCatégorie`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD UNIQUE KEY `Reference` (`Reference`),
  ADD KEY `fk_id` (`id_user`),
  ADD KEY `fk_id_panier` (`Id_Panier`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id_fournisseur`),
  ADD KEY `Id_Produit` (`Id_Produit`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id_livraison`),
  ADD UNIQUE KEY `Reference` (`Reference`),
  ADD KEY `fk_id_commande` (`id_commande`),
  ADD KEY `fk_id_user` (`id_user`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`idOffre`),
  ADD KEY `fk_produit` (`Id_Produit`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`Id_Panier`),
  ADD KEY `fk_panier_user` (`id_user`),
  ADD KEY `produitFK` (`Id_Produit`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`Id_Produit`),
  ADD UNIQUE KEY `UC_NomP` (`NomP`),
  ADD KEY `fk_categorieP` (`Id_Catégorie`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`),
  ADD KEY `fk_reclamation` (`id_client`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`),
  ADD KEY `fk_reponse` (`id_reclamation`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id_avis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT pour la table `catégorie`
--
ALTER TABLE `catégorie`
  MODIFY `Id_Catégorie` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id_livraison` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `idOffre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `Id_Panier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `Id_Produit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `fk1_avis` FOREIGN KEY (`id_client`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk2_avis` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`Id_Produit`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fk_id` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_panier` FOREIGN KEY (`Id_Panier`) REFERENCES `panier` (`Id_Panier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD CONSTRAINT `fournisseur_ibfk_1` FOREIGN KEY (`Id_Produit`) REFERENCES `produit` (`Id_Produit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `fk_id_commande` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id_commande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ref` FOREIGN KEY (`Reference`) REFERENCES `commande` (`Reference`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `fk_produit` FOREIGN KEY (`Id_Produit`) REFERENCES `produit` (`Id_Produit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `fk_panier_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `produitFK` FOREIGN KEY (`Id_Produit`) REFERENCES `produit` (`Id_Produit`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_categorieP` FOREIGN KEY (`Id_Catégorie`) REFERENCES `catégorie` (`Id_Catégorie`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_reclamation` FOREIGN KEY (`id_client`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fk_reponse` FOREIGN KEY (`id_reclamation`) REFERENCES `reclamation` (`id_reclamation`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
