package services;


import entities.Commande;
import entities.Livraison;
import utlis.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonService implements IService<Livraison> {
    Connection cnx;
    private Statement ste;
    private PreparedStatement pre;

    private LivraisonService(){{cnx= DataSource.getInstance().getCnx();}}

    @Override
    public int Add(Livraison livraison) {
        String requete="insert into Livraison (	Status_livraison,date_livraison,prix_livraison)";
        try {
            pre=cnx.prepareStatement(requete);
            pre.setString(4,livraison.getStatusLivraison().toString());
            pre.setDate(5,new java.sql.Date(livraison.getDate_livraison().getTime()));
            pre.setFloat(6,livraison.getPrix_livraison());
            pre.executeUpdate();
            System.out.println("Livraison ajoutee !!");
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;}

        @Override
    public List<Livraison> Afficher() {
        String requete="SELECT * from Livraison ";
            List<Livraison> list=new ArrayList<>();
            try {
                ste=cnx.createStatement();
                ResultSet rs =ste.executeQuery(requete);
                while (rs.next()){
                    list.add(new Livraison(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            Livraison.Status_livraison.valueOf(rs.getString(4)),
                            rs.getDate(5),
                            rs.getFloat(6)));

    }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    @Override
    public void Delete(Livraison livraison) {
        String requete="DELETE FROM Livraison WHERE id_livraison=?";
        try{pre=cnx.prepareStatement(requete);
            pre.setInt(1,livraison.getId_livraison());
            pre.executeUpdate();
            if(pre.executeUpdate()>0)
                System.out.println("Livraison suprimee!");
            else System.out.println("Livraison non suprimee !");

        } catch (SQLException e) {
            throw new RuntimeException(e);

            }}

            @Override
    public void Modify(Livraison livraison) {
                String requete="UPDATE Commande Set Status_livraison=?,date_livraison=? ,prix_livraison=? WHERE id_livraison=?";
                try {
                    pre=cnx.prepareStatement(requete);
                    pre.setString(4,livraison.getStatusLivraison().toString());
                    pre.setDate(5,new java.sql.Date(livraison.getDate_livraison().getTime()));
                    pre.setFloat(6,livraison.getPrix_livraison());
                    //executeUpdate return 0 ou 1
                    if(pre.executeUpdate()>0){
                        System.out.println("Livraison a ete modifiee avec succes !");
                    }else
                        System.out.println("Echec de modification");
                    pre.close(); //to make sure l prestatement is clawzed
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

    @Override
    public Livraison getById(int T) {
        return null;
    }
}


