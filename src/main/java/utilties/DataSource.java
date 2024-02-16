package utilties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    final String url="jdbc:mysql://localhost:3306/esprit";
    final String username="root";
    final String pwd="";
    private Connection conx;

    public static DataSource instance;


    public static DataSource getInstance(){
        if (instance == null)
            instance = new DataSource();
        return instance;

    }

    private DataSource() {
        try {
            conx = DriverManager.getConnection(url, username, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConx() {
        return conx;
    }
}
