package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Reclamation;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ReclamationService implements Services <Reclamation>  {

    Connection cnx;
    Statement statement= null;
    PreparedStatement ste;
    MyDataBase connect = new MyDataBase();
    public ReclamationService() {
        Connection cnx= null;
        Statement statement= null;
        PreparedStatement ste;
        MyDataBase connect = new MyDataBase();


    }

    @Override
    public void add(Reclamation reclamation) {
        String requete="INSERT into reclamation (id_client,description,statu_reclamation,type_reclamation) VALUES(?,?,?,?)";

        try {
            ste= cnx.prepareStatement(requete);
            ste.setInt(1,reclamation.getID_client());
            ste.setString(2,reclamation.getDescription());
            ste.setString(3, reclamation.getStatu_reclamation());
            ste.setString(4,reclamation.getType_reclamation());
            ste.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reclamation reclamation) {
        String requete="UPDATE reclamation SET description=?,statu_reclamation=?,type_reclamation=? where id_reclamation=?";
        try {
            ste=cnx.prepareStatement(requete);
            ste.setString(1,reclamation.getDescription());
            ste.setString(2,reclamation.getStatu_reclamation());
            ste.setString(3,reclamation.getType_reclamation());
            ste.setInt(4,reclamation.getID_reclamation());
            ste.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Reclamation reclamation) {
        String requete="DELETE FROM reclamation where id_reclamation=?";
        try {
            ste=cnx.prepareStatement(requete);
            ste.setInt(1,reclamation.getID_reclamation());
            ste.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updatepersonalise(Reclamation R){
        String requete="UPDATE reclamation SET statu_reclamation='traité' where id_reclamation=?";
        try {
            ste=cnx.prepareStatement(requete);
            ste.setInt(1,R.getID_reclamation());
            ste.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Reclamation readById(int id) {
        String requete="SELECT user.nomuser, description, date_reclamation, statu_reclamation, type_reclamation FROM reclamation JOIN user ON reclamation.id_client = user.id_user WHERE id_reclamation='"+id+"'";
        Reclamation rec=null;
        try {
            ste= cnx.prepareStatement(requete);
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


    @Override

    public ObservableList<Reclamation> readAll() {

        ObservableList<Reclamation> list = FXCollections.observableArrayList();

        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();

        String requete1="SELECT user.nomuser, description, date_reclamation, statu_reclamation, type_reclamation FROM reclamation JOIN user ON reclamation.id_client = user.id_user";
        //List<Reclamation> list =new ArrayList<>();
        Statement st;
        ResultSet rs;
        try {

            st = cnx.createStatement();
            rs = st.executeQuery(requete1);
            //ste= (PreparedStatement)cnx.createStatement();
            //ResultSet rs= ste.executeQuery(requete1);
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








}
