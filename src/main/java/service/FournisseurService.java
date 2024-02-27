package service;

import entities.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FournisseurService implements IService<Fournisseur> {

    private Connection conn;
    private Statement ste;

    public FournisseurService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void add(Fournisseur fournisseur) {
        String requete = "INSERT INTO fournisseur(Id_Produit, nom_fournisseur, num_fournisseur, adresse_fournisseur) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, fournisseur.getId_Produit());
            ps.setString(2, fournisseur.getNom_fournisseur());
            ps.setString(3, fournisseur.getNum_fournisseur());
            ps.setString(4, fournisseur.getAdresse_fournisseur());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Fournisseur fournisseur) {
        String requete = "DELETE FROM fournisseur WHERE id_fournisseur = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, fournisseur.getId_fournisseur());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Fournisseur deleted successfully.");
            } else {
                System.out.println("Failed to delete Fournisseur. Fournisseur not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Fournisseur: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Fournisseur fournisseur) {
        String requete = "UPDATE fournisseur SET Id_Produit = ?, nom_fournisseur = ?, num_fournisseur = ?, adresse_fournisseur = ? WHERE id_fournisseur = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, fournisseur.getId_Produit());
            ps.setString(2, fournisseur.getNom_fournisseur());
            ps.setString(3, fournisseur.getNum_fournisseur());
            ps.setString(4, fournisseur.getAdresse_fournisseur());
            ps.setInt(5, fournisseur.getId_fournisseur());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Fournisseur updated successfully.");
            } else {
                System.out.println("Failed to update Fournisseur. Fournisseur with id " + fournisseur.getId_fournisseur() + " not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Fournisseur: " + e.getMessage(), e);
        }
    }

    @Override
    public ObservableList<Fournisseur> readAll() {
        ObservableList<Fournisseur>  list= FXCollections.observableArrayList();
        String requete = "SELECT * FROM fournisseur";
        //List<Fournisseur> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Fournisseur(rs.getInt("id_fournisseur"), rs.getInt("Id_Produit"), rs.getString("nom_fournisseur"), rs.getString("num_fournisseur"), rs.getString("adresse_fournisseur")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Fournisseur readById(int idfournisseur) {
        String requete = "SELECT * FROM Fournisseur WHERE id_fournisseur = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, idfournisseur);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Fournisseur(rs.getInt("id_fournisseur"), rs.getInt("IdProduit"), rs.getString("nom_fournisseur"), rs.getString("num_fournisseur"), rs.getString("adresse_fournisseur"));
            } else {
                return null; // No Fournisseur found with the given idFournisseur
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOffreByTitreOffre(String oldTitreOffre, String newTitreOffre, LocalDate newDateDebut, LocalDate newDateFin, String newReduction, int newIdProduit) {

    }


}
