package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Reponse;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ReponseServices implements Services<Reponse>{

    Connection cnx;
    Statement statement= null;
    PreparedStatement ste;
    MyDataBase connect = new MyDataBase();

    public ReponseServices() {
        Connection cnx= null;
        Statement statement= null;
        PreparedStatement ste;
        MyDataBase connect = new MyDataBase();
    }

    @Override
    public void add(Reponse reponse) {
        String requete="INSERT into reponse (id_reclamation,reponse) VALUES(?,?)";

        try {
            statement=cnx.prepareStatement(requete);
            ste.setInt(1,reponse.getID_reclamation());
            ste.setString(2,reponse.getReponse());

            ste.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reponse readById(int id) {

            String requete="SELECT reponse, date_reponse FROM reponse  WHERE id_reponse='"+id+"'";
            Reponse rep=null;
            try {
                ste= (PreparedStatement) cnx.createStatement();
                ResultSet rs= ste.executeQuery(requete);

                if(rs.next())
                {
                    rep= new Reponse(rs.getString("reponse"),
                            rs.getDate("date_reponse"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return rep;
    }

    @Override
    public void update(Reponse reponse) {
        String requete="UPDATE reponse SET reponse=? where id_reponse=?";
        try {
            ste=cnx.prepareStatement(requete);
            ste.setString(1,reponse.getReponse());
            ste.setInt(2,reponse.getID_reponse());
            ste.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Reponse reponse) {
        String requete="DELETE FROM reponse where reponse=?";
        try {
            ste=cnx.prepareStatement(requete);
            ste.setString(1,reponse.getReponse());
            ste.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int repurerID_reclamation(String st){
        String requete="SELECT id_reclamation FROM reclamation WHERE description='"+st+"'";
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



    public ObservableList<Reponse> readAll() {

        ObservableList<Reponse> list = FXCollections.observableArrayList();
        MyDataBase db = new MyDataBase();
        Connection cnx = db.getCnx();
        String requete1="SELECT reponse, date_reponse FROM reponse";
        Statement st;
        ResultSet rs;

        //List<Reclamation> list =new ArrayList<>();
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(requete1);
            while(rs.next()){
                list.add(new Reponse(rs.getString("reponse"),rs.getDate("date_reponse")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }










}
