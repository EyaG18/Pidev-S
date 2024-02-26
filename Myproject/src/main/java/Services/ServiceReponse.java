package Services;

import DataBase.DataSource;
import entities.Reclamation;
import entities.Reponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ServiceReponse implements Services<Reponse> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    public ServiceReponse(){conn= DataSource.getInstance().getCnx();}

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

    public int repurerID_reclamation(String st){
        String requete="SELECT id_reclamation FROM reclamation WHERE description='"+st+"'";
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
