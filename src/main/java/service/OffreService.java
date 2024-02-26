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
import java.util.Date;
import java.time.LocalDate;
public class OffreService implements IService<Offre>{

    private Connection conn;
    private Statement ste;
    public OffreService() {
        conn= DataSource.getInstance().getCnx();
    }
    public void add(Offre offre){
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
        String requete = "UPDATE offre SET id_Produit = ?, date_debut = ?, date_fin = ?, reduction = ?, titre_offre = ? WHERE idOffre = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, offre.getId_Produit());
            ps.setDate(2, java.sql.Date.valueOf(offre.getDate_debut()));
            ps.setDate(3, java.sql.Date.valueOf(offre.getDate_fin()));
            ps.setString(4, offre.getReduction());
            ps.setString(5, offre.getTitre_Offre());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Offre updated successfully.");
            } else {
                System.out.println("Failed to update Offre. Offre with id " + offre.getIdOffre() + " not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Offre: " + e.getMessage(), e);
        }
    }


    @Override

    public List<Offre> readAll() {
        String requete="select * from offre";
        List<Offre> list=new ArrayList<>();
        try {
            ste= conn.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while (rs.next()){
                list.add(new Offre(rs.getInt(1),rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getDate(4).toLocalDate(),rs.getString(5),rs.getString(6)));

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



    }

