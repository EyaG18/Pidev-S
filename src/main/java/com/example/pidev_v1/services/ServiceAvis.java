package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Avis;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ServiceAvis implements Services<Avis>{
    Connection cnx= null;
    Statement statement= null;
    PreparedStatement ste;
    MyDataBase connect = new MyDataBase();
    public ServiceAvis() {
        Connection cnx= null;
        Statement statement= null;
        PreparedStatement ste;
        MyDataBase connect = new MyDataBase();

    }

    @Override
    public void add(Avis avis) {
        String requete="INSERT into avis (id_client,id_produit,commentaire,note) VALUES(?,?,?,?)";

        try {
            Connection cnx;
            cnx = null;
            PreparedStatement ste = cnx.prepareStatement(requete);
            ste.setInt(1,avis.getID_client());
            ste.setInt(2,avis.getID_produit());
            ste.setString(3,avis.getCommentaire());
            ste.setInt(4,avis.getNote());

            ste.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Avis readById(int id) {
        String requete="SELECT user.nomuser,produit.NomP, commentaire, note FROM avis JOIN user ON avis.id_client = user.id_user JOIN produit ON avis.id_produit = produit.Id_Produit WHERE id_avis='"+id+"'";
        Avis avis=null;
        try {
            ste= (PreparedStatement) cnx.createStatement();
            ResultSet rs= ste.executeQuery(requete);

            if(rs.next())
            {
                avis= new Avis(rs.getString("nomuser"),rs.getString("NomP"),
                        rs.getString("commentaire"),rs.getInt("note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return avis;
    }

    @Override
    public void update(Avis avis) {
        String requete="UPDATE avis SET commentaire=?,note=? where id_avis=?";
        try {
            ste=cnx.prepareStatement(requete);
            ste.setString(1,avis.getCommentaire());
            ste.setInt(2,avis.getNote());
            ste.setInt(3,avis.getID_avis());
            ste.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Avis avis) {
        String requete="DELETE FROM avis where commentaire=? AND note=?";
        try {
            ste=cnx.prepareStatement(requete);
            ste.setString(1,avis.getCommentaire());
            ste.setInt(2,avis.getNote());
            ste.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Avis> readAll() {

        ObservableList<Avis> list = FXCollections.observableArrayList();

        String requete1="SELECT user.nomuser,produit.NomP,commentaire, note FROM avis JOIN user ON avis.id_client = user.id_user JOIN produit ON avis.id_produit = produit.Id_Produit ";
        //List<Reclamation> list =new ArrayList<>();
        try {
            ste= (PreparedStatement) cnx.createStatement();
            ResultSet rs= ste.executeQuery(requete1);
            while(rs.next()){
                list.add(new Avis(rs.getString("nomuser"),rs.getString("NomP"),rs.getString("commentaire"),rs.getInt("note")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public int repurerID_produit(String st){
        String requete="SELECT Id_Produit FROM produit WHERE NomP='"+st+"'";
        int rep=0;
        try {
            ste= (PreparedStatement) cnx.createStatement();
            ResultSet rs= ste.executeQuery(requete);

            if(rs.next())
            {
                rep= rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rep;
    }
    public int repurerID_avis(Integer idp ,Integer idc){
        String requete="SELECT id_avis FROM avis WHERE id_produit= '"+idp+"' AND id_client='"+idc+"'";
        int rep=0;
        try {
            ste= (PreparedStatement) cnx.createStatement();
            ResultSet rs= ste.executeQuery(requete);
            if(rs.next())
            {
                rep= rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rep;
    }








}
