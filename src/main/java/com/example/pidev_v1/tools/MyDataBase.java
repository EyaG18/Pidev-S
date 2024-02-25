package com.example.pidev_v1.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {

    public static final String url = "jdbc:mysql://localhost:3306/pidev2";
    public static final String user ="root";
    public static final  String pwd="";
    private Connection cnx ;
    public static  MyDataBase ct  ;

    public MyDataBase()
    {
        try {
            cnx = DriverManager.getConnection(url , user,pwd);
            System.out.println("Connexion établie avec success! ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }

    public static MyDataBase getInstance()
    {
        if ( ct == null)
        {
            ct = new MyDataBase();
        }
        return ct ;
    }



}
