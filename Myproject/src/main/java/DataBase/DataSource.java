package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    String url="jdbc:mysql://localhost:3306/esprit-3";
    String user="root";
    String pwd="";
    Connection cnx ;
    private static DataSource instance;

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("cnx etablie");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance==null)
            instance=new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
