package com.example.vpndatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
   public Connection connection;

    public Connection getConnection() {

        String dbName = "vpnusers";
        String userName = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);

        } catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }
}
