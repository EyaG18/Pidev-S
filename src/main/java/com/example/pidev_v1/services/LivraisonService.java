package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Livraison;
import com.example.pidev_v1.entities.Status;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static com.example.pidev_v1.entities.Status.*;

public class LivraisonService implements IServiceLivraison<Livraison> {
    Connection cnx;

    public LivraisonService() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void Add(Livraison livraison) {
        String requete = "INSERT INTO livraison (Reference, id_commande, AdrUsr, id_user, Status_livraison, date_livraison, prix_livraison) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Use LocalDate to represent only the date without time
        livraison.setDate(LocalDate.now());

        // Generate a random reference
        int reference = generateRandomReference();

        try (PreparedStatement pre = cnx.prepareStatement(requete)) {
            pre.setInt(1, reference);
            pre.setInt(2, livraison.getCommande().getId_commande());
            pre.setString(3, livraison.getUser().getAdrUser());
            pre.setInt(4, livraison.getId_user());
            pre.setString(5, livraison.getStatus().toString());
            pre.setDate(6, Date.valueOf(livraison.getDate())); // Convert LocalDate to SQL Date
            pre.setFloat(7, livraison.getPrix_livraison());

            pre.executeUpdate();
            System.out.println("Livraison ajoutée avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout d'une nouvelle livraison", e);
        }
    }

    // Method to generate a random reference
    private int generateRandomReference() {
        Random random = new Random();
        return random.nextInt(1000); // Generates a random integer between 0 and 999
    }

    @Override
    public ObservableList<Livraison> Afficher() {
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        String requete = "SELECT * FROM livraison";

        try (Statement ste = cnx.createStatement();
             ResultSet rs = ste.executeQuery(requete)) {
            while (rs.next()) {
                Livraison l = new Livraison();
                l.setReference(rs.getInt("Reference"));
                l.setId_user(rs.getInt("id_user"));
                l.setAdrUser(rs.getString("AdrUser"));
                l.setStatus(valueOf(rs.getString("Status_livraison"))); // Assuming you have Status as Enum

               // l.setStatus(Status.valueOf(rs.getString("Status_livraison"))); // Assuming you have Status as Enum
                l.setDate_livraison(rs.getDate("date_livraison").toLocalDate());
                livraisons.add(l);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage: " + e.getMessage());
        }
        return livraisons;
    }

    @Override
    public void Delete(Livraison livraison) {
        String requete = "DELETE FROM Livraison WHERE id_livraison=?";
        try (PreparedStatement pre = cnx.prepareStatement(requete)) {
            pre.setInt(1, livraison.getId_livraison());
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la livraison", e);
        }
    }

    @Override
    public void Modify(Livraison livraison, String newStatus) throws Exception {
        // Check if the status is valid
        if (valueOf(newStatus) != null) {
            String requete = "UPDATE livraison SET status_livraison=? WHERE id_livraison=? ";
            try (PreparedStatement pre = cnx.prepareStatement(requete)) {
                pre.setString(1, livraison.getStatus().toString()); // Set the status_livraison column to the new status
                pre.setInt(2, livraison.getId_livraison());
                int rowsAffected = pre.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Commande modifiée avec succès !");
                } else {
                    System.out.println("Aucune modification apportée à la commande.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la modification: " + e.getMessage());
            }
        } else {
            System.out.println("Modification not allowed. Invalid status.");
        }
    }
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE id_user = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId_user(resultSet.getInt("id_user"));
                user.setNomuser(resultSet.getString("nomuser"));
                // Vous pouvez récupérer d'autres attributs de l'utilisateur ici
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
