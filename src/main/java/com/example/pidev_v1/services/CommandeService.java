package com.example.pidev_v1.services;




import com.example.pidev_v1.entities.Commande;
import com.example.pidev_v1.entities.Status;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

public class CommandeService implements IService2<Commande>{
    private Connection cnx;

    public CommandeService() {
        this.cnx = MyDataBase.getInstance().getCnx();
    }


    @Override
    public void Add(Commande commande) {
        Random random = new Random();
        int randomReference = random.nextInt(Integer.MAX_VALUE); // Example: generates random integer up to max int value
        commande.setReference(randomReference);

        String requete = "INSERT INTO commande (Reference, Id_Panier, id_user, livrable, Date_com, Status, Prix_total) VALUES (?, ?, ?, ?, ?, ?)";
        // Set current date and time
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.sql.Timestamp sqlDateTime = java.sql.Timestamp.valueOf(now);
        commande.setDate(now); // Use the original LocalDateTime object 'now'
        try (PreparedStatement pre = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1, commande.getReference());
            pre.setInt(2, commande.getId_Panier());
            pre.setInt(3, commande.getIdUser());
            pre.setBoolean(4, commande.isLivrable());
            pre.setTimestamp(5,sqlDateTime );
            pre.setString(6, commande.getStatut().toString());
            pre.setFloat(7,commande.getPrix_total());

            pre.executeUpdate();
            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("New commande ID: " + generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur d'ajout: " + e.getMessage());
        }
    }

    @Override
    public ObservableList<Commande> Afficher() {

        ObservableList<Commande> commandes = FXCollections.observableArrayList();
        String requete = "SELECT * FROM commande";
        try (Statement ste = cnx.createStatement();
             ResultSet rs = ste.executeQuery(requete)) {
            while (rs.next()) {
                LocalDateTime now = LocalDateTime.now();
                Commande commande = new Commande();
                commande.setReference(rs.getInt("Reference"));
                commande.setId_Panier(rs.getInt("Id_Panier"));
                commande.setIdUser(rs.getInt("id_user"));
                commande.setLivrable(rs.getBoolean("livrable"));
                commande.setDate(now); // Use the original LocalDateTime object 'now'
                commande.setStatut(Status.valueOf(rs.getString("Status"))); // Assuming you have Status as Enum
                commande.setPrix_total(rs.getFloat("Prix_total"));
                // Récupérer les informations de l'utilisateur associé à cette commande
                User user = getUserById(commande.getIdUser()); // Implémentez cette méthode pour récupérer l'utilisateur par son ID

                // Définir l'utilisateur associé à la commande
                commande.setUser(user);

                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'affichage: " + e.getMessage());
        }
        return commandes;
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();}
    @Override
    public void Delete(Commande commande) {
        String requete = "DELETE FROM commande WHERE Reference = ?";

        try (PreparedStatement pre = cnx.prepareStatement(requete)) {
            pre.setInt(1, commande.getReference());
            int affectedRows = pre.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Commande supprimée avec succès !");
            } else {
                System.out.println("Aucune commande trouvée avec la réference spécifiée.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la commande: " + e.getMessage());
            // Gérer l'exception ici ou remonter l'exception pour la gérer ailleurs si nécessaire
        }
    }



    @Override
    public void Modify(Commande commande, String newStatus) throws Exception {
        // Check if the status is valid
        if (Status.valueOf(newStatus) != null) {
            String requete = "UPDATE commande SET Status=? WHERE Reference=?";
            try (PreparedStatement pre = cnx.prepareStatement(requete)) {
                pre.setObject(1, newStatus);
                pre.setInt(2, commande.getReference());
                int rowsAffected = pre.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Commande modifiée avec succès !");
                } else {
                    System.out.println("Aucune modification apportée à la commande.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de la modification: " + e.getMessage());
            }
        } else {
            System.out.println("Modification not allowed. Invalid status.");
        }
    }
    @Override
    public void DeleteCommandebyRef(int Reference) {
        // Implement if needed, similar to Delete method but using Reference
    }

    public double getTotalPanier(int Id_Panier) throws SQLException {
        double totalPanier = 0.0;
        String query = "SELECT totalPanier FROM panier WHERE Id_Panier = ?";

        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, Id_Panier);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalPanier = resultSet.getDouble("totalPanier");
                }
            }
        }

        return totalPanier; // Retourne la valeur calculée du total du panier
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

    public int CountType(String type) {
        String requete = "SELECT COUNT(Status) FROM commande WHERE Status = ?";
        int nbtype = 0;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nbtype = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nbtype;
    }
}

