package com.example.pidev_v1.services;



import com.example.pidev_v1.entities.Reclamation;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService implements Services <Reclamation> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    public ReclamationService(){conn= MyDataBase.getInstance().getCnx();}



    public void add(Reclamation R)
    {
        String requete="INSERT into reclamation (id_client,description,type_reclamation) VALUES(?,?,?)";

        try {
            pst=conn.prepareStatement(requete);
            pst.setInt(1,R.getID_client());
            pst.setString(2,R.getDescription());
            pst.setString(3,R.getType_reclamation());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Reclamation R)
    {
        String requete="DELETE FROM reclamation where id_reclamation=?";
        try {
            pst=conn.prepareStatement(requete);
            pst.setInt(1,R.getID_reclamation());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Reclamation R)
    {
        String requete="UPDATE reclamation SET statu_reclamation='traité' where id_reclamation=?";
        try {
            pst=conn.prepareStatement(requete);
            pst.setInt(1,R.getID_reclamation());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // @Override

    public ObservableList<Reclamation> readAll() {

        ObservableList<Reclamation> list = FXCollections.observableArrayList();



        String requete1="SELECT user.nomuser, description, date_reclamation, statu_reclamation, type_reclamation FROM reclamation JOIN user ON reclamation.id_client = user.id_user";
        //List<Reclamation> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            ResultSet rs= ste.executeQuery(requete1);
            while(rs.next()){
                list.add(new Reclamation(rs.getString("nomuser"),
                        rs.getString("description"),rs.getDate("date_reclamation"),
                        rs.getString("statu_reclamation"),rs.getString("type_reclamation")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public ObservableList<Reclamation> readAll(int id) {

        ObservableList<Reclamation> list = FXCollections.observableArrayList();



        String requete1="SELECT user.nomuser, description, date_reclamation, statu_reclamation, type_reclamation FROM reclamation JOIN user ON reclamation.id_client = user.id_user where id_client='"+id+"'";
        //List<Reclamation> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            ResultSet rs= ste.executeQuery(requete1);
            while(rs.next()){
                list.add(new Reclamation(rs.getString("nomuser"),
                        rs.getString("description"),rs.getDate("date_reclamation"),
                        rs.getString("statu_reclamation"),rs.getString("type_reclamation")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public Reclamation readById(int id)
    {
        String requete="SELECT user.nomuser, description, date_reclamation, statu_reclamation, type_reclamation FROM reclamation JOIN user ON reclamation.id_client = user.id_user WHERE id_reclamation='"+id+"'";
        Reclamation rec=null;
        try {
            ste= conn.createStatement();
            ResultSet rs= ste.executeQuery(requete);

            if(rs.next())
            {
                rec= new Reclamation(rs.getString("nomuser"),
                        rs.getString("description"), rs.getDate("date_reclamation"),
                        rs.getString("statu_reclamation"),rs.getString("type_reclamation"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rec;
    }

    public ObservableList<Reclamation> readAlltraite(int id) {

        ObservableList<Reclamation> list = FXCollections.observableArrayList();



        String requete1="SELECT user.nomuser, description, date_reclamation, statu_reclamation, type_reclamation FROM reclamation JOIN user ON reclamation.id_client = user.id_user where id_client='"+id+"' AND statu_reclamation='traité'" ;
        //List<Reclamation> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            ResultSet rs= ste.executeQuery(requete1);
            while(rs.next()){
                list.add(new Reclamation(rs.getString("nomuser"),
                        rs.getString("description"),rs.getDate("date_reclamation"),
                        rs.getString("statu_reclamation"),rs.getString("type_reclamation")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ObservableList<Reclamation> readAllNontraite(int id) {

        ObservableList<Reclamation> list = FXCollections.observableArrayList();



        String requete1="SELECT user.nomuser, description, date_reclamation, statu_reclamation, type_reclamation FROM reclamation JOIN user ON reclamation.id_client = user.id_user where id_client='"+id+"' AND statu_reclamation='en attente'" ;
        //List<Reclamation> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            ResultSet rs= ste.executeQuery(requete1);
            while(rs.next()){
                list.add(new Reclamation(rs.getString("nomuser"),
                        rs.getString("description"),rs.getDate("date_reclamation"),
                        rs.getString("statu_reclamation"),rs.getString("type_reclamation")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public int CountType(String type) {
        String requete = "SELECT COUNT(type_reclamation) FROM reclamation WHERE type_reclamation = ?";
        int nbtype = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
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
    public int Count() {
        String requete = "SELECT COUNT(id_reclamation) FROM reclamation WHERE statu_reclamation=?";
        int nb= 0;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, "traité");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nb = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nb;
    }

}