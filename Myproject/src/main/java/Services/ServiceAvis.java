package Services;

import DataBase.DataSource;
import entities.Avis;
import entities.Reponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ServiceAvis implements Services<Avis>{

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    public ServiceAvis(){conn= DataSource.getInstance().getCnx();}

    public void add(Avis A)
    {
        String requete="INSERT into avis (id_client,id_produit,commentaire,note) VALUES(?,?,?,?)";

        try {
            pst=conn.prepareStatement(requete);
            pst.setInt(1,A.getID_client());
            pst.setInt(2,A.getID_produit());
            pst.setString(3,A.getCommentaire());
            pst.setInt(4,A.getNote());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Avis A)
    {
        String requete="DELETE FROM avis where commentaire=? AND note=?";
        try {
            pst=conn.prepareStatement(requete);
            pst.setString(1,A.getCommentaire());
            pst.setInt(2,A.getNote());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Avis A)
    {
        String requete="UPDATE avis SET commentaire=?,note=? where id_avis=?";
        try {
            pst=conn.prepareStatement(requete);
            pst.setString(1,A.getCommentaire());
            pst.setInt(2,A.getNote());
            pst.setInt(3,A.getID_avis());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Avis> readAll() {

        ObservableList<Avis> list = FXCollections.observableArrayList();



        String requete1="SELECT user.nomuser,produit.NomP,commentaire, note FROM avis JOIN user ON avis.id_client = user.id_user JOIN produit ON avis.id_produit = produit.Id_Produit ";
        //List<Reclamation> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            ResultSet rs= ste.executeQuery(requete1);
            while(rs.next()){
                list.add(new Avis(rs.getString("nomuser"),rs.getString("NomP"),rs.getString("commentaire"),rs.getInt("note")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Avis readById(int id)
    {
        String requete="SELECT user.nomuser,produit.NomP, commentaire, note FROM avis JOIN user ON avis.id_client = user.id_user JOIN produit ON avis.id_produit = produit.Id_Produit WHERE id_avis='"+id+"'";
        Avis avis=null;
        try {
            ste= conn.createStatement();
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

    public int repurerID_produit(String st){
        String requete="SELECT Id_Produit FROM produit WHERE NomP='"+st+"'";
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
    public int repurerID_avis(Integer idp ,Integer idc){
        String requete="SELECT id_avis FROM avis WHERE id_produit= '"+idp+"' AND id_client='"+idc+"'";
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
}
