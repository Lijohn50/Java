package com.example.demo.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static final String DB_HOST  = "localhost";
    private static final String DB_PORT  = "3306";
    private static final String DB_USER  = "root";
    private static final String DB_PASS  = "1210";
    private static final String DB_NAME  = "gamerange";
    private static final String DB_URL   = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    private static Connection connection;
    private static ConnectionSingleton instance = new ConnectionSingleton();

    private ConnectionSingleton()
    {
        try
        {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }

    }

    public static Connection getConnection()
    {
        return connection;
    }


}
