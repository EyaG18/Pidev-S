package services;

import entities.User;
import utilties.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements CRUD<User> {
    public Connection conx;
    public Statement stm;

    public UserService() {
        conx = DataSource.getInstance().getConx();

    }

    @Override
    public void add(User p) {
        String req = "INSERT INTO `user`(`nomuser`, `prenomuser`, `AdrUser`, `EmailUsr`, `password`, `Numtel`, `Role`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conx.prepareStatement(req);
            pstmt.setString(1, p.getNomuser());
            pstmt.setString(2, p.getPrenomuser());
            pstmt.setString(3, p.getAdrUser());
            pstmt.setString(4, p.getEmailUsr());
            pstmt.setString(5, p.getPassword());
            pstmt.setInt(6, p.getNumtel());
            pstmt.setString(7, p.getRole());
            pstmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        String req = "UPDATE `user` SET `nomuser`=?, `prenomuser`=?, `AdrUser`=?, `EmailUsr`=?, `password`=?, `Numtel`=?, `Role`=? WHERE `id_user`=?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(req);
            pstmt.setString(1, entity.getNomuser());
            pstmt.setString(2, entity.getPrenomuser());
            pstmt.setString(3, entity.getAdrUser());
            pstmt.setString(4, entity.getEmailUsr());
            pstmt.setString(5, entity.getEmailUsr());
            pstmt.setInt(6, entity.getNumtel());
            pstmt.setString(7, entity.getRole());
            pstmt.setInt(8, entity.getId_user());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Utilisateur mis à jour avec succès");
            } else {
                System.out.println("Aucun utilisateur n'a été mis à jour");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remove(User entity) {
        String req = "DELETE FROM `user` WHERE `id_user`=?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(req);
            pstmt.setInt(1, entity.getId_user());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Utilisateur supprimé avec succès");
            } else {
                System.out.println("Aucun utilisateur n'a été supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficher() {
        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        try {
            stm = conx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String nomuser = rs.getString("nomuser");
                String prenomuser = rs.getString("prenomuser");
                String adrUser = rs.getString("AdrUser");
                String emailUsr = rs.getString("EmailUsr");
                String password = rs.getString("password");
                int numtel = rs.getInt("Numtel");
                String role = rs.getString("Role");
                users.add(new User(id_user, nomuser, prenomuser, adrUser, emailUsr,password, numtel, role));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
}
