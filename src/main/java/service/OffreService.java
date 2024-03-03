package service;

import entities.Offre;
import utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class OffreService implements IService<Offre> {

    private Connection conn;
    private Statement ste;

    public OffreService() {
        conn = DataSource.getInstance().getCnx();
    }

    public void add(Offre offre) {
        try {
            // Prepare the insert query
            String query = "INSERT INTO Offre (Id_Produit, date_debut, date_fin, reduction, titre_offre) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);

            // Set parameters
            statement.setInt(1, offre.getId_Produit());
            statement.setDate(2, java.sql.Date.valueOf(offre.getDate_debut()));
            statement.setDate(3, java.sql.Date.valueOf(offre.getDate_fin()));
            statement.setString(4, offre.getReduction());
            statement.setString(5, offre.getTitre_Offre());

            // Execute the query
            statement.executeUpdate();

            // Commit the transaction
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }


    @Override
    public void delete(Offre offre) {
        String requete = "DELETE FROM offre WHERE id_Produit = ? AND date_debut = ? AND date_fin = ? AND reduction = ? AND titre_offre = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, offre.getId_Produit());
            ps.setDate(2, java.sql.Date.valueOf(offre.getDate_debut()));
            ps.setDate(3, java.sql.Date.valueOf(offre.getDate_fin()));
            ps.setString(4, offre.getReduction());
            ps.setString(5, offre.getTitre_Offre());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Offre deleted successfully.");
            } else {
                System.out.println("Failed to delete Offre. Offre not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Offre: " + e.getMessage(), e);
        }
    }


    @Override
    public void update(Offre offre) {
        String requete = "UPDATE offre SET id_Produit = ?, date_debut = ?, date_fin = ?, reduction = ? WHERE titre_offre = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, offre.getId_Produit());
            ps.setDate(2, java.sql.Date.valueOf(offre.getDate_debut()));
            ps.setDate(3, java.sql.Date.valueOf(offre.getDate_fin()));
            ps.setString(4, offre.getReduction());
            ps.setString(5, offre.getTitre_Offre());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Offres with titre_offre " + offre.getTitre_Offre() + " updated successfully.");
            } else {
                System.out.println("Failed to update Offres. No Offre found with titre_offre " + offre.getTitre_Offre());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Offre: " + e.getMessage(), e);
        }
    }


    @Override

    public List<Offre> readAll() {
        String requete = "select * from offre";
        List<Offre> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Offre(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Offre readById(int idOffre) {

        String requete = "select * from offre where idOffre = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, idOffre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Offre(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6));
            } else {
                return null; // No Offre found with the given idOffre
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updateOffreByTitreOffre(String oldTitreOffre, String newTitreOffre, LocalDate newDateDebut, LocalDate newDateFin, String newReduction, int newIdProduit) {
        String sql = "UPDATE offre SET titre_offre = ?, date_debut = ?, date_fin = ?, reduction = ?, id_Produit = ? WHERE titre_offre = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newTitreOffre);
            preparedStatement.setDate(2, java.sql.Date.valueOf(newDateDebut));
            preparedStatement.setDate(3, java.sql.Date.valueOf(newDateFin));
            preparedStatement.setString(4, newReduction);
            preparedStatement.setInt(5, newIdProduit);
            preparedStatement.setString(6, oldTitreOffre);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Offre updated successfully!");
            } else {
                System.out.println("Failed to update Offre. Offre with titre_offre " + oldTitreOffre + " not found.");
            }
        } catch (SQLException e) {
            // Handle the exception appropriately in your application
            e.printStackTrace();
        }
    }


    public String getProduitImageById(int Id_Produit) {
        String imagePath = null;
        String query = "SELECT ImageP FROM produit WHERE Id_Produit = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Id_Produit);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                imagePath = rs.getString("ImageP");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }

        return imagePath;
    }


    @Override
    public float getPrixAfterReduction(int idProduit) {
        // Fetch the original price from the Produit table using the idProduit
        String produitQuery = "SELECT PrixP FROM produit WHERE Id_Produit = ?";
        try (PreparedStatement produitStmt = conn.prepareStatement(produitQuery)) {
            produitStmt.setInt(1, idProduit);
            ResultSet produitRs = produitStmt.executeQuery();
            if (produitRs.next()) {
                float originalPrice = produitRs.getFloat("PrixP");

                // Fetch the reduction from the Offre table for the given idProduit
                String reductionQuery = "SELECT reduction FROM offre WHERE Id_Produit = ?";
                try (PreparedStatement reductionStmt = conn.prepareStatement(reductionQuery)) {
                    reductionStmt.setInt(1, idProduit);
                    ResultSet reductionRs = reductionStmt.executeQuery();
                    if (reductionRs.next()) {
                        float reductionPercentage = reductionRs.getFloat("reduction");

                        // Calculate the discounted price after the reduction
                        float reductionAmount = originalPrice * reductionPercentage / 100;
                        return originalPrice - reductionAmount; // Apply reduction amount
                    } else {
                        return originalPrice; // No reduction found, return the original price
                    }
                }
            } else {
                return -1; // Indicates produit not found or error fetching price
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            return -1; // Indicates an error


        }
    }
}
