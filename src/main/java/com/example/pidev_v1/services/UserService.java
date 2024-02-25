package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.RoleUser;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.tools.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements CRUD<User >{

    Connection cnx= null;
    Statement statement= null;
    private PreparedStatement ste;
    MyDataBase connect = new MyDataBase();

    public UserService() {
        cnx = connect.getCnx();
    }

    @Override
    public void add(User p) {
        String req = "INSERT INTO `user`(`nomuser`, `prenomuser`, `AdrUser`, `EmailUsr`, `password`, `Numtel`, `Role`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, p.getNomuser());
            pstmt.setString(2, p.getPrenomuser());
            pstmt.setString(3, p.getAdrUser());
            pstmt.setString(4, p.getEmailUsr());
            pstmt.setString(5, p.getPassword());
            pstmt.setInt(6, p.getNumtel());
            pstmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void remove(User p) {
        String req = "DELETE FROM `user` WHERE `id_user`=?";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, p.getId_user());
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
    public void update(User p) {
        String req = "UPDATE `user` SET `nomuser`=?, `prenomuser`=?, `AdrUser`=?, `EmailUsr`=?, `password`=?, `Numtel`=?, `Role`=? WHERE `id_user`=?";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, p.getNomuser());
            pstmt.setString(2, p.getPrenomuser());
            pstmt.setString(3, p.getAdrUser());
            pstmt.setString(4, p.getEmailUsr());
            pstmt.setString(5, p.getEmailUsr());
            pstmt.setInt(6,p.getNumtel());
            pstmt.setInt(8, p.getId_user());
            pstmt.setString(8, String.valueOf(p.getRole()));
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
    public List<User> afficher() {
        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        try {
            statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String nomuser = rs.getString("nomuser");
                String prenomuser = rs.getString("prenomuser");
                String adrUser = rs.getString("AdrUser");
                String emailUsr = rs.getString("EmailUsr");
                String password = rs.getString("password");
                int numtel = rs.getInt("Numtel");
                String roleStr = rs.getString("Role"); // Récupérer le rôle sous forme de chaîne depuis la base de données
                RoleUser role = null;
                try {
                    role = RoleUser.valueOf(roleStr); // Convertir la chaîne en une instance de l'énumération RoleUser
                } catch (IllegalArgumentException e) {
                    // Gérer le cas où la chaîne ne correspond à aucun des noms d'énumération définis
                    System.err.println("La chaîne de rôle récupérée de la base de données n'est pas valide: " + roleStr);
                    e.printStackTrace(); // Afficher la trace de la pile pour le débogage
                }
                users.add(new User(nomuser, prenomuser, adrUser, emailUsr, password, numtel, role)); // Créer un nouvel utilisateur avec le rôle converti

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public User RecuperUserById(int idu, List<User> users) {
        for (User user : users) {
            if (user.getId_user() == idu) {
                return user;
            }
        }
        return null; // Re
    }


}
