package services;

import entities.Commande;
import utlis.DataSource;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements IService<Commande> {
    Connection cnx;
    private Statement ste;
    private PreparedStatement pre;

    public CommandeService(){cnx= DataSource.getInstance().getCnx();}

    @Override
    public int Add(Commande commande) {
        String requete="insert into Commande (produit,Quantite,status_com,Date_com,livrable) values(?,?,?,?,?,?,?)" ;
        try {
            pre=cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pre.setString(3,commande.getProduit());
            pre.setFloat(4,commande.getQuantite());
            pre.setString(5,commande.getStatus().toString());
            pre.setDate(6,new java.sql.Date(commande.getDate_com().getTime()));
            pre.setBoolean(7, commande.isLivrable());

            int affectedRows =pre.executeUpdate();
            //vérifier si la commande a ete ajoutée
            if (affectedRows ==0){
                throw new SQLException("aucune ligne affectée");
            }
            //recuperer l 'id de la nouvelle commande pour le prochain usage
            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Échec de la création, pas d'ID obtenu");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'ajout");
        }
        return  0;
    }

    @Override
    public List<Commande> Afficher() {
        String requete="SELECT * from Commande ";
        List<Commande> list=new ArrayList<>();
        try {
            ste=cnx.createStatement();
             ResultSet rs =ste.executeQuery(requete);
             while (rs.next()){
                 list.add(new Commande(
                         rs.getInt(2),
                         rs.getInt(3),
                         rs.getString(4),
                         rs.getInt(5) ,
                         Commande.status_com.valueOf(rs.getString(6)), // Convert string to enum
                         rs.getDate(7),
                         rs.getBoolean(8)));
             }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void Delete(Commande commande) {
    String requete ="Delete FROM ommand where id_commande=?";
    try{pre=cnx.prepareStatement(requete);
        pre.setInt(1,commande.getId_commande());
        pre.executeUpdate();
        if(pre.executeUpdate()>0)
            System.out.println("Commande suprimee!");
        else System.out.println("Commande non suprimee !");

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void Modify(Commande commande) {
        String requete="UPDATE Commande Set produit=?,Quantite=?,livrable=? WHERE id_commande=? AND id_user=? ";
   try {
       pre=cnx.prepareStatement(requete);
       pre.setString(1,commande.getProduit());
       pre.setFloat(2,commande.getQuantite());
       pre.setBoolean(3, commande.isLivrable());

       //executeUpdate return 0 ou 1
       if(pre.executeUpdate()>0){
           System.out.println("Commande a ete modifiee avec succes !");
       }else
           System.out.println("Echec de modification");
            pre.close(); //to make sure l prestatement is clawzed
   } catch (SQLException e) {
       throw new RuntimeException(e);
   }

    }

    @Override
    public Commande getById(int id_commande) {
        String requete="SELECT * FROM Commande WHERE id_commande=?";
        Commande commande = null;
        try {
            pre=cnx.prepareStatement(requete);
            pre.setInt(1, id_commande);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                commande = new Commande(
                        rs.getInt("id_user"),
                        rs.getInt("Id_Panier"),
                        rs.getString("produit"),
                        rs.getFloat("Quantite"),
                        Commande.status_com.valueOf(rs.getString("status_com")),
                        rs.getDate("Date_com"),
                        rs.getBoolean("livrable")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commande;
    }}
