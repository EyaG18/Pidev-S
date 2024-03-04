package com.example.pidev_v1.services;

import com.example.pidev_v1.entities.Reponse;
import com.example.pidev_v1.tools.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ReponseServices implements Services<Reponse>{

        private Connection conn;
        private Statement ste;
        private PreparedStatement pst;
        public ReponseServices(){conn= MyDataBase.getInstance().getCnx();}

        public void add(Reponse R)
        {
            String requete="INSERT into reponse (id_reclamation,reponse) VALUES(?,?)";

            try {
                pst=conn.prepareStatement(requete);
                pst.setInt(1,R.getID_reclamation());
                pst.setString(2,R.getReponse());

                pst.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void delete(Reponse R)
        {
            String requete="DELETE FROM reponse where reponse=?";
            try {
                pst=conn.prepareStatement(requete);
                pst.setString(1,R.getReponse());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void update(Reponse R)
        {
            String requete="UPDATE reponse SET reponse=? where id_reponse=?";
            try {
                pst=conn.prepareStatement(requete);
                pst.setString(1,R.getReponse());
                pst.setInt(2,R.getID_reponse());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public ObservableList<Reponse> readAll() {

            ObservableList<Reponse> list = FXCollections.observableArrayList();



            String requete1="SELECT reponse, date_reponse FROM reponse";
            //List<Reclamation> list =new ArrayList<>();
            try {
                ste=conn.createStatement();
                ResultSet rs= ste.executeQuery(requete1);
                while(rs.next()){
                    list.add(new Reponse(rs.getString("reponse"),rs.getDate("date_reponse")));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return list;
        }

        public Reponse readById(int id)
        {
            String requete="SELECT reponse, date_reponse FROM reponse  WHERE id_reponse='"+id+"'";
            Reponse rep=null;
            try {
                ste= conn.createStatement();
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


        public int repurerID_reclamation(String st,String st2){
            String requete="SELECT id_reclamation FROM reclamation WHERE description='"+st+"' AND type_reclamation='"+st2+"'";
            int rep=0;
            try {
                ste= conn.createStatement();
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
    public double moyenneTempsdereponse(int mois) {
        String requete = "SELECT AVG(TIMESTAMPDIFF(HOUR, date_reclamation, date_reponse)) AS moyenne_temps_reponse_en_heures " +
                "FROM reclamation " +
                "JOIN reponse ON reclamation.id_reclamation = reponse.id_reclamation " +
                "WHERE MONTH(date_reponse) = ?";
        int moyenneTempsReponse = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, mois);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                moyenneTempsReponse = rs.getInt("moyenne_temps_reponse_en_heures");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return moyenneTempsReponse;
    }



    }











