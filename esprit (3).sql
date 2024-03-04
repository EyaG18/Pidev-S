-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 04 mars 2024 à 02:57
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `esprit`
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
  `note` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `catégorie`
--

CREATE TABLE `catégorie` (
  `Id_Catégorie` int(20) NOT NULL,
  `NomCatégorie` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `Id_Panier` int(11) NOT NULL,
  `id_user` int(255) NOT NULL,
  `status_com` enum('en attente','traiter','annuler','') NOT NULL,
  `Date_com` date NOT NULL,
  `livrable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `id_livraison` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `Status_livraison` enum('en attente','traiter','annuler','') NOT NULL,
  `date_livraison` date NOT NULL,
  `prix_livraison` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `id_offre` int(11) NOT NULL,
  `Id_Catégorie` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `reduction` varchar(255) NOT NULL,
  `titre_offre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `Id_Panier` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `panier_produit`
--

CREATE TABLE `panier_produit` (
  `Id_Panier` int(11) NOT NULL,
  `Id_Produit` int(11) NOT NULL,
  `quantite_panier` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `publicite`
--

CREATE TABLE `publicite` (
  `id_publicite` int(11) NOT NULL,
  `id_offre` int(11) NOT NULL,
  `titre_pub` varchar(255) NOT NULL,
  `cout_pub` varchar(255) NOT NULL,
  `media` varchar(255) NOT NULL,
  `date_pub` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation_reponse`
--

CREATE TABLE `reclamation_reponse` (
  `id_reponse` int(11) NOT NULL,
  `id_reclamation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `reponse` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `Role` enum('administrateur','Client','Livreur','Employee') DEFAULT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `nomuser`, `prenomuser`, `AdrUser`, `EmailUsr`, `password`, `Numtel`, `Role`, `image`) VALUES
(4, 'aziz', 'grissa', 'Tunis', 'azizowski10@gmail.com', '7fc8c9f03572ec5c3330b8a401d6923cb8c392e1932d3002c7bbc1bab66a0470', 12345678, 'administrateur', 'grissa.jpg'),
(5, 'Aziz', 'Grissa', 'addresse', 'mohamedaziz.grissa@esprit.tn', '123456', 18759642, 'administrateur', 'lasmer.jpg'),
(8, 'test', 'test', 'test', 'test@gmail.com', 'password', 12345678, 'Client', 'lasmer.jpg'),
(9, 'Mohamed', 'Aziz Grissa', 'Tunis', 'azizowski_sanchez@yahoo.fr', 'azizowski_sanchez@yahoo.fr', 12345678, 'Employee', 'lasmer.jpg'),
(10, 'dtcfyvg', 'ubhinj', 'zafafa', 'azfza@gmail.com', '6e373f1c31ffed6ff012c2c04dfe3a9a60541f2fc62b2026e9921679acdc9eb4', 12345678, 'administrateur', 'lasmer.jpg'),
(11, 'Mohamed', 'Aziz Grissa B', 'Tunis', 'azizgrissaaaa@gmail.com', '6e4f4793ce55c8718f220599e10648935cc2714b83b934ec240182b365eb0b14', 12345678, 'Client', 'lasmer.jpg'),
(12, 'test', 'aziz', 'Tunis', 'testaziz@gmail.com', '5329aa5797a3a38f50327b56527d60596f1e00da6f09c2fce09e53ccf129b128', 12345678, 'administrateur', '426758643_949328800110571_2144119959227790316_n.jpg');

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
  ADD PRIMARY KEY (`Id_Catégorie`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `fk_id` (`id_user`),
  ADD KEY `fk_id_panier` (`Id_Panier`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id_livraison`),
  ADD KEY `fk_id_commande` (`id_commande`),
  ADD KEY `fk_id_user` (`id_user`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`id_offre`),
  ADD KEY `fk_id_categorie` (`Id_Catégorie`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`Id_Panier`),
  ADD KEY `fk_panier_user` (`id_user`);

--
-- Index pour la table `panier_produit`
--
ALTER TABLE `panier_produit`
  ADD PRIMARY KEY (`Id_Panier`,`Id_Produit`),
  ADD KEY `fk_produitPanier` (`Id_Produit`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`Id_Produit`),
  ADD KEY `fk_categorieP` (`Id_Catégorie`);

--
-- Index pour la table `publicite`
--
ALTER TABLE `publicite`
  ADD PRIMARY KEY (`id_publicite`),
  ADD KEY `fk_id_offre` (`id_offre`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`),
  ADD KEY `fk_reclamation` (`id_client`);

--
-- Index pour la table `reclamation_reponse`
--
ALTER TABLE `reclamation_reponse`
  ADD PRIMARY KEY (`id_reponse`),
  ADD KEY `fk1` (`id_reclamation`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`);

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
  MODIFY `id_avis` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `catégorie`
--
ALTER TABLE `catégorie`
  MODIFY `Id_Catégorie` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id_livraison` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `Id_Panier` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `Id_Produit` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `publicite`
--
ALTER TABLE `publicite`
  MODIFY `id_publicite` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id_user` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

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
  ADD CONSTRAINT `fk_id` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `fk_id_panier` FOREIGN KEY (`Id_Panier`) REFERENCES `panier` (`Id_Panier`);

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `fk_id_commande` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id_commande`),
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `fk_id_categorie` FOREIGN KEY (`Id_Catégorie`) REFERENCES `catégorie` (`Id_Catégorie`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `fk_panier_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `panier_produit`
--
ALTER TABLE `panier_produit`
  ADD CONSTRAINT `fk_Panier_IDD` FOREIGN KEY (`Id_Panier`) REFERENCES `panier` (`Id_Panier`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_produitPanier` FOREIGN KEY (`Id_Produit`) REFERENCES `produit` (`Id_Produit`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_categorieP` FOREIGN KEY (`Id_Catégorie`) REFERENCES `catégorie` (`Id_Catégorie`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `publicite`
--
ALTER TABLE `publicite`
  ADD CONSTRAINT `fk_id_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`id_offre`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_reclamation` FOREIGN KEY (`id_client`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `reclamation_reponse`
--
ALTER TABLE `reclamation_reponse`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`id_reclamation`) REFERENCES `reclamation` (`id_reclamation`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`id_reponse`) REFERENCES `reponse` (`id_reponse`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
